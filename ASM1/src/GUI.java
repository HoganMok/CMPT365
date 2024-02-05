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
                fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // start at home directory

                // Show the file chooser dialog
                int result = fileChooser.showOpenDialog(mainFrame);

                // If the user selects a file
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // You can do something with the file here
                    JOptionPane.showMessageDialog(mainFrame, "Selected file: " + selectedFile.getAbsolutePath());
                }
            }
        });

        tiffButton.setPreferredSize(new Dimension(170, 150));
        jPanel.add(wavButton);
        jPanel.add(tiffButton);
//        gridBagLayout = new GridBagLayout();
//        gridBagConstraints = new GridBagConstraints();
//        jFrame.setLayout(gridBagLayout);
//        gridBagConstraints.fill = GridBagConstraints.PAGE_START;
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.ipadx = 80;
//        jFrame.add(wavButton, gridBagConstraints);
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.ipadx = 80;
//        jFrame.add(tiffButton, gridBagConstraints);

        jPanel.setVisible(true);
        mainFrame.add(jPanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, jPanel);
        mainFrame.setVisible(true);
    }
}
