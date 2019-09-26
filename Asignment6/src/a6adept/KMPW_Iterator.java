package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class KMPW_Iterator implements Iterator<SubPicture>
{
  private Picture source;
  private CoordinateGenerator cg;
  private int window_width;
  private int window_height;
  
  public KMPW_Iterator(Picture source, int window_width, int window_height)
  {
    if (source == null) {
      throw new IllegalArgumentException("Source for window iterator can not be null");
    }
    
    this.window_width = window_width;
    this.window_height = window_height;
    this.source = source;
    int max_window_x = source.getWidth() - window_width;
    int max_window_y = source.getHeight() - window_height;
    cg = new CoordinateGenerator(0, 0, 1, 1, max_window_x, max_window_y);
  }
  
  public boolean hasNext()
  {
    return cg.hasNext();
  }
  
  public SubPicture next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("No next window");
    }
    Coordinate ul = cg.next();
    Coordinate lr = new Coordinate(ul.getX() + window_width - 1, ul.getY() + window_height - 1);
    return source.extract(ul, lr);
  }
}
