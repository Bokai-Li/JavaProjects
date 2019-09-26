package a5.bokai247;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import a6adept.*;

public class A6AdeptTest {

	private static final Pixel RED = new ColorPixel(1,0,0);
	private static final Pixel GREEN = new ColorPixel(0,1,0);
	private static final Pixel BLUE = new ColorPixel(0,0,1);
	private static final Pixel WHITE = new GrayPixel(1);
	private static final Pixel BLACK = new GrayPixel(0);
	
	static public String[] getTestNames() {
		String[] test_names = new String[4];
		
		test_names[0] = "testSampleIterator";
		test_names[1] = "testWindowIterator";
		test_names[2] = "testTileIterator";
		test_names[3] = "testInvalidInput";
		
		return test_names;
	}
		
	@Test
	public void testSampleIterator() 
	{
		Picture p = new PictureImpl(15,10);
		Iterator<Pixel> sample_iter = p.sample(2, 3, 3, 4);
		
		Pixel p1 = new GrayPixel(0.0);
		Pixel p2 = new GrayPixel(0.1);
		Pixel p3 = new GrayPixel(0.2);
		Pixel p4 = new GrayPixel(0.3);
		Pixel p5 = new GrayPixel(0.4);
		Pixel p6 = new GrayPixel(1.0);
		Pixel p7 = new GrayPixel(0.6);
		Pixel p8 = new GrayPixel(0.7);
		Pixel p9 = new GrayPixel(0.8);
		Pixel p10 = new GrayPixel(0.9);
		
		
		p.setPixel(2, 3, p1);
		p.setPixel(5, 3, p2);
		p.setPixel(8, 3, p3);
		p.setPixel(11, 3, p4);
		p.setPixel(14, 3, p5);
		p.setPixel(2, 7, p6);
		p.setPixel(5, 7, p7);
		p.setPixel(8, 7, p8);
		p.setPixel(11, 7, p9);
		p.setPixel(14, 7, p10);
		
		assertEquals("Sample iterator next method is wrong", p1 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p2 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p3 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p4 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p5 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p6 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p7 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p8 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p9 , sample_iter.next());
		assertEquals("Sample iterator next method is wrong", p10 , sample_iter.next());
		try{
			sample_iter.next();
			fail("iterator should not have next element");
		}
		catch(NoSuchElementException e) {
		}
	}
	@Test
	public void testWindowIterator() 
	{
		Picture p = new PictureImpl(3,3);
		Iterator<SubPicture> window_iter = p.window(2, 2);
		Pixel p1 = new GrayPixel(Math.random());
		Pixel p2 = new GrayPixel(Math.random());
		Pixel p3 = new GrayPixel(Math.random());
		Pixel p4 = new GrayPixel(Math.random());
		Pixel p5 = new GrayPixel(Math.random());
		Pixel p6 = new GrayPixel(Math.random());
		Pixel p7= new GrayPixel(Math.random());
		Pixel p8= new GrayPixel(Math.random());
		Pixel p9 = new GrayPixel(Math.random());
		
		p.setPixel(0,0,p1);
		p.setPixel(1,0,p2);
		p.setPixel(2,0,p3);
		p.setPixel(0,1,p4);
		p.setPixel(1,1,p5);
		p.setPixel(2,1,p6);
		p.setPixel(0,2,p7);
		p.setPixel(1,2,p8);
		p.setPixel(2,2,p9);
		
		Picture s1 = window_iter.next();
		assertEquals(p.extract(0,0,2,2).getPixel(0,0),s1.getPixel(0,0));
		assertEquals(p.extract(0,0,2,2).getPixel(1,0),s1.getPixel(1,0));
		assertEquals(p.extract(0,0,2,2).getPixel(0,1),s1.getPixel(0,1));
		assertEquals(p.extract(0,0,2,2).getPixel(1,1),s1.getPixel(1,1));
		
		Picture s2 = window_iter.next();
		assertEquals(p.extract(1,0,2,2).getPixel(0,0),s2.getPixel(0,0));
		assertEquals(p.extract(1,0,2,2).getPixel(1,0),s2.getPixel(1,0));
		assertEquals(p.extract(1,0,2,2).getPixel(0,1),s2.getPixel(0,1));
		assertEquals(p.extract(1,0,2,2).getPixel(1,1),s2.getPixel(1,1));
	
		Picture s3 = window_iter.next();
		assertEquals(p.extract(0,1,2,2).getPixel(0,0),s3.getPixel(0,0));
		assertEquals(p.extract(0,1,2,2).getPixel(1,0),s3.getPixel(1,0));
		assertEquals(p.extract(0,1,2,2).getPixel(0,1),s3.getPixel(0,1));
		assertEquals(p.extract(0,1,2,2).getPixel(1,1),s3.getPixel(1,1));
		
		Picture s4 = window_iter.next();
		assertEquals(p.extract(1,1,2,2).getPixel(0,0),s4.getPixel(0,0));
		assertEquals(p.extract(1,1,2,2).getPixel(1,0),s4.getPixel(1,0));
		assertEquals(p.extract(1,1,2,2).getPixel(0,1),s4.getPixel(0,1));
		assertEquals(p.extract(1,1,2,2).getPixel(1,1),s4.getPixel(1,1));
	}
	
