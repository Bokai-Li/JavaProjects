package a6novice;

public class ColorPixel extends PixelImpl
{
  private double red;
  private double green;
  private double blue;
  
  public ColorPixel(double r, double g, double b) {
    if ((r > 1.0D) || (r < 0.0D)) {
      throw new RuntimeException("Red out of bounds");
    }
    if ((g > 1.0D) || (g < 0.0D)) {
      throw new RuntimeException("Green out of bounds");
    }
    if ((b > 1.0D) || (b < 0.0D)) {
      throw new RuntimeException("Blue out of bounds");
    }
    red = r;
    green = g;
    blue = b;
  }
  
  public double getRed()
  {
    return red;
  }
  
  public double getBlue()
  {
    return blue;
  }
  
  public double getGreen()
  {
    return green;
  }
}
