package a6test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6jedi.*;

public class A6JediTest {
		
	static public String[] getTestNames() {
		String[] test_names = new String[1];
		
		test_names[0] = "testZigZag";
		
		return test_names;
	}
		
	@Test
	public void testZigZag() {
		Picture p = new PictureImpl(3,3);
		Iterator<Pixel> zz = p.zigzag();
		Pixel p1 = new GrayPixel(Math.random());
		Pixel p2 = new GrayPixel(Math.random());
		Pixel p3 = new GrayPixel(Math.random());
		Pixel p4 = new GrayPixel(Math.random());
		Pixel p5 = new GrayPixel(Math.random());
		Pixel p6 = new GrayPixel(Math.random());
		Pixel p7 = new GrayPixel(Math.random());
		Pixel p8 = new GrayPixel(Math.random());
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
		
		assertEquals("Zig zag is wrong",p1,zz.next());
		assertEquals("Zig zag is wrong",p2,zz.next());
		assertEquals("Zig zag is wrong",p4,zz.next());
		assertEquals("Zig zag is wrong",p7,zz.next());
		assertEquals("Zig zag is wrong",p5,zz.next());
		assertEquals("Zig zag is wrong",p3,zz.next());
		assertEquals("Zig zag is wrong",p6,zz.next());
		assertEquals("Zig zag is wrong",p8,zz.next());
		assertEquals("Zig zag is wrong",p9,zz.next());
		
	}
}