	@Test
	public void testTileIterator()
	{
		Picture p = new PictureImpl(5,5);
		Iterator<SubPicture> tile_iter = p.tile(2, 2);
		
		p.setPixel(0,0,RED);
		p.setPixel(1,0,GREEN);
		p.setPixel(2,0,BLUE);
		p.setPixel(3,0,WHITE);
		p.setPixel(4,0,BLACK);
		p.setPixel(0,1,WHITE);
		p.setPixel(1,1,BLACK);
		p.setPixel(2,1,GREEN);
		p.setPixel(3,1,RED);
		p.setPixel(4,1,WHITE);
		p.setPixel(0,2,RED);
		p.setPixel(1,2,GREEN);
		p.setPixel(2,2,BLUE);
		p.setPixel(3,2,BLACK);
		p.setPixel(4,2,BLUE);
		
		Picture s1 = tile_iter.next();
		assertEquals(p.extract(0,0,2,2).getPixel(0,0),s1.getPixel(0,0));
		assertEquals(p.extract(0,0,2,2).getPixel(1,0),s1.getPixel(1,0));
		assertEquals(p.extract(0,0,2,2).getPixel(0,1),s1.getPixel(0,1));
		assertEquals(p.extract(0,0,2,2).getPixel(1,1),s1.getPixel(1,1));
		
		Picture s2 = tile_iter.next();
		assertEquals(p.extract(2,0,2,2).getPixel(0,0),s2.getPixel(0,0));
		assertEquals(p.extract(2,0,2,2).getPixel(1,0),s2.getPixel(1,0));
		assertEquals(p.extract(2,0,2,2).getPixel(0,1),s2.getPixel(0,1));
		assertEquals(p.extract(2,0,2,2).getPixel(1,1),s2.getPixel(1,1));
	
		Picture s3 = tile_iter.next();
		assertEquals(p.extract(0,2,2,2).getPixel(0,0),s3.getPixel(0,0));
		assertEquals(p.extract(0,2,2,2).getPixel(1,0),s3.getPixel(1,0));
		assertEquals(p.extract(0,2,2,2).getPixel(0,1),s3.getPixel(0,1));
		assertEquals(p.extract(0,2,2,2).getPixel(1,1),s3.getPixel(1,1));
		
		Picture s4 = tile_iter.next();
		assertEquals(p.extract(2,2,2,2).getPixel(0,0),s4.getPixel(0,0));
		assertEquals(p.extract(2,2,2,2).getPixel(1,0),s4.getPixel(1,0));
		assertEquals(p.extract(2,2,2,2).getPixel(0,1),s4.getPixel(0,1));
		assertEquals(p.extract(2,2,2,2).getPixel(1,1),s4.getPixel(1,1));
		
		
	}
	@Test
	public void testInvalidInput()
	{
		Picture p = new PictureImpl(15,10);
		try {
			Iterator<Pixel> sample_iter = p.sample(-2, 3, 3, 4);
			fail("sample iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<Pixel> sample_iter = p.sample(2, -3, 3, 4);
			fail("sample iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<Pixel> sample_iter = p.sample(2, 3, 3, -4);
			fail("sample iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<Pixel> sample_iter = p.sample(2, 3, -3, 4);
			fail("sample iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<Pixel> sample_iter = p.sample(10000, 3, 3, 4);
			fail("sample iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<Pixel> sample_iter = p.sample(1, 10000, 3, 4);
			fail("sample iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		
		try {
			Iterator<SubPicture> window_iter = p.window(-2, 2);
			fail("window iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<SubPicture> window_iter = p.window(2, -2);
			fail("window iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<SubPicture> window_iter = p.window(20000, 1);
			fail("window iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<SubPicture> window_iter = p.window(2, 100000);
			fail("window iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}

		try {
			Iterator<SubPicture> tile_iter = p.tile(-2, 2);
			fail("tile iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<SubPicture> tile_iter = p.tile(2, -2);
			fail("tile iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<SubPicture> tile_iter = p.tile(200000, 2);
			fail("tile iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		try {
			Iterator<SubPicture> tile_iter = p.tile(2, 2000000);
			fail("tile iterator did not check invalid input");
		}catch(IllegalArgumentException e){
		}
		
	}

}
