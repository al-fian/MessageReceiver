package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    private JButton _cancelButton;
    private JProgressBar _progressBar;

    public ProgressDialog(Window parent) {
        super(parent, "Message Downloading...",ModalityType.APPLICATION_MODAL);

        _cancelButton = new JButton("Cancel");
        _progressBar = new JProgressBar();

        // _progressBar.setIndeterminate(true);

        setLayout(new FlowLayout());

        Dimension size = _cancelButton.getPreferredSize();
        size.width = 400;
        _progressBar.setPreferredSize(size);

        add(_progressBar);
        add(_cancelButton);

        pack();

        setLocationRelativeTo(parent);
    }

    public void setMaximum(int value) {
        _progressBar.setMaximum(value);
    }

    public void setValue(int value) {
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
}
