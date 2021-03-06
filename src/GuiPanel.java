import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
	
	JLabel theMode;
	
	public GuiPanel() {
		
		setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		
	    mode = "music";
	    theMode = new JLabel();
	    theMode.setText("Mode: " + mode+"  ");
	    top.add(theMode, BorderLayout.EAST);
	    
	    JLabel title = new JLabel();
	    title.setFont(new Font("Calibri", Font.PLAIN, 40));
	    
	    title.setText("  AirTable");
	    top.add(title, BorderLayout.WEST);
	    add(top, BorderLayout.NORTH);
	    
	    ImageIcon icon = new ImageIcon("Images"+File.separator+"logo.jpg"); 
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		add(thumb, BorderLayout.CENTER);
		
	    
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
			            GuiPanel.this.mix.mute();
			            GuiPanel.this.music.play();
			        }
			        GuiPanel.this.updateMode();
			    }
			    
			    if (mode.equals("music")) {
    				if (KeyEvent.getKeyText(e.getKeyCode()).equals("Up")) {
    					GuiPanel.this.music.moveUp();
    					
    				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")) {
    					GuiPanel.this.music.moveDown();
    					
    				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("H")) {
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
    					
    				} else if (KeyEvent.getKeyText(e.getKeyCode()).equals("P")) {
    					GuiPanel.this.music.play();
    					
    				} 
			    } else if (mode.equals("mix")) {
			        if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
			            GuiPanel.this.mix.back();
			        }
			    }
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (mode.equals("music")) {
					if (KeyEvent.getKeyText(e.getKeyCode()).equals("H")) {
						GuiPanel.this.music.play();
						
					}
				}
			}
		});
		setFocusable(true);
		music = new Music();
		mix = new Mix();
	}
	
	void updateMode() {
		theMode.setText("Mode: " + mode+"  ");
		
	}
}
