package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    private final JTextArea _textArea;
    public TextPanel() {

        _textArea = new JTextArea();

        _textArea.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        setLayout(new BorderLayout());
        add(new JScrollPane(_textArea), BorderLayout.CENTER);
    }

    public void appendText(String text) {
        _textArea.append(text);
    }

    public void setText(String text) {
        _textArea.setText(text);
    }
}
