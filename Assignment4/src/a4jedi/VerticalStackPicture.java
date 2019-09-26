package a4jedi;

public class VerticalStackPicture extends AnyPicture{
	public VerticalStackPicture(Picture top, Picture bottom)
	{
	
		if(top == null || bottom == null)
			throw new IllegalArgumentException("Source is null.");
		if(top.getWidth()!=bottom.getWidth() || top.getHeight()!=bottom.getHeight())
			throw new IllegalArgumentException("Imcompatable Size!");
		pixels = new Pixel[w][2*h];
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = top.getPixel(x, y);
			}
		}
		for(int x = w; x<w; x++)
		{
			for(int y = h; y<2*h; y++)
			{
				pixels[x][y] = bottom.getPixel(x, y-h);
			}
		}
			
		
	}

}
