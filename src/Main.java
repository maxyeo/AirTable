import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame;
		frame = new JFrame("AirTable");
		ImageIcon img = new ImageIcon("Images"+File.separator+"logo.jpg");
		frame.setIconImage(img.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiPanel gui = new GuiPanel();
		frame.add(gui);
		frame.setSize(567, 630);
		frame.setVisible(true);
	}
}
