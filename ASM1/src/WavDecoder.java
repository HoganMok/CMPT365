import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WavDecoder extends JPanel{
    WavDecoder wavDecoder;
    JFileChooser fileChooser;
    AudioFileFormat wavFileFormat;
    File selectedFile;
    int[][] samples;
    public WavDecoder(JFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // start at home directory

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(frame);

        // If the user selects a file
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            AudioInputStream audioInputStream = null;
            
            try{
                audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
                int frameLength = (int) audioInputStream.getFrameLength();
                int frameSize = (int) audioInputStream.getFormat().getFrameSize();
                byte[] byteArray = new byte[frameLength*frameSize];

                System.out.println("frameLength: "+frameLength+" frameSize: "+frameSize);

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
                        int sample = getSixteenBitSample(high, low);
                        //System.out.println("high: "+high+" low: "+low+ " sample: "+sample + "channel number: "+j);
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

    protected int getSixteenBitSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Assuming two channels for simplicity. You can generalize this for more channels.
        Color[] colors = {Color.RED, Color.BLUE}; // Colors for each channel
        int channels = samples.length;
        int heightPerChannel = getHeight() / channels;
        double scale = 1.5;

        for (int channel = 0; channel < channels; channel++) {
            g.setColor(colors[channel]); // Set the color for the current channel

            for (int i = 1; i < samples[channel].length; i++) {
                int x1 = (int)((i - 1) * getWidth()*scale / (samples[channel].length - 1));
                int y1 = (int) ((1 - (samples[channel][i - 1] / 32768.0))*scale * heightPerChannel / 2 + (heightPerChannel * channel));
                int x2 = (int)(i * getWidth()*scale / (samples[channel].length - 1));
                int y2 = (int) ((1 - (samples[channel][i] / 32768.0))*scale * heightPerChannel / 2 + (heightPerChannel * channel));

                g.drawLine(x1, y1, x2, y2); // Draw the line for the current sample
            }
        }
    }

}
