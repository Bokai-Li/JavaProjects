package a9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PixelInspectorTool implements Tool, ActionListener{

	private PixelInspectorUI ui;
	private ImageEditorModel model;
	private boolean zoomed = false;
	private boolean magOn =false;
	
	public PixelInspectorTool(ImageEditorModel model) {
		this.model = model;
		ui = new PixelInspectorUI();
		ui.addActionListener(this);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!magOn)
		{	
			try {
				ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
			}
			catch (Exception ex) {
				// Click may have been out of bounds. Do nothing in this case.
			}
		}
		else
		{
			model.zoom(e.getX(), e.getY());
			zoomed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Pixel Inspector";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(!magOn)
		{
			try {
				ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
			}
			catch (Exception ex) {
				// Click may have been out of bounds. Do nothing in this case.
			}
		}
		else
		{
			model.zoom(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(magOn)
		{
			if(zoomed)
			{
				model.unzoom();
				zoomed = false;
			}
			magOn=false;
		}else {
			magOn=true;
		}
		
	}
	
}
