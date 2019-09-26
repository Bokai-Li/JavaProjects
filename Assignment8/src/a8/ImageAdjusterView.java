package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ImageAdjusterView extends JPanel implements ChangeListener{

	private PictureView picture_view;
	private JSlider blur_slider;
	private JSlider saturation_slider;
	private JSlider brightness_slider;
	private Picture pic;
	private JPanel picturePanel;
	private boolean blurChanged = false;
	private int blurlevel=0;
	private Picture blurPicture;
	
	public ImageAdjusterView(Picture picture) {
		pic = picture;
		setLayout(new BorderLayout());
		JPanel slider_panel = new JPanel();
		
		JPanel blur_panel = new JPanel();
		JPanel saturation_panel = new JPanel();
		JPanel brightness_panel = new JPanel();
		
		slider_panel.setLayout(new GridLayout(3,1));
		blur_slider = new JSlider(0,5,0);
		blur_slider.setPreferredSize(new Dimension(590, 50));
		blur_slider.setMajorTickSpacing(1);
		blur_slider.setSnapToTicks(true);
		blur_slider.setPaintTicks(true);
		blur_slider.setPaintLabels(true);
		blur_panel.add(new JLabel("Blur: "));
		blur_panel.add(blur_slider);
		
		
		saturation_slider = new JSlider(-100,100,0);
		saturation_slider.setPreferredSize(new Dimension(550, 50));
		saturation_slider.setMajorTickSpacing(25);
		saturation_slider.setPaintTicks(true);
		saturation_slider.setPaintLabels(true);
		saturation_panel.add(new JLabel("Saturation: "));
		saturation_panel.add(saturation_slider);
		
		brightness_slider = new JSlider(-100,100,0);
		brightness_slider.setPreferredSize(new Dimension(550, 50));
		brightness_slider.setMajorTickSpacing(25);
		brightness_slider.setPaintTicks(true);
		brightness_slider.setPaintLabels(true);
		brightness_panel.add(new JLabel("Brightness: "));
		brightness_panel.add(brightness_slider);
		slider_panel.add(blur_panel);
		slider_panel.add(saturation_panel);
		slider_panel.add(brightness_panel);
		add(slider_panel, BorderLayout.SOUTH);
		
		picture_view = new PictureView(pic.createObservable());
		picturePanel = new JPanel();
		picturePanel.add(picture_view);
		add(picturePanel, BorderLayout.CENTER);
		
		blur_slider.addChangeListener(this);
		saturation_slider.addChangeListener(this);
		brightness_slider.addChangeListener(this);
		
		blurPicture = new PictureImpl(picture.getWidth(),picture.getHeight());
		for(int r = 0; r<picture.getHeight();r++)
			for(int c = 0; c<picture.getWidth(); c++)
					blurPicture.setPixel(c, r, picture.getPixel(c, r));
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Picture pic2 = blur(pic,blur_slider.getValue());	
		Picture pic3 = brightness(pic2,brightness_slider.getValue());
		Picture pic4 = saturate(pic3,saturation_slider.getValue());
		
		picture_view.setPicture(pic4.createObservable());
	}
	
	private Picture blur(Picture Original, int blur)
	{
		Picture newpic = new PictureImpl(Original.getWidth(),Original.getHeight());
	
		if(blur != blurlevel)
		{
			blurlevel = blur;
		
			if(blur_slider.getValue()!=0)
			{
				for(int r = 0; r<Original.getHeight();r++)
					for(int c = 0; c<Original.getWidth(); c++)
					{
						double sumr=0,sumg=0,sumb=0,aver=0,aveg=0,aveb=0;
						int count =0;
						for(int row = 0-blur; row <= blur; row++)
							for(int col = 0-blur; col <= blur; col++)
							{
								try {
									sumr = sumr + Original.getPixel(c+col,r+row).getRed();
									sumg = sumg + Original.getPixel(c+col,r+row).getGreen();
									sumb = sumb + Original.getPixel(c+col,r+row).getBlue();
									count++;
								}catch(RuntimeException e)
								{}
								aver=sumr/count;
								aveg=sumg/count;
								aveb=sumb/count;
								Pixel pixel = new ColorPixel(aver,aveg,aveb);
								newpic.setPixel(c,r,pixel);
								blurPicture.setPixel(c, r, pixel);
							}	
					}
			}
			else
			{
				for(int r = 0; r<Original.getHeight();r++)
					for(int c = 0; c<Original.getWidth(); c++)
							blurPicture.setPixel(c, r, Original.getPixel(c, r));
				return Original;
			}
			
		}
		else
		{
			return blurPicture;
		}
		return newpic;
		
		}
	private Picture brightness(Picture Original, double bri)
	{
		Picture newpic = new PictureImpl(Original.getWidth(),Original.getHeight());
		if(bri>0)
			for(int r = 0; r<Original.getHeight();r++)
				for(int c = 0; c<Original.getWidth(); c++)
				{
					newpic.setPixel(c,r,lighten(Original.getPixel(c, r),bri/100));
				}
		else if (bri<0)
		{
			for(int r = 0; r<Original.getHeight();r++)
				for(int c = 0; c<Original.getWidth(); c++)
				{
					newpic.setPixel(c,r,darken(Original.getPixel(c, r),0-bri/100));
				}
		}
		else
		{
			return Original;
		}
			
		return newpic;
	}
	
	private Picture saturate(Picture Original, double sat)
	{
		Picture newpic = new PictureImpl(Original.getWidth(),Original.getHeight());
		if(sat>0)
			for(int r = 0; r<Original.getHeight();r++)
				for(int c = 0; c<Original.getWidth(); c++)
				{
					
					newpic.setPixel(c,r,positiveSaturation(Original.getPixel(c, r).getIntensity(),sat,Original.getPixel(c,r)));
				}
		else if (sat<0)
		{
			for(int r = 0; r<Original.getHeight();r++)
				for(int c = 0; c<Original.getWidth(); c++)
				{
					newpic.setPixel(c,r,negtiveSaturation(Original.getPixel(c, r).getIntensity(),sat,Original.getPixel(c,r)));
				}
		}
		else
		{
			return Original;
		}
			
		return newpic;
	}
	
	private Pixel negtiveSaturation(double b,double f, Pixel p)
	{
		Pixel rp;
		double rRed, rGreen, rBlue;
		rRed = p.getRed()*(1.0 + (f/100))-(b*f/100);
		rGreen = p.getGreen()*(1.0 + (f/100))-(b*f/100);
		rBlue = p.getBlue()*(1.0 + (f/100))-(b*f/100);
		rp = new ColorPixel(rRed,rGreen,rBlue);
		return rp;
	}
	
	private Pixel positiveSaturation(double b,double f, Pixel p)
	{
		Pixel rp;
		double rRed, rGreen, rBlue,a;
		if(p.getRed() >= p.getGreen() && p.getRed()>= p.getBlue())
			a = p.getRed();
			else if(p.getGreen()>=p.getBlue())
				a=p.getGreen();
			else
				a=p.getBlue();
		
		rRed = p.getRed()*(a + (1.0-a) * (f/100))/a;
		rGreen = p.getGreen()*(a + (1.0-a) * (f/100))/a;
		rBlue = p.getBlue()*(a + (1.0-a) * (f/100))/a;
		rp = new ColorPixel(rRed,rGreen,rBlue);
		return rp;
	}
	
	private Pixel blend(Pixel ori, Pixel p, double weight) {
		if(p == null)
			throw new RuntimeException("Pixel is null");
		Pixel rPixel;
		double rRed, rGreen, rBlue;
		if(weight<0.0 || weight>1.0)
			throw new RuntimeException("Weight value is out of bound.");
		rRed= weight*ori.getRed() + (1.0-weight)*p.getRed();
		rGreen= weight*ori.getGreen() + (1.0-weight)*p.getGreen();
		rBlue= weight*ori.getBlue() + (1.0-weight)*p.getBlue();
		rPixel = new ColorPixel(rRed,rGreen,rBlue);
		return rPixel;
	}
	public Pixel lighten(Pixel ori, double factor) {
		Pixel white = new ColorPixel(1.0,1.0,1.0);
		Pixel rPixel = blend(white, ori, factor);
		return rPixel;
	}
	public Pixel darken(Pixel ori,double factor) {
		Pixel black = new ColorPixel(0,0,0);
		Pixel rPixel = blend(black, ori, factor);
		return rPixel;
	}
}
