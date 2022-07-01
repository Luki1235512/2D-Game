package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    private Clip clip;
    private final URL[] soundURL = new URL[30];
    private FloatControl floatControl;
    private int volumeScale = 3;
    private float volume;

    public Sound() {

//         TODO: Add main theme
        soundURL[0] = getClass().getResource("/sound/adventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/hitMonster.wav");
        soundURL[6] = getClass().getResource("/sound/receiveDamage.wav");
        soundURL[7] = getClass().getResource("/sound/swingWeapon.wav");
        soundURL[8] = getClass().getResource("/sound/cursor.wav");
        soundURL[9] = getClass().getResource("/sound/cuttree.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception ignored) {}

    }

    public void play() {
        clip.start();
    }

    public  void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {

        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        floatControl.setValue(volume);
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public void decreaseVolumeScale() {
        this.volumeScale--;
    }

    public void increaseVolumeScale() {
        this.volumeScale++;
    }

}
