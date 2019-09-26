package a3adept;

public class PictureImpl implements Picture{
	private int w, h;
	private Pixel[][] pixels;
	public PictureImpl(int width, int height)
	{
		if(width<0 || height<0)
			throw new RuntimeException("width and height cannot be negative.");
		w=width;
		h=height;
		pixels = new Pixel[w][h];
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = new GrayPixel(0.5);
			}
		}
	}
	
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

}
