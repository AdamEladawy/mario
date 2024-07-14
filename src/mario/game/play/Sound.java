package mario.game.play;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    public static final int CURSOR = 9;
    public static final int GAME_OVER = 12;

    private URL[] soundURl = new URL[30];
    private FloatControl fc;
    private int volumeScale = 3;
    private Clip clip;

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
        soundURl[CURSOR] = getClass().getResource("/sound/cursor.wav");
        soundURl[10] = getClass().getResource("/sound/burning.wav");
        soundURl[11] = getClass().getResource("/sound/cuttree.wav");
        soundURl[GAME_OVER] = getClass().getResource("/sound/gameover.wav");
        soundURl[13] = getClass().getResource("/sound/stairs.wav");
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public URL[] getSoundURl() {
        return soundURl;
    }

    public void setSoundURl(URL[] soundURl) {
        this.soundURl = soundURl;
    }

    public FloatControl getFc() {
        return fc;
    }

    public void setFc(FloatControl fc) {
        this.fc = fc;
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public void setVolumeScale(int volumeScale) {
        if (volumeScale < 0)
            throw new IllegalArgumentException("Volume Scale cannot be less than 1");
        this.volumeScale = volumeScale;
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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

    public void checkVolume() {

        fc.setValue(switch (volumeScale) {
            case 0 -> -80f;
            case 1 -> -20f;
            case 2 -> -12f;
            case 3 -> -5f;
            case 4 -> 1f;
            case 5 -> 6f;
            default -> 0f;
        });
    }
}