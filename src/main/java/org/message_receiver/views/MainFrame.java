package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final Toolbar _toolbar;
    private final TextPanel _textPanel;
    private final JButton _button;

    public MainFrame()
    {
        super("Message Receiver");

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        _toolbar = new Toolbar();
        _textPanel = new TextPanel();
        _button = new JButton("Click Me!");

        add(_toolbar, BorderLayout.PAGE_START);
        add(_textPanel, BorderLayout.CENTER);

        _toolbar.setTextPanel(_textPanel::appendText);
    }
}
