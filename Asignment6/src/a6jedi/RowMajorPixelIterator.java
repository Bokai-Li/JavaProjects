package a6jedi;

import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements java.util.Iterator<Pixel>
{
  private Picture source;
  private int current_x;
  private int current_y;
  
  public RowMajorPixelIterator(Picture source)
  {
    this.source = source;
    current_x = 0;
    current_y = 0;
  }
  
  public boolean hasNext()
  {
    return current_y < source.getHeight();
  }
  

  public Pixel next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("No more pixels");
    }
    Pixel p = source.getPixel(current_x, current_y);
    if (current_x == source.getWidth() - 1) {
      current_x = 0;
      current_y += 1;
    } else {
      current_x += 1;
    }
    return p;
  }
}
