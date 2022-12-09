package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Toolbar extends JToolBar implements ActionListener {

    private final JButton _saveButton;
    private final JButton _refreshButton;
    private IToolbarListener _listener;

    public Toolbar() {

        // setBorder(BorderFactory.createEtchedBorder());
        setFloatable(false);

        _saveButton = new JButton();
        _saveButton.setIcon(createIcon("images/save16.png"));
        _saveButton.setToolTipText("Save");

        _refreshButton = new JButton();
        _refreshButton.setIcon(createIcon("images/refresh16.png"));
        _refreshButton.setToolTipText("Reload");

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

    private ImageIcon createIcon(String path) {

        URL url = getClass().getClassLoader().getResource(path);

        if (url == null) {
            System.err.println("Unable to load icon: " + path);
            return null;
        }

        return new ImageIcon(url);
    }
}
