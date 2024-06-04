package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURl = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {
        soundURl[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURl[1] = getClass().getResource("/sound/coin.wav");
        soundURl[2] = getClass().getResource("/sound/powerup.wav");
        soundURl[3] = getClass().getResource("/sound/unlock.wav");
        soundURl[4] = getClass().getResource("/sound/fanfare.wav");
        soundURl[5] = getClass().getResource("/sound/hitmonster.wav");
        soundURl[6] = getClass().getResource("/sound/receivedamage.wav");
        soundURl[7] = getClass().getResource("/sound/swingweapon.wav");
        soundURl[8] = getClass().getResource("/sound/levelup.wav");
        soundURl[9] = getClass().getResource("/sound/cursor.wav");
        soundURl[10] = getClass().getResource("/sound/burning.wav");
        soundURl[11] = getClass().getResource("/sound/cuttree.wav");
        soundURl[12] = getClass().getResource("/sound/gameover.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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

    public void checkVolume(){

        switch (volumeScale){

            case 0: volume = -80f; break;
            case 1: volume =  -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f;break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}


