package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleIterator implements Iterator<Pixel>
{
	private int X0,Y0, currentX, currentY, dx, dy;
	Picture source;

	public SimpleIterator(Picture source, int x0, int y0, int cx, int cy)
	{
		if (source == null) {
			throw new IllegalArgumentException("Picture is null");
		}

		X0 =x0;
		Y0 =y0;
		dx = cx;
		dy = cy;
		currentX = X0;
		currentY = Y0;
		this.source = source;


	}
	public boolean hasNext(){
		return currentY <= source.getHeight() - 1;
	}
	public Pixel next()
	{
		if (!hasNext()) {
			throw new NoSuchElementException("No more pixels");
		}
		Pixel p =source.getPixel(currentX,currentY);
		currentX += dx;
		if (currentX > source.getWidth() - 1) {
			currentX = X0;
			currentY += dy;
		}
		return p;
	}
}
