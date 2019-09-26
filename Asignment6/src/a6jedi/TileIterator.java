package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileIterator implements Iterator<SubPicture>
{
  private Picture source;
  private CoordinateGenerator cg;
  private int tile_width;
  private int tile_height;
  
  public TileIterator(Picture source, int tile_width, int tile_height)
  {
    if (source == null) {
      throw new IllegalArgumentException("Source for tile iterator can not be null");
    }
    
    this.tile_width = tile_width;
    this.tile_height = tile_height;
    this.source = source;
    int max_tile_x = source.getWidth() / tile_width * tile_width - 1;
    int max_tile_y = source.getHeight() / tile_height * tile_height - 1;
    cg = new CoordinateGenerator(0, 0, tile_width, tile_width, max_tile_x, max_tile_y);
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
    Coordinate lr = new Coordinate(ul.getX() + tile_width - 1, ul.getY() + tile_height - 1);
    return source.extract(ul, lr);
  }
}
