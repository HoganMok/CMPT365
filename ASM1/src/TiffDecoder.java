import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;

public class TiffDecoder {
    private JFileChooser fileChooser;
    private File selectedFile;
    private BufferedImage bufferedImage;
    private JLabel jLabel;
    public Boolean aBoolean = true;
    public TiffDecoder(JFrame frame){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("TIFF files", "tif"));

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try{
                bufferedImage = ImageIO.read(selectedFile);
                jLabel = new JLabel(new ImageIcon(bufferedImage));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else aBoolean = false;
    }

    public JLabel getjLabel() {
        return jLabel;
    }
}
