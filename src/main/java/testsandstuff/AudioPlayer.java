package testsandstuff;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer
{
    final private String hit_sound_path;
    final private String miss_sound_path;

    // constructor to initialize paths
    public AudioPlayer()
    {
        this.hit_sound_path = "C:\\Users\\koczo\\IdeaProjects\\Battleships\\src\\main\\resources\\hit_sound.snd";
        this.miss_sound_path = "C:\\Users\\koczo\\IdeaProjects\\Battleships\\src\\main\\resources\\splash2.wav";
    }

    public void playHitSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(hit_sound_path).getAbsoluteFile());

        // create clip reference
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public void playMissSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(miss_sound_path).getAbsoluteFile());

        // create clip reference
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

}
