package a6adept;

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
			
		
	}


	public void setPixel(int x, int y, Pixel p) {
		if(x>=w || y>=h || x<0 || y<0)
			throw new RuntimeException("Picture index out of bound.");
		if(p == null)
			throw new RuntimeException("Pixel is null");
		pixels[x+xO][y+yO] = p;
		
	}
	public Pixel getPixel(int x, int y) {
		if(x>=w || y>=h || x<0 || y<0)
			throw new RuntimeException("Picture index out of bound.");

		return pictureSource.getPixel(x+xO, y+yO);
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
