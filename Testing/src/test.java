import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class test {
    private JPanel homePanel, centerPanel, topPanel, bottomPanel;
    private JButton EXITButton, OPENFILEButton, WAVButton,TIFFButton, SWITCHButton;
    private JLabel textbox;
    private JFrame mainFrame;
    WavDecoder wavDecoder;
    TiffDecoder tiffDecoder;
    int trueOrFalse;
    int counter = 0;
    public test(){
        mainFrame = new JFrame("CMPT 365 Assignment 1");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(704,576);
        mainFrame.setLocationRelativeTo(null);

        OPENFILEButton.setEnabled(false);
        OPENFILEButton.setVisible(true);
        SWITCHButton.setEnabled(false);

        WAVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trueOrFalse = 0;
                TIFFButton.setEnabled(false);
                OPENFILEButton.setEnabled(true);
                WAVButton.setEnabled(false);
                textbox.setText("WAV decoding mode");
                SWITCHButton.setEnabled(true);
            }
        });
        OPENFILEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textbox.getText().equals("WAV decoding mode")) {
                    if (wavDecoder != null) {
                        centerPanel.setVisible(true);
                        wavDecoder.setVisible(false);
                    }
                    if (trueOrFalse == 0) {
                        wavDecoder = new WavDecoder(mainFrame);
                        counter++;
                        if (wavDecoder.aBoolean) {
                            centerPanel.setVisible(false);
                            homePanel.add(wavDecoder, BorderLayout.CENTER);
                            wavDecoder.setVisible(true);
                        }
                    }
                } else if (textbox.getText().equals("TIF decoding mode")) {
                    if (tiffDecoder != null && tiffDecoder.getjLabel() != null) {
                        centerPanel.setVisible(true);
                        tiffDecoder.getjLabel().setVisible(false);
                    }
                    if (trueOrFalse == 1) {
                        tiffDecoder = new TiffDecoder(mainFrame);
                        if (tiffDecoder.aBoolean) {
                            centerPanel.setVisible(false);
                            homePanel.add(tiffDecoder.getjLabel(), BorderLayout.CENTER);
                            tiffDecoder.getjLabel().setVisible(true);
                        }
                    }
                }
            }
        });
        TIFFButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trueOrFalse = 1;
                TIFFButton.setEnabled(false);
                OPENFILEButton.setEnabled(true);
                WAVButton.setEnabled(false);
                textbox.setText("TIF decoding mode");
                SWITCHButton.setEnabled(true);
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });
        SWITCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textbox.setText("Please select either WAV or TIF");
                WAVButton.setEnabled(true);
                TIFFButton.setEnabled(true);
                OPENFILEButton.setEnabled(false);
                SWITCHButton.setEnabled(false);
                if (wavDecoder != null) wavDecoder.setVisible(false);
                if (tiffDecoder != null && tiffDecoder.getjLabel() != null) tiffDecoder.getjLabel().setVisible(false);
                centerPanel.setVisible(true);
            }
        });
        mainFrame.add(homePanel);
        mainFrame.setVisible(true);
    }
}
