package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PixelInspector {

	public static void main(String[] args) throws IOException {
		Picture p = A8Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		PixelInspectorView Pixel_widget = new PixelInspectorView(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Pixel Inspector View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(Pixel_widget, BorderLayout.CENTER);
		
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
		
	}

}
