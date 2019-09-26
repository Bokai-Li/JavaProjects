package a4jedi;

public class HorizontalStackPicture extends AnyPicture{

	public HorizontalStackPicture(Picture left, Picture right)
	{
		if(left == null || right == null)
			throw new IllegalArgumentException("Source is null.");
		if(left.getWidth()!=right.getWidth() || left.getHeight()!=right.getHeight())
			throw new IllegalArgumentException("Imcompatable Size!");
		pixels = new Pixel[2*w][h];
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = left.getPixel(x, y);
			}
		}
		for(int x = w; x<2*w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = right.getPixel(x-w, y);
			}
		}
		
	}

}
