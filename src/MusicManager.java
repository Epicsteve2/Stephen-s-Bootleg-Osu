/* Class for managing the music*/

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicManager {
    private Clip clip;
    private AudioInputStream audioInput;
    private Handler handler;

    // Checks if the file exists
    public MusicManager(String location, Handler handler) {
        this.handler = handler;
        File musicPath = new File (location);
        try {
            if (musicPath.exists()) {
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method for playing the music
    void playMusic () {
        clip.start();
    }

    // Method for stopping the music, then setting the time back to 0
    void stopMusic () {
        clip.setMicrosecondPosition(0);
        clip.stop();
    }
}
