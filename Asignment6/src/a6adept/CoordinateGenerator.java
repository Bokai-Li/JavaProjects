package a6adept;

import java.util.NoSuchElementException;

public class CoordinateGenerator implements java.util.Iterator<Coordinate>
{
  private int init_x;
  private int init_y;
  private int dx;
  private int dy;
  private int max_x;
  private int max_y;
  private int cur_x;
  private int cur_y;
  
  public CoordinateGenerator(int init_x, int init_y, int dx, int dy, int max_x, int max_y)
  {
    this.init_x = init_x;
    this.init_y = init_y;
    this.dx = dx;
    this.dy = dy;
    this.max_x = max_x;
    this.max_y = max_y;
    cur_x = init_x;
    cur_y = init_y;
  }
  
  public boolean hasNext()
  {
    return cur_y <= max_y;
  }
  
  public Coordinate next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("No more coordinates to generate");
    }
    Coordinate c = new Coordinate(cur_x, cur_y);
    cur_x += dx;
    if (cur_x > max_x) {
      cur_x = init_x;
      cur_y += dy;
    }
    return c;
  }
}
