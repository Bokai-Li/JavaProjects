package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WindowIterator implements Iterator<SubPicture>{
	private Picture source;
	private int width, height;
	private int currentX, currentY;

	public WindowIterator(Picture s, int window_width, int window_height)
	{
		if (s == null) {
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
		return currentY <= source.getHeight()-height;
	}
	public SubPicture next()
	{
		if (!hasNext()) {
			throw new NoSuchElementException("No more windows");
		}
		SubPicture p = source.extract(currentX, currentY, width, height);
		if(currentX + width >= source.getWidth())
		{
			currentY++;
			currentX=0;
		}else
		{
			currentX++;
		}
		return p;

	}
}
