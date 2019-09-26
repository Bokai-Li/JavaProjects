package a9;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;
	private ObservablePicture beforeMaginify;
	private ObservablePicture afterMagnify;
	private boolean zoomed = false;
	private int zoomx = -1, zoomy=-1;
	
	public ImageEditorModel(Picture f) {
		original = f;
		current = original.copy().createObservable();
		beforeMaginify = original.copy().createObservable();
		afterMagnify = original.copy().createObservable();
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void unzoom()
	{
		current.suspendObservable();
		for (int i=0; i<beforeMaginify.getWidth(); i++) {
			for (int j=0; j<beforeMaginify.getHeight(); j++) {
				current.setPixel(i, j, beforeMaginify.getPixel(i,j));
			}
		}
		zoomed = false;
		current.resumeObservable();
	}
	
	public void zoom(int x,int y)
	{
		afterMagnify.suspendObservable();
		current.suspendObservable();
		int magx=0,magy=0;
		if(!zoomed)
		{
			for (int i=0; i<beforeMaginify.getWidth(); i++) {
				for (int j=0; j<beforeMaginify.getHeight(); j++) {
					beforeMaginify.setPixel(i, j, current.getPixel(i,j));
				}
			}
			zoomed = true;
		}
		zoomx=x;
		zoomy=y;
		for (int xm=0; xm<current.getWidth(); xm++) {
			for (int ym=0; ym<current.getHeight(); ym++) {
					if (xm >= 0 &&
							xm < current.getWidth() &&
							ym >= 0 &&
							ym < current.getHeight()) {

						magx=x-75+xm/2;
						magy=y-68+ym/2;
						if (magx >= 0 &&
								magx < current.getWidth() &&
								magy >= 0 &&
										magy < current.getHeight()) {
							afterMagnify.setPixel(xm, ym, beforeMaginify.getPixel(magx,magy));
						}
						else
						{
							afterMagnify.setPixel(xm, ym, new ColorPixel(0.932,0.932,0.932));
						}
				}
			}
		}
		afterMagnify.resumeObservable();
		for (int i=0; i<current.getWidth(); i++) {
			for (int j=0; j<current.getHeight(); j++) {
				current.setPixel(i, j, afterMagnify.getPixel(i,j));
			}
			}
		current.resumeObservable();
	}
	
	public void paintAt(int x, int y, Pixel brushColor, int brush_size,double weight) 
	{
		current.suspendObservable();
		beforeMaginify.suspendObservable();
		for (int xpos=x-brush_size/2+1; xpos <=x+brush_size/2-1; xpos++) {
			for (int ypos=y-brush_size/2+1; ypos <=y+brush_size/2-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					if(zoomed)
					{
						if (xpos/2+zoomx-75 >= 0 &&
								xpos/2+zoomx-75 < beforeMaginify.getWidth() &&
								ypos/2+zoomy-68 >= 0 &&
								ypos/2+zoomy-68 < current.getHeight()) 
						{
							if(xpos%2==0 && ypos%2 ==0)
							{
								beforeMaginify.setPixel(xpos/2+zoomx-75, ypos/2+zoomy-68, 
										blend(current.getPixel(xpos, ypos), brushColor, weight/100));
							}
							current.setPixel(xpos, ypos, blend(current.getPixel(xpos, ypos), brushColor, weight/100));
							
						}
						
					}
					else
						current.setPixel(xpos, ypos, blend(current.getPixel(xpos, ypos), brushColor, weight/100));
				}
			}
		}
		beforeMaginify.resumeObservable();
		current.resumeObservable();
	}
	
	private Pixel blend(Pixel ori, Pixel p, double weight) {
		if(p == null)
			throw new RuntimeException("Pixel is null");
		Pixel rPixel;
		double rRed, rGreen, rBlue;
		if(weight<0.0 || weight>1.0)
			throw new RuntimeException("Weight value is out of bound.");
		rRed= weight*ori.getRed() + (1.0-weight)*p.getRed();
		rGreen= weight*ori.getGreen() + (1.0-weight)*p.getGreen();
		rBlue= weight*ori.getBlue() + (1.0-weight)*p.getBlue();
		rPixel = new ColorPixel(rRed,rGreen,rBlue);
		return rPixel;
	}

	
}
