package a5.bokai247;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import a6novice.*;

public class A6NoviceTest {
	private static final Pixel RED = new ColorPixel(1,0,0);
	private static final Pixel GREEN = new ColorPixel(0,1,0);
	private static final Pixel BLUE = new ColorPixel(0,0,1);
	private static final Pixel WHITE = new GrayPixel(1);
	private static final Pixel BLACK = new GrayPixel(0);
		
	static public String[] getTestNames() {
		String[] test_names = new String[5];
		
		test_names[0] = "testCoordinateGet";
		test_names[1] = "testSetGetPixel";
		test_names[2] = "testExtractCoordinate";
		test_names[3] = "testIterator";
		test_names[4] = "testReturnIterator";
		
		return test_names;
	}
		
	@Test
	public void testCoordinateGet() {
		Coordinate c = new Coordinate(5,10);
		assertEquals("Coordinate get x method is wrong",5,c.getX(),0.001);
		assertEquals("Coordinate get y method is wrong",10,c.getY(),0.001);
	}
	
	@Test
	public void testSetGetPixel() {
		Picture p1 = new PictureImpl(10,20);
		Coordinate c = new Coordinate(5,10);
		Pixel pix = new GrayPixel(1.0);
		p1.setPixel(c, pix);
		assertEquals("Set/Get pixel is wrong",pix,p1.getPixel(c));
		
	}
	
	@Test
	public void testExtractCoordinate() {
		Picture p = new PictureImpl(3, 3);
		p.setPixel(0, 0, RED);
		p.setPixel(1, 0, RED);
		p.setPixel(2, 0, RED);
		p.setPixel(0, 1, GREEN);
		p.setPixel(1, 1, BLUE);
		p.setPixel(2, 1, WHITE);
		p.setPixel(0, 2, BLACK);
		p.setPixel(1, 2, BLACK);
		p.setPixel(2, 2, RED);
		Coordinate c1 = new Coordinate(1,1);
		Coordinate c2 = new Coordinate(2,2);
		SubPicture sp = p.extract(c1,c2);
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(c1), sp.getPixel(0, 0));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(2, 1), sp.getPixel(1, 0));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(1, 2), sp.getPixel(0, 1));
		assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source",
				p.getPixel(c2), sp.getPixel(c1));
		
		
	}
	
	@Test
	public void testIterator()
	{
		Picture p = new PictureImpl(3, 3);
		Pixel p1 = new GrayPixel(0.0);
		Pixel p2 = new GrayPixel(0.1);
		Pixel p3 = new GrayPixel(0.2);
		Pixel p4 = new GrayPixel(0.3);
		Pixel p5 = new GrayPixel(0.4);
		Pixel p6 = new GrayPixel(0.5);
		Pixel p7 = new GrayPixel(0.6);
		Pixel p8 = new GrayPixel(0.7);
		Pixel p9 = new GrayPixel(0.8);
		
		
		p.setPixel(0, 0, p1);
		p.setPixel(1, 0, p2);
		p.setPixel(2, 0, p3);
		p.setPixel(0, 1, p4);
		p.setPixel(1, 1, p5);
		p.setPixel(2, 1, p6);
		p.setPixel(0, 2, p7);
		p.setPixel(1, 2, p8);
		p.setPixel(2, 2, p9);
		Iterator<Pixel> it = new MajorTomPixelIterator(p);
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p1 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p2 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p3 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p4 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p5 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p6 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p7 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p8 , it.next());
		assertEquals("has next method is wrong", true , it.hasNext());
		assertEquals("MajorTomPixelIterator next method is wrong", p9 , it.next());
		assertEquals("has next method is wrong", false , it.hasNext());
		try{
			it.next();
			fail("iterator should not have next element");
		}
		catch(NoSuchElementException e) {
		}
	}
	@Test
	public void testReturnIterator() {
		Picture p = new PictureImpl(3, 3);
		Iterator<Pixel> it = new MajorTomPixelIterator(p);
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
		assertEquals(it.next(),p.iterator().next());
	}
	
}
