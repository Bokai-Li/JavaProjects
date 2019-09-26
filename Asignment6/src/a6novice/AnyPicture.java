package a6novice;

import java.io.PrintStream;

public abstract class AnyPicture implements Picture
{
  private int width;
  private int height;
  
  protected AnyPicture(int width, int height) {
    if ((width <= 0) || (height <= 0)) {
      throw new RuntimeException("Illegal width or height");
    }
    
    this.width = width;
    this.height = height;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  

  public abstract void setPixel(int paramInt1, int paramInt2, Pixel paramPixel);
  

  public abstract Pixel getPixel(int paramInt1, int paramInt2);
  
  public int countRange(double low, double high)
  {
    int count = 0;
    for (int x = 0; x < getWidth(); x++) {
      for (int y = 0; y < getHeight(); y++) {
        double intensity = getPixel(x, y).getIntensity();
        if ((intensity >= low) && (intensity <= high)) {
          count++;
        }
      }
    }
    return count;
  }
  
  public void print()
  {
    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        System.out.print(getPixel(x, y).getChar());
      }
      System.out.println();
    }
  }
  
  public SubPicture extract(int xOffset, int yOffset, int width, int height)
  {
    return new SubPictureImpl(this, xOffset, yOffset, width, height);
  }
  
  public void setPixel(Coordinate c, Pixel p)
  {
    if (c == null) {
      throw new IllegalArgumentException("Coordinate for setPixel is null");
    }
    setPixel(c.getX(), c.getY(), p);
  }
  
  public Pixel getPixel(Coordinate c)
  {
    if (c == null) {
      throw new IllegalArgumentException("Coordinate for getPixel is null");
    }
    return getPixel(c.getX(), c.getY());
  }
  
  public SubPicture extract(Coordinate corner_a, Coordinate corner_b)
  {
    if ((corner_a == null) || (corner_b == null)) {
      throw new IllegalArgumentException("Coordinate for extract is null");
    }
    int min_x = corner_a.getX() < corner_b.getX() ? corner_a.getX() : corner_b.getX();
    int max_x = corner_a.getX() >= corner_b.getX() ? corner_a.getX() : corner_b.getX();
    int min_y = corner_a.getY() < corner_b.getY() ? corner_a.getY() : corner_b.getY();
    int max_y = corner_a.getY() >= corner_b.getY() ? corner_a.getY() : corner_b.getY();
    
    return extract(min_x, min_y, max_x - min_x + 1, max_y - min_y + 1);
  }
  
  public java.util.Iterator<Pixel> iterator()
  {
    return new MajorTomPixelIterator(this);
  }
}
