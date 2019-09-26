package a6jedi;

public class PictureImpl extends AnyPicture{
	public PictureImpl(int width, int height)
	{
		if(width<0 || height<0)
			throw new RuntimeException("width and height cannot be negative.");
		w=width;
		h=height;
		pixels = new Pixel[w][h];
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = new GrayPixel(0.5);
			}
		}
	}

	
}
