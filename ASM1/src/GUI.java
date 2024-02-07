import javax.sound.sampled.AudioFileFormat;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    JFrame mainFrame, wavFrame, tifFrame;
    JButton wavButton, tiffButton, openFileButton, backButton, exitButton;
    JPanel homePanel, enterPanel;
    WavDecoder wavDecoder;
    Boolean mainFrameBoolean, wavFrameBoolean, tifFrameBoolean;

    public GUI(){
        mainFrame = new JFrame("Home Page");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400,400);
        mainFrame.setLocationRelativeTo(null);

        wavFrame = new JFrame("WAV");
        wavFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wavFrame.setSize(1000,800);
        wavFrame.setLocationRelativeTo(null);

        tifFrame = new JFrame("TIFF");
        tifFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tifFrame.setSize(400,400);
        tifFrame.setLocationRelativeTo(null);

        mainFrameBoolean = true;
        wavFrameBoolean = tifFrameBoolean = false;

        wavButton = new JButton("WAV");
        tiffButton = new JButton("TIFF");
        openFileButton = new JButton("Open File");
        openFileButton.setPreferredSize(new Dimension(1000, 25));
        exitButton = new JButton("EXIT");
        //exitButton.setPreferredSize(new Dimension(50, 50));
        exitButton.setSize(new Dimension(50, 50));

        homePanel = new JPanel();
        enterPanel = new JPanel();

        wavButton.setPreferredSize(new Dimension(170, 150));
        wavButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameBoolean = tifFrameBoolean = false;
                wavFrameBoolean = true;

                enterPanel.add(openFileButton);
                wavFrame.getContentPane().add(BorderLayout.SOUTH, enterPanel);
                wavFrame.setVisible(wavFrameBoolean);

                openFileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        wavDecoder = new WavDecoder(wavFrame);
                        if (wavDecoder.aBoolean) {
                            wavFrame.add(wavDecoder);
                            wavFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            wavFrame.setVisible(true);
                        }
                    }
                });
            }
        });

        tiffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameBoolean = wavFrameBoolean = false;
                tifFrameBoolean = true;

                tifFrame = new JFrame("TIFF");
                tifFrame.setSize(400, 400);
                TiffDecoder tiffDecoder = new TiffDecoder(tifFrame);
                if (tiffDecoder.aBoolean) {
                    tifFrame.add(tiffDecoder.getjLabel());
                    tifFrame.pack();
                    tifFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    tifFrame.setVisible(true);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                tifFrame.dispose();
                wavFrame.dispose();
            }
        });

        tiffButton.setPreferredSize(new Dimension(170, 150));

        homePanel.add(wavButton);
        homePanel.add(tiffButton);
        //homePanel.add(exitButton);
        homePanel.setBackground(Color.GREEN);
        homePanel.setVisible(mainFrameBoolean);
        //mainFrame.add(homePanel);
        mainFrame.getContentPane().add(BorderLayout.CENTER, homePanel);
        mainFrame.getContentPane().add(BorderLayout.NORTH, exitButton);
        //mainFrame.pack();
        mainFrame.setVisible(mainFrameBoolean);
    }
}
