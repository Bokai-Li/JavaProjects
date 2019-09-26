package a7;

import java.util.ArrayList;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture{

	private Picture originalPicture;
	private List<Region>  r = new ArrayList<Region>();
	private List<ROIObserver> o = new ArrayList<ROIObserver>();
	//ROIObserver[] suspendedObservers;
	private Region suspendedRegion;
	private boolean state = true;
	
	
	public ObservablePictureImpl(Picture p)
	{
		originalPicture = p;
	}
	@Override
	public int getWidth() {
		return originalPicture.getWidth();
	}

	@Override
	public int getHeight() {
		return originalPicture.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return originalPicture.getPixel(x,y);
	}

	@Override
	public Pixel getPixel(Coordinate c) {
		return originalPicture.getPixel(c);
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		originalPicture.setPixel(x, y, p);
		if(!state)
		{
			Region re = new RegionImpl(new Coordinate(x,y),new Coordinate(x,y));
			if(suspendedRegion == null)
				suspendedRegion = re;
			else
			{
				suspendedRegion = suspendedRegion.union(re);
			}	
		}
		else
		{
			for(Region i : this.r)
			{
				try {
					if(x<=i.getRight()&&x>=i.getLeft()&&y<=i.getBottom()&&y>=i.getTop())
					{
						o.get(r.indexOf(i)).notify(this, i.intersect(new RegionImpl(new Coordinate(x,y),new Coordinate(x,y))));
					}
				}
				catch(NoIntersectionException e)
				{
				}
			}
		}
	}

	@Override
	public void setPixel(Coordinate c, Pixel p) {
		this.setPixel(c.getX(),c.getY(), p);
	}

	@Override
	public SubPicture extract(int xoff, int yoff, int width, int height) {
		return originalPicture.extract(xoff, yoff, width, height);
	}

	@Override
	public SubPicture extract(Coordinate a, Coordinate b) {
		return extract(a,b);
	}

	@Override
	public void registerROIObserver(ROIObserver observer, Region r) {
		this.r.add(r);
		this.o.add(observer);
	}

	@Override
	public void unregisterROIObservers(Region r) {
		for(int index = 0; index < this.r.size();index++)
		{
			try {
				this.r.get(index).intersect(r);
				o.remove(index);
				this.r.remove(index);
				index--;
			}
			catch(NoIntersectionException e)
			{
			}
		}
	}

	@Override
	public void unregisterROIObserver(ROIObserver observer) {
		while(o.contains(observer))
		{
			r.remove(o.indexOf(observer));
			o.remove(o.indexOf(observer));
		}

	}

	@Override
	public ROIObserver[] findROIObservers(Region r) {
		int count=0;
		for(Region i : this.r)
			try {
				i.intersect(r);
				count++;
			}
			catch(NoIntersectionException e)
			{
			}
		ROIObserver[] ROI = new ROIObserver[count];
		count = 0;
		for(Region i : this.r)
			try {
				i.intersect(r);
				ROI[count] = o.get(this.r.indexOf(i));
				count++;
			}
			catch(NoIntersectionException e)
			{
			}
		return ROI;
	}

	@Override
	public void suspendObservable() {
		state = false;
	}

	@Override
	public void resumeObservable() {
		state = true;
		for(ROIObserver observer: o)
		{
			try {
				observer.notify(this, suspendedRegion.intersect(r.get(this.o.indexOf(observer))));
			}
			catch(NoIntersectionException e)  
			{
			}
		}
		suspendedRegion = null;
	}

}
