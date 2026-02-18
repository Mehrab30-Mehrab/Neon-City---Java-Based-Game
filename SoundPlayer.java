package audio;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class SoundPlayer {

    public static void playLoop(String filepath) {
        new Thread(() -> {
            try {
                // Load resource from classpath
                InputStream audioSrc = SoundPlayer.class.getResourceAsStream(filepath);
                if (audioSrc == null) {
                    System.out.println("ERROR: File not found: " + filepath);
                    return;
                }

                AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                        new BufferedInputStream(audioSrc)
                );

                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}