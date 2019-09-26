package a6jedi;

public abstract class PixelImpl
  implements Pixel
{
  private static final double RED_INTENSITY_FACTOR = 0.299D;
  private static final double GREEN_INTENSITY_FACTOR = 0.587D;
  private static final double BLUE_INTENSITY_FACTOR = 0.114D;
  private static final Pixel WHITE_PIXEL = new GrayPixel(1.0D);
  private static final Pixel BLACK_PIXEL = new GrayPixel(0.0D);
  private static final char[] PIXEL_CHAR_MAP = { '#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' ', ' ' };
  

  public PixelImpl() {}
  
  public abstract double getRed();
  
  public abstract double getBlue();
  
  public abstract double getGreen();
  
  public double getIntensity()
  {
    return 0.299D * getRed() + 
      0.587D * getGreen() + 
      0.114D * getBlue();
  }
  
  public char getChar()
  {
    int char_idx = (int)(getIntensity() * 10.0D);
    return PIXEL_CHAR_MAP[char_idx];
  }
  
  public Pixel lighten(double factor)
  {
    if ((factor < 0.0D) || (factor > 1.0D)) {
      throw new RuntimeException("Lighten factor out of range");
    }
    return WHITE_PIXEL.blend(this, factor);
  }
  
  public Pixel darken(double factor)
  {
    if ((factor < 0.0D) || (factor > 1.0D)) {
      throw new RuntimeException("Darken factor out of range");
    }
    return BLACK_PIXEL.blend(this, factor);
  }
  
  public Pixel blend(Pixel p, double weight)
  {
    if ((weight < 0.0D) || (weight > 1.0D)) {
      throw new RuntimeException("Blend weight out of range");
    }
    if (p == null) {
      throw new RuntimeException("Blend pixel is null");
    }
    
    return new ColorPixel(getRed() * weight + p.getRed() * (1.0D - weight), 
      getGreen() * weight + p.getGreen() * (1.0D - weight), 
      getBlue() * weight + p.getBlue() * (1.0D - weight));
  }
  
  public boolean equals(Pixel p)
  {
    if (p == null) {
      throw new RuntimeException("Pixel passed to equals method is null");
    }
    
    double max_intensity = getIntensity() > p.getIntensity() ? getIntensity() : p.getIntensity();
    double equal_bound = max_intensity * 0.1D;
    return (Math.abs(getRed() - p.getRed()) < equal_bound) && 
      (Math.abs(getGreen() - p.getGreen()) < equal_bound) && (
      Math.abs(getBlue() - p.getBlue()) < equal_bound);
  }
}
