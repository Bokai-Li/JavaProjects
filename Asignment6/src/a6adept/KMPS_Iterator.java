package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class KMPS_Iterator implements Iterator<Pixel>
{
  private CoordinateGenerator coordinate_generator;
  private Picture source;
  
  public KMPS_Iterator(Picture source, int init_x, int init_y, int dx, int dy)
  {
    if (source == null) {
      throw new IllegalArgumentException("Source for window iterator can not be null");
    }
    
    coordinate_generator = new CoordinateGenerator(init_x, init_y, dx, dy, source.getWidth() - 1, source.getHeight() - 1);
    this.source = source;
  }
  
  public boolean hasNext()
  {
    return coordinate_generator.hasNext();
  }
  
  public Pixel next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("No next pixel in sample iterator");
    }
    return source.getPixel(coordinate_generator.next());
  }
}
