package a6jedi;

public class GrayPixel extends PixelImpl
{
  private double intensity;
  
  public GrayPixel(double intensity) {
    if ((intensity < 0.0D) || (intensity > 1.0D)) {
      throw new RuntimeException("Intensity value out of range");
    }
    this.intensity = intensity;
  }
  
  public double getRed()
  {
    return intensity;
  }
  
  public double getBlue()
  {
    return intensity;
  }
  
  public double getGreen()
  {
    return intensity;
  }
  
  public double getIntensity()
  {
    return intensity;
  }
}
