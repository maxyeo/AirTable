import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame;
		frame = new JFrame("AirTable");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiPanel gui = new GuiPanel();
		frame.add(gui);
		frame.setSize(420, 300);
		frame.setVisible(true);
	}
}
