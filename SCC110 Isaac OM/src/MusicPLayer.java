import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicPLayer {
    private static File btf, backgroundmusic, bcf, pwf;
    private static Clip backgroundMusics;

    static {
        try {
            btf = new File("src/Audio/bounce.wav");
            backgroundmusic = new File("src/Audio/drumroll.wav");
            bcf = new File("src/Audio/fanfare.wav");
            pwf = new File("src/Audio/applause.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String fileName, boolean on) {
        if (!on)
            return;
        File fileToPlay = null;

        switch (fileName) {
            case "hitBall":
                fileToPlay = btf;
                break;
            case "clickplay":
                fileToPlay = bcf;
                break;
            case "wonSound":
                fileToPlay = pwf;
                break;
            default:
                System.out.println("Invalid filename");
                return;
        }

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileToPlay)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playBackGroundMusic(float volume) {
        try {
            if (backgroundMusics == null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(backgroundmusic);
                backgroundMusics = AudioSystem.getClip();
                backgroundMusics.open(audioInputStream);
                FloatControl gainControl = (FloatControl) backgroundMusics.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(volume));
                backgroundMusics.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                backgroundMusics.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBackGroundMusic() {
        if (backgroundMusics != null && backgroundMusics.isRunning()) {
            backgroundMusics.stop();
            backgroundMusics.setFramePosition(0);
        }
    }
}

