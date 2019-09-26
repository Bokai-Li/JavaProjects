package a9;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel{

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton Magnify;

	private double current_red=0.5,current_blue=0.5,current_green=0.5;
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		
		setLayout(new GridLayout(5,1));
		
		add(x_label);
		add(y_label);
		add(pixel_info);
		Magnify = new JButton("magnifying glass");
		add(Magnify);
		
	}
	
	public void setInfo(int x, int y, Pixel p) {
		current_red=p.getRed();
		current_blue=p.getBlue();
		current_green=p.getGreen();
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));		
	}



	public double getCurrentRed()
	
	{
		return current_red;
	}

	public double getCurrentBlue()
	{
		return current_blue;
	}
	public double getCurrentGreen()
	{
		return current_green;
	}

	public void addActionListener(ActionListener l) {
			Magnify.addActionListener(l);
		}
}
