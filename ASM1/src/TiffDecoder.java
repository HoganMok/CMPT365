import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TiffDecoder {
    JFileChooser fileChooser;
    File selectedFile;
    BufferedImage bufferedImage;
    JLabel jLabel;
    public TiffDecoder(JFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // start at home directory

        int result = fileChooser.showOpenDialog(frame);

        // If the user selects a file
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try{
                bufferedImage = ImageIO.read(selectedFile);
                jLabel = new JLabel(new ImageIcon(bufferedImage));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public JLabel getjLabel() {
        return jLabel;
    }
}
