package a4jedi;

public class TransformedPicture extends AnyPicture{
	
	public TransformedPicture (Picture source, PixelTransformation xform)
	{
		w = source.getWidth();
		h = source.getHeight();
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = xform.transform(source.getPixel(x,y));
			}
		}
	}
	public void setPixel(int x, int y, Pixel p) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}
	
}
