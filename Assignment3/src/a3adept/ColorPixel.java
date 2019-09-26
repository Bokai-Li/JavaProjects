package a3adept;

public class ColorPixel implements Pixel {
	private double r, g, b;
	private double i;
	public ColorPixel(double red, double green, double blue)
	{
		if(red<0.0 || red>1.0)
			throw new RuntimeException("Red value is out of bound.");
		if(green<0.0 || green>1.0)
			throw new RuntimeException("Green value is out of bound.");
		if(blue<0.0 || blue>1.0)
			throw new RuntimeException("Blue value is out of bound.");
		r=red;
		g=green;
		b=blue;
		i=0.299 * r + 0.587 * g + 0.114 * b;
	}
	public double getRed() {
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
		double rRed, rGreen, rBlue;
		if(weight<0.0 || weight>1.0)
			throw new RuntimeException("Weight value is out of bound.");
		rRed= weight*this.getRed() + (1.0-weight)*p.getRed();
		rGreen= weight*this.getGreen() + (1.0-weight)*p.getGreen();
		rBlue= weight*this.getBlue() + (1.0-weight)*p.getBlue();
		rPixel = new ColorPixel(rRed,rGreen,rBlue);
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
		double largerIntensity=0;
		double Rdiff, Gdiff, Bdiff;
		if(p.getIntensity()<this.getIntensity())
		{
			largerIntensity = this.getIntensity();
		}else
		{
			largerIntensity = p.getIntensity();
		}
		Rdiff = (Math.abs(p.getRed()-this.getRed()))/(largerIntensity);
		Gdiff = (Math.abs(p.getGreen()-this.getGreen()))/(largerIntensity);
		Bdiff = (Math.abs(p.getBlue()-this.getBlue()))/(largerIntensity);
		
		if(Rdiff <0.1 && Gdiff <0.1 && Bdiff<0.1)
			return true;
		else
			return false;
	}
	
	

}
