package a9;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PaintBrushTool implements Tool {

	private PaintBrushToolUI ui;
	private ImageEditorModel model;
	
	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!ui.getPickOn())
			model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize(),ui.getOpacity());
		else
		{
			try {
			ui.setColor(model.getCurrent().getPixel(e.getX(), e.getY()).getRed(), 
					model.getCurrent().getPixel(e.getX(), e.getY()).getGreen(), 
					model.getCurrent().getPixel(e.getX(), e.getY()).getBlue());
			}
			catch(IllegalArgumentException ex)
			{
				
			}
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
	public void mouseDragged(MouseEvent e) {
		if(!ui.getPickOn())
			model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize(),ui.getOpacity());
		else
		{
			try {
			ui.setColor(model.getCurrent().getPixel(e.getX(), e.getY()).getRed(), 
					model.getCurrent().getPixel(e.getX(), e.getY()).getGreen(), 
					model.getCurrent().getPixel(e.getX(), e.getY()).getBlue());
			}
			catch(IllegalArgumentException ex)
			{
				
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

}
