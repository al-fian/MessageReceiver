package org.message_receiver.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame()
    {
        super("Message Receiver");

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
