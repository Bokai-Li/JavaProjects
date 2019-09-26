package a4jedi;

public class Threshold implements PixelTransformation{
	private double t;
	public Threshold (double threshold)
	{
		if(threshold < 0 || threshold > 1.0)
			throw new IllegalArgumentException("threshold out of range");
		t = threshold;
	}
	public Pixel transform(Pixel p)
	{
		if(p == null)
			throw new IllegalArgumentException("Pixel is null");
		if(p.getIntensity() > t)
			return new GrayPixel(1.0);
		else
			return new GrayPixel(0.0);
	}

}
