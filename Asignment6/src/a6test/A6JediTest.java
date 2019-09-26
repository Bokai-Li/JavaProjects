package a6test;

import a6jedi.Picture;
import a6jedi.PictureImpl;
import a6jedi.Pixel;
import java.util.Iterator;
import org.junit.Assert;

public class A6JediTest
{
  public A6JediTest() {}
  
  private static final Pixel RED = new a6jedi.ColorPixel(1.0D, 0.0D, 0.0D);
  private static final Pixel GREEN = new a6jedi.ColorPixel(0.0D, 1.0D, 0.0D);
  private static final Pixel BLUE = new a6jedi.ColorPixel(0.0D, 0.0D, 1.0D);
  private static final Pixel WHITE = new a6jedi.GrayPixel(1.0D);
  private static final Pixel BLACK = new a6jedi.GrayPixel(0.0D);
  
  public static String[] getTestNames() {
    String[] test_names = new String[3];
    
    test_names[0] = "testZigZagSquare";
    test_names[1] = "testZigZagPortrait";
    test_names[2] = "testZigZagLandscape";
    return test_names;
  }
  
  public static String[] getTestDescriptions() {
    String[] test_descriptions = new String[3];
    
    test_descriptions[0] = "Tests ZigZagIterator for square pictures with odd and even side lengths.";
    test_descriptions[1] = "Tests ZigZagIterator for width < height pictures with odd and even side lengths; last move is down.";
    test_descriptions[2] = "Tests ZigZagIterator for width > height pictures; last move is to right.";
    return test_descriptions;
  }
  
  @org.junit.Test
  public void testZigZagSquare() {
    Picture s1 = new PictureImpl(2, 2);
    s1.setPixel(0, 0, RED);
    s1.setPixel(1, 0, BLUE);
    s1.setPixel(0, 1, GREEN);
    s1.setPixel(1, 1, WHITE);
    
    Iterator<Pixel> s1_zigzagiterator = s1.zigzag();
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", WHITE, 
      s1_zigzagiterator.next());
    Assert.assertFalse(s1_zigzagiterator.hasNext());
    
    Picture s2 = new PictureImpl(3, 3);
    s2.setPixel(0, 0, RED);
    s2.setPixel(1, 0, WHITE);
    s2.setPixel(2, 0, GREEN);
    s2.setPixel(0, 1, BLACK);
    s2.setPixel(1, 1, BLUE);
    s2.setPixel(2, 1, GREEN);
    s2.setPixel(0, 2, BLACK);
    s2.setPixel(1, 2, BLUE);
    s2.setPixel(2, 2, RED);
    
    Iterator<Pixel> s2_zigzagiterator = s2.zigzag();
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", WHITE, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLACK, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLACK, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s2_zigzagiterator.next());
    Assert.assertFalse(s2_zigzagiterator.hasNext());
  }
  
  @org.junit.Test
  public void testZigZagPortrait() {
    Picture s1 = new PictureImpl(3, 4);
    s1.setPixel(0, 0, RED);
    s1.setPixel(1, 0, WHITE);
    s1.setPixel(2, 0, GREEN);
    s1.setPixel(0, 1, BLACK);
    s1.setPixel(1, 1, BLUE);
    s1.setPixel(2, 1, GREEN);
    s1.setPixel(0, 2, BLACK);
    s1.setPixel(1, 2, BLUE);
    s1.setPixel(2, 2, RED);
    s1.setPixel(0, 3, WHITE);
    s1.setPixel(1, 3, BLUE);
    s1.setPixel(2, 3, RED);
    
    Iterator<Pixel> s1_zigzagiterator = s1.zigzag();
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", WHITE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLACK, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLACK, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", WHITE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s1_zigzagiterator.next());
    Assert.assertFalse(s1_zigzagiterator.hasNext());
    
    Picture s2 = new PictureImpl(2, 3);
    s2.setPixel(0, 0, RED);
    s2.setPixel(1, 0, BLUE);
    s2.setPixel(0, 1, GREEN);
    s2.setPixel(1, 1, WHITE);
    s2.setPixel(0, 2, BLACK);
    s2.setPixel(1, 2, RED);
    
    Iterator<Pixel> s2_zigzagiterator = s2.zigzag();
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLACK, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", WHITE, 
      s2_zigzagiterator.next());
    
    Assert.assertTrue(s2_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s2_zigzagiterator.next());
    Assert.assertFalse(s2_zigzagiterator.hasNext());
  }
  
  @org.junit.Test
  public void testZigZagLandscape() {
    Picture s1 = new PictureImpl(4, 2);
    s1.setPixel(0, 0, RED);
    s1.setPixel(1, 0, BLUE);
    s1.setPixel(2, 0, GREEN);
    s1.setPixel(3, 0, WHITE);
    s1.setPixel(0, 1, BLUE);
    s1.setPixel(1, 1, GREEN);
    s1.setPixel(2, 1, BLACK);
    s1.setPixel(3, 1, RED);
    
    Iterator<Pixel> s1_zigzagiterator = s1.zigzag();
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLUE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", GREEN, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", WHITE, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", BLACK, 
      s1_zigzagiterator.next());
    
    Assert.assertTrue(s1_zigzagiterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the zigzag iterator is not the expected pixel", RED, 
      s1_zigzagiterator.next());
    Assert.assertFalse(s1_zigzagiterator.hasNext());
  }
}
