import javax.sound.sampled.AudioFileFormat;
import javax.swing.*;
import java.io.File;

public class WavDecoder {
    WavDecoder wavDecoder;
    JFileChooser fileChooser;
    AudioFileFormat wavFileFormat;
    File selectedFile;
    public WavDecoder(JFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // start at home directory

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(frame);

        // If the user selects a file
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            // You can do something with the file here
            JOptionPane.showMessageDialog(frame, "Selected file: " + selectedFile.getAbsolutePath());
        }
    }

}
