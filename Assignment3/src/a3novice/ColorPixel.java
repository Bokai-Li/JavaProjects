package a3novice;

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
			return '>';
		else if(i<0.70)
			return 's';
		else if(i<0.80)
			return ':';
		else if(i<0.90)
			return '-';
		else
			return ' ';
	}
	
	

}
