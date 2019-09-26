package a6novice;

import java.util.Iterator;

public abstract class AnyPicture implements Picture{
	protected int w, h;
	protected Pixel[][] pixels;
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public void setPixel(int x, int y, Pixel p) {
		if(x>=w || y>=h || x<0 || y<0)
			throw new RuntimeException("Picture index out of bound.");
		if(p == null)
			throw new RuntimeException("Pixel is null");
		pixels[x][y] = p;
		
	}
	public Pixel getPixel(int x, int y) {
		if(x>=w || y>=h || x<0 || y<0)
			throw new RuntimeException("Picture index out of bound.");

		return pixels[x][y];
	}
	public int countRange(double low, double high) {
		int count=0;
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				if(pixels[x][y].getIntensity() >= low && pixels[x][y].getIntensity() <= high)
				{
					count++;
				}
			}
		}
		return count;
	}
	public void print() {
		for(int y = 0; y<h; y++)
		{
			for(int x = 0; x<w; x++)
			{
				System.out.print(pixels[x][y].getChar());
			}
			System.out.println();
		}
		
	}
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		int largestW, largestH;
		if(xOffset<0 || yOffset<0 || xOffset>=this.getWidth() || yOffset>=this.getHeight())
			throw new IllegalArgumentException("x or y offset is out of bound.");
		largestW = xOffset + width;
		largestH = yOffset + height;
		if(largestW > this.getWidth() || largestH > this.getHeight())
			throw new IllegalArgumentException("invalid area that outside the source picture");
		Picture s = new SubPictureImpl(this, xOffset, yOffset,width,height);
		return (SubPicture) s;
	}
	@Override
	public void setPixel(Coordinate c, Pixel p) {
		if (c == null) {
		      throw new IllegalArgumentException("Coordinate for setPixel is null");
		    }
		    setPixel(c.getX(), c.getY(), p);
	}

	@Override
	public Pixel getPixel(Coordinate c) {
		if(c == null)
		{
			throw new RuntimeException("Coordinate is null");
		}
		return getPixel(c.getX(), c.getY());
	}

	@Override
	public SubPicture extract(Coordinate a, Coordinate b) {
		if(a == null || b == null)
		{
			throw new RuntimeException("Coordinate is null");
		}
		if(a.getX()<0 || a.getY()<0 || a.getX()>=this.getWidth() || a.getY()>=this.getHeight())
			throw new IllegalArgumentException("a is out of bound.");
		
		if(b.getX()<0 || b.getY()<0 ||b.getX() > this.getWidth() || b.getY() > this.getHeight())
			throw new IllegalArgumentException("b is out of bound");
		if(b.getX()<0 || b.getY()<0 || b.getX()>=this.getWidth() || b.getY()>=this.getHeight())
			throw new IllegalArgumentException("b is out of bound.");
		
		if(a.getX()<0 || a.getY()<0 ||a.getX() > this.getWidth() || a.getY() > this.getHeight())
			throw new IllegalArgumentException("a is out of bound");
		
		int x0 = a.getX() < b.getX() ? a.getX() : b.getX();
	    int x1 = a.getX() >= b.getX() ? a.getX() : b.getX();
	    int y0 = a.getY() < b.getY() ? a.getY() : b.getY();
	    int y1 = a.getY() >= b.getY() ? a.getY() : b.getY();
		
		Picture s = new SubPictureImpl(this, x0, y0,x1-x0+1,y1-y0+1);
		return (SubPicture) s;
	}
	public Iterator<Pixel> iterator()
	  {
	    return new RowMajorPixelIterator(this);
	  }


}
