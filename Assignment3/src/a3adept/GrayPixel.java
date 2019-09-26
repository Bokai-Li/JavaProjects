package a3adept;

public class GrayPixel implements Pixel {
	private double i ,r ,g ,b;
	public GrayPixel(double intensity)
	{
		if(i<0.0 || i>1.0)
			throw new RuntimeException("Intensity value is out of bound.");
		i = intensity;
		r=i;
		g=i;
		b=i;
	}
	public double getRed()
	{
		return r;
	}
	public double getGreen()
	{
		return g;
	}
	public double getBlue()
	{
		return b;
	}
	public double getIntensity()
	{
		return i;
	}
	public char getChar()
	{
		if(i<0.10)
			return '#';
		else if(i<0.20)
			return 'M';
		else if(i<0.30)
			return 'X';
		else if(i<0.40)
			return 'D';
		else if(i<0.50)
			return '<';
		else if(i<0.60)
			return 's';
		else if(i<0.70)
			return ':';
		else if(i<0.80)
			return '-';
		else
			return ' ';
	}
	public Pixel blend(Pixel p, double weight) {
		if(p == null)
			throw new RuntimeException("Pixel is null");
		Pixel rPixel;
		double rIntensity;
		if(weight<0.0 || weight>1.0)
			throw new RuntimeException("Weight value is out of bound.");
		rIntensity= weight*this.getIntensity() + (1.0-weight)*p.getIntensity();
		rPixel = new GrayPixel(rIntensity);
		return rPixel;
	}
	
	public Pixel lighten(double factor) {
		if(factor<0.0 || factor>1.0)
			throw new RuntimeException("factor value is out of bound.");
		Pixel white = new ColorPixel(1.0,1.0,1.0);
		Pixel rPixel = white.blend(this, factor);
		return rPixel;
	}
	public Pixel darken(double factor) {
		if(factor<0.0 || factor>1.0)
			throw new RuntimeException("factor value is out of bound.");
		Pixel black = new ColorPixel(0,0,0);
		Pixel rPixel = black.blend(this, factor);
		return rPixel;
	}
	public boolean equals(Pixel p) {
		if(p == null)
			throw new RuntimeException("Pixel is null");
		double tenPercent=0;
		if(p.getIntensity()<this.getIntensity())
		{
			tenPercent = 0.1 * this.getIntensity();
		}else
		{
			tenPercent = 0.1 * p.getIntensity();
		}
		if(Math.abs(p.getBlue()-this.getBlue())<tenPercent
			&& 	Math.abs(p.getRed()-this.getRed())<tenPercent
			&& Math.abs(p.getGreen()-this.getGreen())<tenPercent)
			return true;
		else
			return false;
	}
}
