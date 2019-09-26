package a9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener, ActionListener{

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private JSlider brush_size;
	private PictureView color_preview;
	private JSlider opacity_slider;
	private boolean pickOn=false;
	private JLabel pickStateLabel;
	public PaintBrushToolUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel opacity_slider_panel = new JPanel();
		JLabel opacity_label = new JLabel("Opacity: ");
		opacity_slider_panel.setLayout(new BorderLayout());
		opacity_slider_panel.add(opacity_label, BorderLayout.WEST);
		opacity_slider = new JSlider(0,100);
		opacity_slider.addChangeListener(this);
		opacity_slider_panel.add(opacity_slider, BorderLayout.CENTER);
		
		JPanel brush_size_panel = new JPanel();
		JLabel brush_label = new JLabel("Brush size: ");
		brush_size_panel.setLayout(new BorderLayout());
		brush_size_panel.add(brush_label, BorderLayout.WEST);
		brush_size = new JSlider(5,50,5);
		brush_size.addChangeListener(this);
		brush_size_panel.add(brush_size, BorderLayout.CENTER);
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		
		pickStateLabel = new JLabel("Drawing");
		
		Dimension d = brush_label.getPreferredSize();
		opacity_label.getPreferredSize();
		green_label.setPreferredSize(d);
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		pickStateLabel.setPreferredSize(d);
		
		slider_panel.add(pickStateLabel);
		slider_panel.add(opacity_slider_panel);
		slider_panel.add(brush_size_panel);
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		String pickState = "Pick color";
		JButton Pick = new JButton(pickState);
		Pick.addActionListener(this);
		slider_panel.add(Pick);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);
		
		color_preview = new PictureView(new ObservablePictureImpl(50,50));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);
		
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				                 green_slider.getValue()/100.0,
				                 blue_slider.getValue()/100.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.suspendObservable();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {	
				preview_frame.setPixel(x, y, new ColorPixel(0.932,0.932,0.932));
			}
		}
		for (int x=preview_frame.getWidth()/2-(brush_size.getValue())/2+1; x<preview_frame.getWidth()/2+brush_size.getValue()/2-1; x++) {
			for (int y=preview_frame.getHeight()/2-(brush_size.getValue())/2+1; y<preview_frame.getHeight()/2+brush_size.getValue()/2-1; y++) {	
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeObservable();
		
		
	}
	public int getOpacity()
	{
		return opacity_slider.getValue();
	}
	public int getBrushSize() {
		return brush_size.getValue();
	}
	public Pixel getBrushColor() {
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
                green_slider.getValue()/100.0,
                blue_slider.getValue()/100.0);
		return p;
	}
	public void setColor(double r,double g,double b)
	{
		red_slider.setValue((int)(Math.round(r*100)));
		green_slider.setValue((int)(Math.round(g*100)));
		blue_slider.setValue((int)(Math.round(b*100)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(pickOn)
		{
			pickOn = false;
			pickStateLabel.setText("Drawing");
		}
		else
		{
			pickOn = true;
			pickStateLabel.setText("Picking a Color......");
		}
		
	}
	public boolean getPickOn()
	{
		return pickOn;
	}
	
}
