package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileIterator implements Iterator<SubPicture> {
	private Picture source;
	private int width, height;
	private int currentX, currentY;
	
	public TileIterator(Picture s, int window_width, int window_height)
	{
		if(s==null)
		{
			throw new IllegalArgumentException("Source Picture cannot be null");	
		}
		source = s;
		width = window_width;
		height = window_height;
		currentX = 0;
		currentY = 0;
		
	}
	public boolean hasNext()
	{
		return currentY + height <= source.getHeight();
	}
	public SubPicture next()
	{
		if (!hasNext()) {
			throw new NoSuchElementException("No more tiles");
		}
		SubPicture p = source.extract(currentX, currentY, width, height);
		if(currentX + width + width > source.getWidth())
		{
			currentY += height;
			currentX = 0;
		}else
		{
			currentX += width;
		}
		return p;
	}

}
