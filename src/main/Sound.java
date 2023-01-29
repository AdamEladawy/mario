package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURl = new URL[30];

    public Sound() {
        soundURl[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURl[1] = getClass().getResource("/sound/coin.wav");
        soundURl[2] = getClass().getResource("/sound/powerup.wav");
        soundURl[3] = getClass().getResource("/sound/unlock.wav");
        soundURl[4] = getClass().getResource("/sound/fanfare.wav");
        soundURl[5] = getClass().getResource("/sound/hitmonster.wav");
        soundURl[6] = getClass().getResource("/sound/receivedamage.wav");
        soundURl[7] = getClass().getResource("/sound/swingweapon.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
