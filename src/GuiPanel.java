import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class GuiPanel extends JPanel {
	Music music;
	
	public GuiPanel() {
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (KeyEvent.getKeyText(e.getKeyCode()).equals("Up")) {
					GuiPanel.this.music.moveUp();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")) {
					GuiPanel.this.music.moveDown();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
					GuiPanel.this.music.mute();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")) {
					GuiPanel.this.music.moreBeats();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left")) {
					GuiPanel.this.music.lessBeats();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {
					GuiPanel.this.music.drum();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {
					GuiPanel.this.music.theDrop();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("F")) {
					GuiPanel.this.music.playFastDrum();
					
				}  else if (KeyEvent.getKeyText(e.getKeyCode()).equals("1")) {
					GuiPanel.this.music.prevDrum();
					
				}  else if (KeyEvent.getKeyText(e.getKeyCode()).equals("2")) {
					GuiPanel.this.music.nextDrum();
					
				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("O")) {
					GuiPanel.this.music.play();
					
				} 
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
					GuiPanel.this.music.play();
					
				} 
			}
		});
		setFocusable(true);
		music = new Music();
	}
}
