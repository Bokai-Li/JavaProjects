package a6jedi;

import java.util.NoSuchElementException;

public class KMP_ZZ_Iterator implements java.util.Iterator<Pixel>
{
	private Picture source;
	private int cur_x;
	private int cur_y;
	private boolean done;

	public KMP_ZZ_Iterator(Picture source)
	{
		this.source = source;
		cur_x = 0;
		cur_y = 0;
		done = false;
	}

	public boolean hasNext()
	{
		return !done;
	}

	public Pixel next()
	{
		if (!hasNext()) {
			throw new NoSuchElementException("Zig zag over");
		}

		Pixel p = source.getPixel(cur_x, cur_y);

		if ((cur_x == source.getWidth() - 1) && (cur_y == source.getHeight() - 1)) {
			done = true;
		}
		else if ((cur_x + cur_y) % 2 == 0)
		{
			if (cur_x == source.getWidth() - 1) {
				cur_y += 1;
			} else if (cur_y == 0) {
				cur_x += 1;
			} else {
				cur_y -= 1;
				cur_x += 1;
			}
		}
		else if (cur_y == source.getHeight() - 1) {
			cur_x += 1;
		} else if (cur_x == 0) {
			cur_y += 1;
		} else {
			cur_x -= 1;
			cur_y += 1;
		}
		return p;
	}
}
