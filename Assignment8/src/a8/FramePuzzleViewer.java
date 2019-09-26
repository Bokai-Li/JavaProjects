package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzleViewer extends JPanel implements MouseListener, KeyListener{
	private JPanel panel= new JPanel();
	private PictureView pv[][] = new PictureView[5][5];
	private int wx = 4, wy = 4;
	private Picture whitepic;
	
	public FramePuzzleViewer(Picture p)
	{
		setLayout(new GridLayout(5,5));
		for(int pp = 0; pp < 5; pp++)
			for(int qq = 0; qq < 5; qq++)
			{
				pv[pp][qq] = new PictureView(this.dividePicture(p, pp, qq).createObservable());
				add(pv[pp][qq]);
				pv[pp][qq].addMouseListener(this);
			}
		addKeyListener(this);
		setFocusable(true);
	}

	public Picture dividePicture(Picture pic, int p, int q)
	{
		int height, width;
		if(p<4)
			height = pic.getHeight()/5;
		else
			height = pic.getHeight()/5 + pic.getHeight()%5;
		
		if(q<4)
			width = pic.getWidth()/5;
		else
			width = pic.getWidth()/5 + pic.getWidth()%5;
	
		Picture smallPic = new PictureImpl(width, height);
		whitepic = new PictureImpl(width, height);
		if(p==4 && q == 4)
		{
			for(int a = 0; a < width; a++)
				for(int b = 0; b < height; b++)
				{
					smallPic.setPixel(a,b,new GrayPixel(1.0));
					whitepic.setPixel(a,b,new GrayPixel(1.0));
				}
		}
		else
			for(int a = 0; a < width; a++)
				for(int b = 0; b < height; b++)
				{
					smallPic.setPixel(a,b,pic.getPixel(q*(pic.getWidth()/5)+a, p*(pic.getHeight()/5)+b));
				}
		
		return smallPic;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		try {
			if(e.getSource() == pv[wx-1][wy])
			{
				wxminus();
			}
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
		}
		try{
			if(e.getSource() == pv[wx+1][wy])
			{
				wxplus();
			}
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
		}
		try {	
		if(e.getSource() == pv[wx][wy-1])
		{
			wyminus();
		}
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
		}
		try{
		if(e.getSource() == pv[wx][wy+1])
		{
			wyplus();
		}
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.requestFocus();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode()==39)
		 {
			
			//Right arrow key code
			if (wy != 4)
			{
				wyplus();
			}
		 }
		else if (e.getKeyCode()==37)
		{
			//Left arrow key code
			if (wy != 0)
			{
				wyminus();
			}
		}
		else if (e.getKeyCode() == 38)
		{
			//Up arrow key code
			if (wx != 0)
			{
				wxminus();
			}
		}
		else if (e.getKeyCode() == 40)
		{
			//Down arrow key code
			if (wx != 4)
			{
				wxplus();
			}
		}	
		
	}
	
	private void wxplus()
	{
		pv[wx][wy].setPicture(pv[wx+1][wy].getPicture());
		pv[wx+1][wy].setPicture(whitepic.createObservable());
		wx++;
	}
	private void wxminus()
	{
		pv[wx][wy].setPicture(pv[wx-1][wy].getPicture());
		pv[wx-1][wy].setPicture(whitepic.createObservable());
		wx--;
	}
	private void wyplus()
	{
		pv[wx][wy].setPicture(pv[wx][wy+1].getPicture());
		pv[wx][wy+1].setPicture(whitepic.createObservable());
		wy++;
	}
	private void wyminus()
	{
		pv[wx][wy].setPicture(pv[wx][wy-1].getPicture());
		pv[wx][wy-1].setPicture(whitepic.createObservable());
		wy--;
	}
	public void keyReleased(KeyEvent e) {
	}
}
