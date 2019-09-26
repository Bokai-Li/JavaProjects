package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PixelInspectorView extends JPanel implements MouseListener,MouseMotionListener{
	private PictureView picture_view;
	private Picture pic;
	private int x = 0,y = 0;
	private double r,g,b,br;
	private JLabel xl,yl,rl,Gl,Bl,Brl;

	
	public PixelInspectorView(Picture picture) {
		
		pic = picture;
		
		setLayout(new BorderLayout());
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		picture_view.addMouseMotionListener(this);
		add(picture_view, BorderLayout.CENTER);
		

		JPanel infoDisplay = new JPanel();
		infoDisplay.setLayout(new GridLayout(6,0));
		xl = new JLabel("X: ");
		yl = new JLabel("Y: ");
		rl = new JLabel("Red: ");
		Gl = new JLabel("Green: ");
		Bl = new JLabel("Blue: ");
		Brl = new JLabel("Brightness: ");
		Brl.setPreferredSize(new Dimension(104,40));
		
		infoDisplay.add(xl);
		infoDisplay.add(yl);
		infoDisplay.add(rl);
		infoDisplay.add(Gl);
		infoDisplay.add(Bl);
		infoDisplay.add(Brl);
		
		add(infoDisplay,BorderLayout.WEST);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		updateInfo(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	private void updateInfo(MouseEvent e)
	{
		try
		{
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		x= e.getX();
		y= e.getY();
		r=pic.getPixel(x, y).getRed();
		g=pic.getPixel(x, y).getGreen();
		b=pic.getPixel(x, y).getBlue();
		br=pic.getPixel(x, y).getIntensity();
		xl.setText("X: "+x);
		yl.setText("Y: "+y);
		rl.setText("Red: "+formatter.format(r));
		Gl.setText("Green: "+formatter.format(g));
		Bl.setText("Blue: "+formatter.format(b));
		Brl.setText("Brightness: "+formatter.format(br));
		}
		catch(RuntimeException ex)
		{
			
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		updateInfo(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}	

}
