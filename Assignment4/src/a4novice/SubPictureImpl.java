package a4novice;

public class SubPictureImpl extends AnyPicture implements SubPicture {
	private int xO,yO;
	private Picture pictureSource;
	public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height)
	{
		int largestW, largestH;
		if(source == null)
			throw new IllegalArgumentException("Source is null.");
		if(xOffset<0 || yOffset<0 || xOffset>=source.getWidth() || yOffset>=source.getHeight())
			throw new IllegalArgumentException("x or y offset is out of bound.");
		largestW = xOffset + width;
		largestH = yOffset + height;
		if(largestW > source.getWidth() || largestH > source.getHeight())
			throw new IllegalArgumentException("invalid area that outside the source picture");
		w = width;
		h = height;
		xO = xOffset;
		yO = yOffset;
		pictureSource = source;//cause aliasing?
		
		pixels = new Pixel[w][h];
		for(int x = 0; x<w; x++)
		{
			for(int y = 0; y<h; y++)
			{
				pixels[x][y] = source.getPixel(xO+x, yO+y);
			}
		}
			
		
	}

	@Override
	public Picture getSource() {
		return pictureSource;
	}

	@Override
	public int getXOffset() {
		return xO;
	}

	@Override
	public int getYOffset() {
		return yO;
	}

}
