package org.message_receiver.views;

import org.message_receiver.models.Utils;

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
        _saveButton.setIcon(Utils.createIcon("/images/Save16.gif"));
        _saveButton.setToolTipText("Save");

        _refreshButton = new JButton();
        _refreshButton.setIcon(Utils.createIcon("/images/Refresh16.gif"));
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
}
