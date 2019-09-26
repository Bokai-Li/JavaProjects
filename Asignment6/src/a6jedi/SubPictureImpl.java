package a6jedi;

public class SubPictureImpl
  extends AnyPicture implements SubPicture
{
  private Picture source;
  private int x_offset;
  private int y_offset;
  
  public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height)
  {
    super(width, height);
    
    if (source == null) {
      throw new IllegalArgumentException("source picture is null");
    }
    
    if ((xOffset < 0) || (xOffset >= source.getWidth())) {
      throw new IllegalArgumentException("xOffset out of range");
    }
    
    if ((yOffset < 0) || (yOffset >= source.getHeight())) {
      throw new IllegalArgumentException("xOffset out of range");
    }
    
    if (xOffset + width > source.getWidth()) {
      throw new IllegalArgumentException("subpicture too wide for source");
    }
    
    if (yOffset + height > source.getHeight()) {
      throw new IllegalArgumentException("subpicture too tall for source");
    }
    
    this.source = source;
    x_offset = xOffset;
    y_offset = yOffset;
  }
  
  public void setPixel(int x, int y, Pixel p)
  {
    source.setPixel(x + x_offset, y + y_offset, p);
  }
  
  public Pixel getPixel(int x, int y)
  {
    return source.getPixel(x + x_offset, y + y_offset);
  }
  
  public Picture getSource()
  {
    return source;
  }
  
  public int getXOffset()
  {
    return x_offset;
  }
  
  public int getYOffset()
  {
    return y_offset;
  }
}
