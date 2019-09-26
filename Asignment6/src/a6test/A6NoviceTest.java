package a6test;

import a6novice.Coordinate;
import a6novice.Picture;
import a6novice.SubPicture;
import java.util.Iterator;
import org.junit.Assert;

public class A6NoviceTest
{
  public A6NoviceTest() {}
  
  private static final a6novice.Pixel RED = new a6novice.ColorPixel(1.0D, 0.0D, 0.0D);
  private static final a6novice.Pixel GREEN = new a6novice.ColorPixel(0.0D, 1.0D, 0.0D);
  private static final a6novice.Pixel BLUE = new a6novice.ColorPixel(0.0D, 0.0D, 1.0D);
  private static final a6novice.Pixel WHITE = new a6novice.GrayPixel(1.0D);
  private static final a6novice.Pixel BLACK = new a6novice.GrayPixel(0.0D);
  
  public static String[] getTestNames() {
    String[] test_names = new String[4];
    
    test_names[0] = "testGetSetPixelByCoord";
    test_names[1] = "testExtractSubpictureByCoord";
    test_names[2] = "testPixelIteratorBasic";
    test_names[3] = "testPixelIteratorSetterError";
    return test_names;
  }
  
  public static String[] getTestDescriptions() {
    String[] test_descriptions = new String[4];
    
    test_descriptions[0] = "Ensure getters and setters using coordinate object references work.";
    test_descriptions[1] = "Ensure proper extraction of subpicture given inclusive coordinates on any pair of diagonal corners.";
    test_descriptions[2] = "Ensure proper function of hasNext and next methods for PixelIterator.";
    test_descriptions[3] = "Ensure use of remove method for PixelIterator results in UnsupportedOperationException.";
    return test_descriptions;
  }
  
