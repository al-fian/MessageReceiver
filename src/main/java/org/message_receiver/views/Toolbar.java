package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton _saveButton;
    private final JButton _refreshButton;
    private IToolbarListener _listener;

    public Toolbar() {

        setBorder(BorderFactory.createEtchedBorder());

        _saveButton = new JButton("Save");
        _refreshButton = new JButton("Refresh");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(_saveButton);
        add(_refreshButton);

        _saveButton.addActionListener(this);
        _refreshButton.addActionListener(this);
    }

    public void setToolbarListener(IToolbarListener listener) {
        _listener = listener;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton == _saveButton) {
            _listener.saveEventOccurred();
        }

        if (clickedButton == _refreshButton) {
            _listener.refreshEventOccurred();
        }

    }
}
