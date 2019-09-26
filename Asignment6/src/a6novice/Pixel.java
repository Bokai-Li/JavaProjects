package a6novice;

public abstract interface Pixel
{
  public abstract double getRed();
  
  public abstract double getBlue();
  
  public abstract double getGreen();
  
  public abstract double getIntensity();
  
  public abstract char getChar();
  
  public abstract Pixel blend(Pixel paramPixel, double paramDouble);
  
  public abstract Pixel lighten(double paramDouble);
  
  public abstract Pixel darken(double paramDouble);
  
  public abstract boolean equals(Pixel paramPixel);
}
