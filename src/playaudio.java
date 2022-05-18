import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class playaudio {

    Clip clip2;
    long frame;
    static  String filepaths;
    AudioInputStream audio1;
    public  playaudio()
            throws UnsupportedAudioFileException, IOException, LineUnavailableException,NullPointerException
    {
        audio1=AudioSystem.getAudioInputStream(new File(filepaths).getAbsoluteFile());
        clip2=AudioSystem.getClip();
        clip2.open(audio1);
        clip2.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void main(String[] args){
        Scanner scan= new Scanner(System.in);
       try {
           filepaths = "src/RaatanLambiyan.wav";
           playaudio a1 = new playaudio();
           a1.play();
          while (true) {
              System.out.println("choose 1: to stop    2: to resume ");
              int c = scan.nextInt();
              a1.choices(c);
          }
       }

       catch (Exception e)
       {
           e.printStackTrace();
       }

    }
    public void choices(int c) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (c) {
            case 1:
                stop();
                break;
            case 2:
                resume1();
                break;
            default:
                System.out.println("enter valid option");
                break;
        }
    }
    public  void play()
    {
        clip2.start();
    }
    public  void  stop()
    {
        frame=clip2.getMicrosecondPosition();
        clip2.stop();
        clip2.close();

    }
    public  void resume1() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clip2.close();
        resetAudioStream1();
        clip2.setMicrosecondPosition(frame);
        this.play();
    }
    public void resetAudioStream1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audio1=AudioSystem.getAudioInputStream(new File(filepaths).getAbsoluteFile());
        clip2.open(audio1);
        clip2.loop(Clip.LOOP_CONTINUOUSLY);

    }
}
