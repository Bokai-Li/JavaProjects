package a4test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import a4novice.*;

public class A4NoviceTest {

	@Test
	public void SubPictureImplTest() {
		Picture source = new PictureImpl(10,10);
		SubPicture sub = new SubPictureImpl(source, 2, 3, 3, 4);
		Assert.assertEquals(3, sub.getWidth(), 0.01);
		Assert.assertEquals(4, sub.getHeight(), 0.01);
		Assert.assertEquals(2, sub.getXOffset(), 0.01);
		Assert.assertEquals(3, sub.getYOffset(), 0.01);
		for(int x = 0; x<source.getWidth(); x++)
		{
			for(int y = 0; y<source.getHeight(); y++)
			{
				if(!source.getPixel(x, y).equals(sub.getSource().getPixel(x, y)))
					fail("picture source is not equal to the subpicture source");
			}
		}
		
	}

}
