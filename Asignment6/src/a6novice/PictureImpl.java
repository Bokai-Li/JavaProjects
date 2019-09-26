package a6novice;

public class PictureImpl
  extends AnyPicture
{
  private Pixel[][] pixels;
  private static final Pixel INITIAL_PIXEL = new GrayPixel(0.5D);
  
  public PictureImpl(int width, int height) {
    super(width, height);
    
    pixels = new Pixel[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        pixels[x][y] = INITIAL_PIXEL;
      }
    }
  }
  
  public void setPixel(int x, int y, Pixel p)
  {
    if (p == null) {
      throw new RuntimeException("Pixel p is null");
    }
    if ((x < 0) || (x >= getWidth())) {
      throw new RuntimeException("x is out of bounds");
    }
    if ((y < 0) || (y >= getHeight())) {
      throw new RuntimeException("y is out of bounds");
    }
    pixels[x][y] = p;
  }
  
  public Pixel getPixel(int x, int y)
  {
    if ((x < 0) || (x >= getWidth())) {
      throw new RuntimeException("x is out of bounds");
    }
    if ((y < 0) || (y >= getHeight())) {
      throw new RuntimeException("y is out of bounds");
    }
    return pixels[x][y];
  }
}
