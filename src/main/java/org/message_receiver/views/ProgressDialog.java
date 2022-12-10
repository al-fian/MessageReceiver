package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgressDialog extends JDialog {

    private JButton _cancelButton;
    private JProgressBar _progressBar;
    private IProgressDialogListener _progressDialogListener;

    public ProgressDialog(Window parent, String title) {
        super(parent, title,ModalityType.APPLICATION_MODAL);

        _cancelButton = new JButton("Cancel");
        _progressBar = new JProgressBar();

        _progressBar.setMaximum(10);
        _progressBar.setStringPainted(true);
        _progressBar.setString("Retrieving messages...");

        // _progressBar.setIndeterminate(true);

        setLayout(new FlowLayout());

        Dimension size = _cancelButton.getPreferredSize();
        size.width = 400;
        _progressBar.setPreferredSize(size);

        add(_progressBar);
        add(_cancelButton);

        _cancelButton.addActionListener(e -> {
            if (_progressDialogListener != null) {
                _progressDialogListener.progressDialogCancelled();
            }
        });

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                if (_progressDialogListener != null) {
                    _progressDialogListener.progressDialogCancelled();
                }
            }
        });

        pack();

        setLocationRelativeTo(parent);
    }

    public void setMaximum(int value) {
        _progressBar.setMaximum(value);
    }

    public void setValue(int value) {

        int progress = 100*value/_progressBar.getMaximum();
        _progressBar.setString(String.format("%d%%", progress));

        _progressBar.setValue(value);
    }

    @Override
    public void setVisible(final boolean visible) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                if (visible == false) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    _progressBar.setValue(0);
                }

                ProgressDialog.super.setVisible(visible);
            }
        });
    }

    public void setProgressDialogListener(IProgressDialogListener progressDialogListener) {
        _progressDialogListener = progressDialogListener;
    }
}
