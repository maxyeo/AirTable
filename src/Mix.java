import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**Audio files generated from http://onlinetonegenerator.com/
 * 
 * @author Max
 *
 */

public class Mix {
    
    private Clip clip1;
    private AudioInputStream inputStream1;
    private Clip clip2;
    private AudioInputStream inputStream2;
    
    public Mix() {
        this.load();
    }
    
    private void load() {
        try {
            this.clip1 = AudioSystem.getClip();
            this.inputStream1 = AudioSystem.getAudioInputStream(new File("Music"+File.separator+"sil.wav"));
            this.clip1.open(inputStream1);
            this.clip1.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void back() {
        try {
            this.clip2 = AudioSystem.getClip();
            this.inputStream2 = AudioSystem.getAudioInputStream(new File("Music"+File.separator+"sil2.wav"));
            this.clip2.open(inputStream2);
            this.clip2.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}