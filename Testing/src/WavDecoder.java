import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class WavDecoder extends JPanel{
    WavDecoder wavDecoder;
    JFileChooser fileChooser;
    AudioFileFormat wavFileFormat;
    File selectedFile;
    int[][] samples;
    boolean aBoolean;
    public WavDecoder(JFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("WAV files", "wav"));

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            AudioInputStream audioInputStream = null;
            
            try{
                aBoolean = true;
                audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
                int frameLength = (int) audioInputStream.getFrameLength();
                int frameSize = (int) audioInputStream.getFormat().getFrameSize();
                byte[] byteArray = new byte[frameLength*frameSize];


                audioInputStream.read(byteArray);

                int channels = audioInputStream.getFormat().getChannels();
                samples = new int[channels][frameLength];

                int sampleIndex = 0;
                for (int i = 0; i < byteArray.length;) {
                    for (int j = 0; j < channels; j++){
                        int low = (int) byteArray[i];
                        i++;
                        int high = (int) byteArray[i];
                        i++;
                        int sample = combineHighLowSample(high, low);
                        samples[j][sampleIndex] = sample;
                    }
                    sampleIndex++;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    audioInputStream.close();
                } catch (Exception e){
                    
                }
            }
        }
    }

    protected int combineHighLowSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (aBoolean) {
            Color[] colors = {Color.RED, Color.BLUE};
            int channels = samples.length;
            int heightPerChannel = (getHeight()-25)/ channels;
            double scale = 1.5;
            double highestPossiblePoint = 32768.0;

            for (int channel = 0; channel < channels; channel++) {
                g.setColor(colors[channel]);

                for (int i = 1; i < samples[channel].length; i++) {
                    int x1 = (int) ((i - 1) * getWidth() * scale / (samples[channel].length - 1));
                    int y1 = (int) ((1 - (samples[channel][i - 1] / highestPossiblePoint)) * scale * heightPerChannel / 2 + (heightPerChannel * channel));
                    int x2 = (int) (i * getWidth() * scale / (samples[channel].length - 1));
                    int y2 = (int) ((1 - (samples[channel][i] / highestPossiblePoint)) * scale * heightPerChannel / 2 + (heightPerChannel * channel));

                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }

    public void changeBool(){aBoolean = false;}

}