  @org.junit.Test
  public void testGetSetPixelByCoord() {
    Picture source_1 = initializeSource1();
    Coordinate source_a = new Coordinate(2, 1);
    Coordinate source_b = new Coordinate(2, 2);
    Coordinate source_c = new Coordinate(3, 1);
    Coordinate source_d = new Coordinate(3, 2);
    
    SubPicture subpic_1 = new a6novice.SubPictureImpl(source_1, 2, 1, 2, 2);
    Coordinate sub_a = new Coordinate(0, 0);
    Coordinate sub_b = new Coordinate(0, 1);
    Coordinate sub_c = new Coordinate(1, 0);
    Coordinate sub_d = new Coordinate(1, 1);
    

    Assert.assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y", 
      source_1.getPixel(source_a), source_1.getPixel(2, 1));
    Assert.assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y", 
      source_1.getPixel(source_b), source_1.getPixel(2, 2));
    Assert.assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y", 
      subpic_1.getPixel(sub_c), subpic_1.getPixel(1, 0));
    Assert.assertEquals("Pixel retrieved by coordinate does not match pixel retrieved by x and y", 
      subpic_1.getPixel(sub_d), subpic_1.getPixel(1, 1));
    
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_a), subpic_1.getPixel(sub_a));
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_b), subpic_1.getPixel(sub_b));
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_c), subpic_1.getPixel(sub_c));
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_d), subpic_1.getPixel(sub_d));
    
    subpic_1.setPixel(sub_a, RED);
    Assert.assertEquals("Pixel retrieved using coordinate after setting does not match expected value", 
      RED, subpic_1.getPixel(sub_a));
    Assert.assertEquals("Pixel retrieved using x and y after setting does not match expected value", 
      RED, subpic_1.getPixel(0, 0));
    Assert.assertEquals("Pixel in source not changed after setting through subpicture", 
      RED, source_1.getPixel(source_a));
  }
  
  @org.junit.Test
  public void testExtractSubpictureByCoord() {
    Picture source_1 = initializeSource1();
    Coordinate source_a = new Coordinate(2, 1);
    Coordinate source_b = new Coordinate(2, 2);
    Coordinate source_c = new Coordinate(3, 1);
    Coordinate source_d = new Coordinate(3, 2);
    
    SubPicture subpic_1a = source_1.extract(source_a, source_d);
    Coordinate sub1a_a = new Coordinate(0, 0);
    Coordinate sub1a_b = new Coordinate(0, 1);
    Coordinate sub1a_c = new Coordinate(1, 0);
    Coordinate sub1a_d = new Coordinate(1, 1);
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_a), subpic_1a.getPixel(sub1a_a));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_b), subpic_1a.getPixel(sub1a_b));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_c), subpic_1a.getPixel(sub1a_c));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_d), subpic_1a.getPixel(sub1a_d));
    
    SubPicture subpic_1b = source_1.extract(source_b, source_c);
    Coordinate sub1b_a = new Coordinate(0, 0);
    Coordinate sub1b_b = new Coordinate(0, 1);
    Coordinate sub1b_c = new Coordinate(1, 0);
    Coordinate sub1b_d = new Coordinate(1, 1);
    Assert.assertEquals("Pixel retrieved from subpicture using top-right/bottom-left coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_a), subpic_1b.getPixel(sub1b_a));
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_b), subpic_1b.getPixel(sub1b_b));
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_c), subpic_1b.getPixel(sub1b_c));
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_d), subpic_1b.getPixel(sub1b_d));
    
    SubPicture subpic_1c = source_1.extract(source_c, source_b);
    Coordinate sub1c_a = new Coordinate(0, 0);
    Coordinate sub1c_b = new Coordinate(0, 1);
    Coordinate sub1c_c = new Coordinate(1, 0);
    Coordinate sub1c_d = new Coordinate(1, 1);
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_a), subpic_1c.getPixel(sub1c_a));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_b), subpic_1c.getPixel(sub1c_b));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_c), subpic_1c.getPixel(sub1c_c));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_d), subpic_1c.getPixel(sub1c_d));
    
    SubPicture subpic_1d = source_1.extract(source_d, source_a);
    Coordinate sub1d_a = new Coordinate(0, 0);
    Coordinate sub1d_b = new Coordinate(0, 1);
    Coordinate sub1d_c = new Coordinate(1, 0);
    Coordinate sub1d_d = new Coordinate(1, 1);
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_a), subpic_1d.getPixel(sub1d_a));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_b), subpic_1d.getPixel(sub1d_b));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_c), subpic_1d.getPixel(sub1d_c));
    Assert.assertEquals("Pixel retrieved from subpicture using top-left/bottom-right coordinates does not match expected pixel value from source", 
      source_1.getPixel(source_d), subpic_1d.getPixel(sub1d_d));
    
    SubPicture subpic_2 = subpic_1a.extract(sub1a_d, sub1a_d);
    Coordinate sub2_d = new Coordinate(0, 0);
    Assert.assertEquals("Pixel retrieved from subpicture does not match expected pixel value from source", 
      source_1.getPixel(source_d), subpic_2.getPixel(sub2_d));
  }
  
  @org.junit.Test
  public void testPixelIteratorBasic() {
    Picture source_1 = initializeSource1();
    Iterator<a6novice.Pixel> s1_iterator = source_1.iterator();
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      RED, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      WHITE, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      GREEN, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      BLACK, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      GREEN, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      BLACK, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      BLUE, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      RED, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      BLUE, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      RED, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      WHITE, s1_iterator.next());
    
    Assert.assertTrue(s1_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      GREEN, s1_iterator.next());
    Assert.assertFalse(s1_iterator.hasNext());
    
    Picture source_2 = initializeSource2();
    Iterator<a6novice.Pixel> s2_iterator = source_2.iterator();
    
    Assert.assertTrue(s2_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      RED, s2_iterator.next());
    
    Assert.assertTrue(s2_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      BLUE, s2_iterator.next());
    
    Assert.assertTrue(s2_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      GREEN, s2_iterator.next());
    
    Assert.assertTrue(s2_iterator.hasNext());
    Assert.assertEquals("The next pixel retrieved by the pixel iterator is not the expected pixel", 
      WHITE, s2_iterator.next());
    Assert.assertFalse(s2_iterator.hasNext());
  }
  
  @org.junit.Test
  public void testPixelIteratorSetterError() {
    Picture source_1 = initializeSource1();
    Iterator<a6novice.Pixel> s1_iterator = source_1.iterator();
    try
    {
      s1_iterator.remove();
      Assert.fail("remove on PixelIterator should throw UnsupportedOperationException");
    }
    catch (UnsupportedOperationException localUnsupportedOperationException) {}catch (Exception e) {
      Assert.fail("Expected UnsupportedOperationException but detected some other kind of exception");
    }
  }
  
  private Picture initializeSource1() {
    Picture s1 = new a6novice.PictureImpl(4, 3);
    
    s1.setPixel(0, 0, RED);
    s1.setPixel(1, 0, WHITE);
    s1.setPixel(2, 0, GREEN);
    s1.setPixel(3, 0, BLACK);
    s1.setPixel(0, 1, GREEN);
    s1.setPixel(1, 1, BLACK);
    s1.setPixel(2, 1, BLUE);
    s1.setPixel(3, 1, RED);
    s1.setPixel(0, 2, BLUE);
    s1.setPixel(1, 2, RED);
    s1.setPixel(2, 2, WHITE);
    s1.setPixel(3, 2, GREEN);
    return s1;
  }
  
  private Picture initializeSource2() {
    Picture s2 = new a6novice.PictureImpl(2, 2);
    
    s2.setPixel(0, 0, RED);
    s2.setPixel(1, 0, BLUE);
    s2.setPixel(0, 1, GREEN);
    s2.setPixel(1, 1, WHITE);
    return s2;
  }
}
