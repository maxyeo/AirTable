import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GuiPanel extends JPanel {
	Music music;
	Mix mix;
	String mode;
	
	public GuiPanel() {
		

		ImageIcon icon = new ImageIcon("Images"+File.separator+"logo.jpg"); 
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		add(thumb);
		

	    mode = "music";
	    

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
				
			    if (KeyEvent.getKeyText(e.getKeyCode()).equals("M")) {
			        if (mode.equals("music")) {
			            mode = "mix";
			            GuiPanel.this.music.mute();
			            GuiPanel.this.mix.start();
			        } else {
			            mode = "music";
			        }
			    }
			    
			    if (mode.equals("music")) {
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
			    } else if (mode.equals("mix")) {
			        if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
			            GuiPanel.this.mix.back();
			            System.out.println("yo");
			        }
			    }
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (mode.equals("music")) {
					if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
						GuiPanel.this.music.play();
						
					}
				}
			}
		});
		setFocusable(true);
		music = new Music();
		mix = new Mix();
	}
}
