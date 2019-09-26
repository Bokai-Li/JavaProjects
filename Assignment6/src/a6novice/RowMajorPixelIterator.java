package a6novice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements Iterator<Pixel>{
	private Picture source;
	private int pointerX, pointerY;
	public RowMajorPixelIterator(Picture p)
	{
		source = p;
		pointerX = 0;
		pointerY = 0;
	}
	@Override
	public boolean hasNext() {
		if(pointerY>=source.getHeight())
		{
			return false;
		}else
			return true;
	}

	@Override
	public Pixel next() {
		if (!hasNext()) {
		      throw new NoSuchElementException("out of bound");
		    }
		Pixel p = source.getPixel(pointerX,pointerY);
		if(pointerX == source.getWidth()-1)
		{
			pointerY++;
			pointerX=0;
		}
		else
		{
			pointerX++;
		}
		return p;
	}

}
