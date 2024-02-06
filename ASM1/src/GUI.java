import javax.sound.sampled.AudioFileFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    JFrame mainFrame, enterFrame;
    JButton wavButton;
    JButton tiffButton;
    JPanel jPanel;
    JFileChooser fileChooser;
    GridBagLayout gridBagLayout;
    GridBagConstraints gridBagConstraints;
    WavDecoder wavDecoder;

    public GUI(){
        mainFrame = new JFrame("GUI Screen");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400,400);
        wavButton = new JButton("WAV");
        tiffButton = new JButton("TIFF");
        jPanel = new JPanel();

        wavButton.setPreferredSize(new Dimension(170, 150));
        wavButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterFrame = new JFrame("new Frame");
                enterFrame.setSize(400, 400);
                enterFrame.add(new WavDecoder(enterFrame));
                enterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                enterFrame.setVisible(true);
            }
        });

        tiffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterFrame = new JFrame("new Frame");
                enterFrame.setSize(400, 400);
                TiffDecoder tiffDecoder = new TiffDecoder(enterFrame);
                enterFrame.add(tiffDecoder.getjLabel());
                enterFrame.pack();
                enterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                enterFrame.setVisible(true);
            }
        });

        tiffButton.setPreferredSize(new Dimension(170, 150));
        jPanel.add(wavButton);
        jPanel.add(tiffButton);

        jPanel.setVisible(true);
        mainFrame.add(jPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, jPanel);
        mainFrame.setVisible(true);
    }
}
