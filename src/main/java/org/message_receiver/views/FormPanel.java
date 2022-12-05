package org.message_receiver.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    private final JLabel _nameLabel;
    private final JLabel _jobLabel;
    private final JTextField _nameField;
    private final JTextField _jobField;
    private final JButton _okButton;

    public FormPanel() {

        Dimension d = getPreferredSize();
        d.width = 250;
        setPreferredSize(d);

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        _nameLabel = new JLabel("Name: ");
        _jobLabel = new JLabel("Job: ");
        _nameField = new JTextField(12);
        _jobField = new JTextField(12);

        _okButton = new JButton("OK");

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        add(_nameLabel, gc);




    }
}
