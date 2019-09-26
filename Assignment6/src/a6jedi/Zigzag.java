
package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Zigzag implements Iterator<Pixel>{
	private int cx,cy;
	private Picture source;
	public Zigzag(Picture s)
	{
		if(s==null)
		{
			throw new IllegalArgumentException("Picture source cannot be null");
		}
		source = s;
		cx = 0;
		cy = 0;
		
	}

	@Override
	public boolean hasNext() {
		
		if (cx == -1) {
			return false;
		}
		return true;
	}

	@Override
	public Pixel next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No more pixels");
		}
		
		Pixel p = source.getPixel(cx,cy);
		if ((cx != source.getWidth() - 1) || (cy != source.getHeight() - 1)) {
			
			if((cy+cx)%2 == 0)
			{	
				if(cy == 0 && cx != source.getWidth() -1 )
				{
					cx++;
				} else if(cx == source.getWidth() -1){
					cy++;
				} else {
					cx++;
					cy--;
				}
			}else
			{
				if(cx==0 && cy != source.getHeight() -1)
					cy++;
				else if(cy == source.getHeight() -1)
					cx++;
				else
				{
					cy++;
					cx--;
				}
			}	
		}else
		{
			cx = -1;
		}
		return p;
	}
}
