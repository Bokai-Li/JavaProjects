package a6adept;

public class TransparentColorPixel extends ColorPixel implements TransparentPixel
{
  private double transparency;
  
  public TransparentColorPixel(double r, double g, double b, double t) {
    super(r, g, b);
    if ((t > 1.0D) || (t < 0.0D)) {
      throw new RuntimeException("Transparency out of bounds");
    }
    
    transparency = t;
  }
  
  public TransparentPixel blend(TransparentPixel p, double weight)
  {
    Pixel blended_base = blend(p, weight);
    
    return new TransparentColorPixel(blended_base.getRed(), 
      blended_base.getGreen(), 
      blended_base.getBlue(), getTransparency() * p.getTransparency());
  }
  
  public double getTransparency()
  {
    return transparency;
  }
}
