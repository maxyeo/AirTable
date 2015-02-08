import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**Audio files generated from http://onlinetonegenerator.com/
 * 
 * @author Connie
 *
 */

public class Music {
	
	private final int MAX_BEATS = 5;
	
	private ArrayList<ArrayList<File>> pitches;
	private ArrayList<File> drums;
	private ArrayList<File> fastDrums;
	private int currentPitch;
	private int currentBeat;
	private int currentDrum;
	
	private Clip clip1;
	private AudioInputStream inputStream1;
	private Clip clip2;
	private AudioInputStream inputStream2;
	private Clip clip3;
	private AudioInputStream inputStream3;
	private Clip clip4;
	private AudioInputStream inputStream4;
	private Clip clip5;
	private AudioInputStream inputStream5;
	
	private boolean isFastPlaying;
	private boolean isMute;
	
	public Music() {
		this.currentPitch = 0;
		this.currentDrum= 0;
		this.pitches = new ArrayList<ArrayList<File>>();
		this.drums = new ArrayList<File>();
		this.fastDrums= new ArrayList<File>();
		this.currentBeat = 0;
		this.isFastPlaying = false;
		this.isMute = true;
		this.load();
	}
	
	private void load() {
			
			addPitches(261);
			addPitches(277);
			addPitches(293);
			addPitches(311);
			addPitches(330);
			addPitches(349);
			addPitches(370);
			addPitches(392);
			addPitches(415);
			addPitches(440);
			addPitches(466);
			addPitches(494);
			addPitches(523);
			
			addDrums();
			
			try {
				this.clip1 = AudioSystem.getClip();
				this.clip2 = AudioSystem.getClip();
				this.clip3 = AudioSystem.getClip();
				this.clip4 = AudioSystem.getClip();
				this.clip5 = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			
			reset1();
			reset2();
			resetDrum();
	}
	
	private void addPitches(int n) {
		this.pitches.add(new ArrayList<File>());
		for (int i = 0; i < MAX_BEATS + 1; i++) {
			int k = i + n;
			this.pitches.get(this.pitches.size() - 1).add(new File("Music"+File.separator+k+".wav"));
		}
	}
	
	private void addDrums() {
		File drumFolder = new File("Music"+File.separator+"Drums");
		for (File file : drumFolder.listFiles()) {
			if (!file.isDirectory()) {
				this.drums.add(file);
			}
		}
		
		drumFolder = new File("Music"+File.separator+"FastDrums");
		for (File file : drumFolder.listFiles()) {
			if (!file.isDirectory()) {
				this.fastDrums.add(file);
			}
		}
	}
	
	private void reset1() {
		try {
			this.clip1.stop();
			this.clip1 = AudioSystem.getClip();
			this.inputStream1 = AudioSystem.getAudioInputStream(this.pitches.get(currentPitch).get(0));
			this.clip1.open(inputStream1);
			if (!this.isMute) {
				this.clip1.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void reset2() {
		try {
			this.clip2.stop();
			this.clip2 = AudioSystem.getClip();
			this.inputStream2 = AudioSystem.getAudioInputStream(this.pitches.get(this.currentPitch).get(Math.abs(this.currentBeat)));
			this.clip2.open(inputStream2);
			if (!this.isMute) {
				this.clip2.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	void moveUp() {
		if (this.currentPitch < this.pitches.size() - 1) {
			this.currentPitch += 1;
			reset1();
			reset2();
		}
	}
	
	void moveDown() {
		if (this.currentPitch > 0) {
			this.currentPitch -= 1;
			reset1();
			reset2();
		}
	}
	
	void moreBeats() {
		if (this.currentBeat < this.pitches.get(this.currentPitch).size() - 1) {
			this.currentBeat += 1;
			reset2();
		}
	}
	
	void lessBeats() {
		if (this.currentBeat > -(this.pitches.get(this.currentPitch).size() - 1)) {
			this.currentBeat -= 1;
			reset2();
		}
	}
	
	void mute() {
		this.clip1.stop();
		this.clip2.stop();
		this.clip4.stop();
		this.clip5.stop();
		this.isMute = true;
	}
	
	void play() {
		this.clip1.loop(Clip.LOOP_CONTINUOUSLY);
		this.clip2.loop(Clip.LOOP_CONTINUOUSLY);
		if (this.isFastPlaying) {
			this.clip5.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			this.clip4.loop(Clip.LOOP_CONTINUOUSLY);
		}
		this.isMute = false;
	}
	
	
	void drum() {
		try {
				this.isFastPlaying = false;
				resetDrum();
		} catch (Exception e) {
				System.err.println(e.getMessage());
		}
	}
	
	void resetDrum() {
		try {
			this.clip4.stop();
			this.clip5.stop();
			this.clip4 = AudioSystem.getClip();
			this.inputStream4 = AudioSystem.getAudioInputStream(this.drums.get(this.currentDrum));
			this.clip4.open(inputStream4);
			this.clip5 = AudioSystem.getClip();
			this.inputStream5 = AudioSystem.getAudioInputStream(this.fastDrums.get(this.currentDrum));
			this.clip5.open(inputStream5);
			
			if (!this.isMute) {
				if (this.isFastPlaying) {
					this.clip5.loop(Clip.LOOP_CONTINUOUSLY);
				} else {
					this.clip4.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	void nextDrum() {
		this.currentDrum += 1;
		if (this.currentDrum >= this.drums.size()) {
			this.currentDrum = 0;
		}
		resetDrum();
	}
	
	void prevDrum() {
		this.currentDrum -= 1;
		if (this.currentDrum < 0) {
			this.currentDrum = this.drums.size() - 1;
		}
		resetDrum();
	}
	
	void playFastDrum() {
		try {
				this.isFastPlaying = true;
				resetDrum();
		} catch (Exception e) {
				System.err.println(e.getMessage());
		}
	}
	
	void theDrop() {
		try {
			this.clip3 = AudioSystem.getClip();
			this.inputStream3 = AudioSystem.getAudioInputStream(new File("Music"+File.separator+"drop.wav"));
			this.clip3.open(inputStream3);
			this.clip3.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}