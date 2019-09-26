package a4jedi;

public class GammaCorrect implements PixelTransformation{
	private double g;
	public GammaCorrect (double gamma)
	{
		g = gamma;
	}
	public Pixel transform(Pixel p)
	{
		if(p == null)
			throw new IllegalArgumentException("Pixel is null");
		double newR, newG, newB;
		newR = Math.pow(p.getRed(), (1.0/g));
		newG = Math.pow(p.getGreen(), (1.0/g));
		newB = Math.pow(p.getBlue(), (1.0/g));
		return new ColorPixel(newR,newG,newB);
		
	}
}
