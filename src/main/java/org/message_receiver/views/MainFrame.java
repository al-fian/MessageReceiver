package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame()
    {
        super("Message Receiver");

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        Toolbar _toolbar = new Toolbar();
        TextPanel _textPanel = new TextPanel();
        FormPanel _formPanel = new FormPanel();

        add(_toolbar, BorderLayout.PAGE_START);
        add(_textPanel, BorderLayout.CENTER);
        add(_formPanel, BorderLayout.LINE_START);

        _toolbar.setStringListener(_textPanel::appendText);

        _formPanel.setFormListener(e -> {
            String name = e.getName();
            String job = e.getJob();

            _textPanel.appendText(name + ": " + job + "\n");
        });
    }
}
