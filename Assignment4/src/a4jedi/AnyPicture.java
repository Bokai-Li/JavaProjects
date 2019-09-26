package a4jedi;

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


}
