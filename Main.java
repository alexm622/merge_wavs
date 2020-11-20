import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        //create an arraylist of files
        ArrayList<File> fs = new ArrayList<>();
        //using folder wavs
        File wavs = new File("wavs/");
        for(File f : wavs.listFiles()){
            //if file is not a wav file we throw it out and don't use it
            if(f.isDirectory() || !f.getName().endsWith(".wav")){
                //keep looping
                continue;
            }

            //if the file is a wav then we use it. add to the arraylist
            fs.add(f);
        }

        //create an arraylist of clips
        ArrayList<Clip> clips = new ArrayList<>();

        //loop through the wav files
        for(File f : fs){
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;
            //simple debug
            System.out.println("added file " + f.getName());
            try{
                //get the audio input stream
                stream = AudioSystem.getAudioInputStream(f);

                //setup the clip object
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);

                //open the audio input stream
                clip.open(stream);

                //add to the arraylist
                clips.add(clip);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }

        //loop through the clips
        for(Clip c : clips){
            c.start();
            //or you can do c.loop
        }
        Thread.sleep(5000);

        //and using a function like this will stop all the clips from playing
        for(Clip c : clips){
            c.close();
        }
        Thread.sleep(10000);

        
    }
}