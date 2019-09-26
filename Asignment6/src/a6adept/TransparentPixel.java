package a6adept;

public abstract interface TransparentPixel
  extends Pixel
{
  public abstract double getTransparency();
  
  public abstract TransparentPixel blend(TransparentPixel paramTransparentPixel, double paramDouble);
}
