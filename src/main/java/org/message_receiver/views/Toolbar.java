package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton _helloButton;
    private final JButton _goodbyeButton;
    private IStringListener _listener;

    public Toolbar() {

        setBorder(BorderFactory.createEtchedBorder());

        _helloButton = new JButton("Hello\n");
        _goodbyeButton = new JButton("Goodbye\n");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(_helloButton);
        add(_goodbyeButton);

        _helloButton.addActionListener(this);
        _goodbyeButton.addActionListener(this);
    }

    public void setTextPanel(IStringListener listener) {
        _listener = listener;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton == _helloButton) {
            _listener.textEmitted("Hello\n");
        }

        if (clickedButton == _goodbyeButton) {
            _listener.textEmitted("Goodbye\n");
        }

    }
}
