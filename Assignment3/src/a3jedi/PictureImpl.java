package a3jedi;

public class PictureImpl implements Picture{
	private int w, h;
	private Pixel[][] pixels;
	public PictureImpl(int width, int height)
	{
		if(w<0 || h<0)
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
		if(x>=w || y>=h)
			throw new RuntimeException("Picture index out of bound.");
		pixels[x][y] = p;
		
	}
	public Pixel getPixel(int x, int y) {
		if(x>=w || y>=h)
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

	public double unequalPixelRatio(Picture p) {
		if(p.getHeight()!=this.getHeight() || p.getWidth()!=this.getWidth())
			throw new RuntimeException("Picture dimension is not the same");
		double count = 0;
		double ratio;
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				if(!(p.getPixel(x, y).equals(this.getPixel(x, y))))
				{
					count++;
				}
			}
		}
		ratio = count/(this.getHeight()*this.getWidth());
		return ratio;
	}

	public double calculatePSNR(Picture p) {
		if(p.getHeight()!=this.getHeight() || p.getWidth()!=this.getWidth())
			throw new RuntimeException("Picture dimension is not the same");
		double PSNR, MSE, diffSumSq=0;
		
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				diffSumSq = diffSumSq + Math.pow(p.getPixel(x, y).getIntensity()- this.getPixel(x, y).getIntensity(),2);
			}
		}
		MSE = diffSumSq / (this.getHeight()*this.getWidth());
		
		if(MSE == 0)
			throw new RuntimeException("MSE is zero, the pictures are identical");
		PSNR = 20*Math.log10(1.0) - 10*Math.log10(MSE);
		
		return PSNR;
	}

}
