package org.message_receiver.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    private final JTextField _nameField;
    private final JTextField _jobField;

    private IFormListener _formListener;

    public FormPanel() {

        Dimension d = getPreferredSize();
        d.width = 250;
        setPreferredSize(d);

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        JLabel _nameLabel = new JLabel("Name: ");
        JLabel _jobLabel = new JLabel("Job: ");
        _nameField = new JTextField(12);
        _jobField = new JTextField(12);

        JButton _okButton = new JButton("OK");
        _okButton.addActionListener(e -> {
            String name = _nameField.getText();
            String job = _jobField.getText();

            FormEvent fe = new FormEvent(this, name, job);

            if (_formListener != null) {
                _formListener.formEventOccurred(fe);
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // 1st row
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(_nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(_nameField, gc);

        // 2nd row
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(_jobLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(_jobField, gc);

        // 3rd row
        gc.weightx = 1;
        gc.weighty = 2.0;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_okButton, gc);
    }

    public void setFormListener(IFormListener formListener) {
        _formListener = formListener;
    }
}
