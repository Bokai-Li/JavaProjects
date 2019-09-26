package a3novice;

public class GrayPixel implements Pixel {
	private double i ,r ,g ,b;
	public GrayPixel(double intensity)
	{
		if(intensity<0.0 || intensity>1.0)
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
