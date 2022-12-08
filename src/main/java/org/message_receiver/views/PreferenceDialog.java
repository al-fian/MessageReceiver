package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;

public class PreferenceDialog extends JDialog {

    private final JButton _okButton;
    private final JButton _cancelButton;
    private final JSpinner _portSpinner;
    private final SpinnerNumberModel _spinnerModel;
    private final JTextField _userField;
    private final JPasswordField _passwordField;

    public PreferenceDialog(JFrame parent) {
        super(parent, "Preferences", false);

        _okButton = new JButton("OK");
        _cancelButton = new JButton("Cancel");
        _spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        _userField = new JTextField(10);
        _passwordField = new JPasswordField(null, 10);

        _portSpinner = new JSpinner(_spinnerModel);

        JSpinner.NumberEditor portNumber = new JSpinner.NumberEditor(_portSpinner, "#");
        _portSpinner.setEditor(portNumber);

        setSize(400,300);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // 1st row
        gc.gridy = 0;
        gc.weighty = 1;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Username: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_userField, gc);

        // next row
        gc.gridy++;
        gc.weighty = 1;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Password: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_passwordField, gc);

        // next row
        gc.gridy++;
        gc.weighty = 1;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Port: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_portSpinner, gc);

        // next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(_okButton, gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_cancelButton, gc);

        _okButton.addActionListener(e -> {
            String user = _userField.getText();
            char[] password = _passwordField.getPassword();
            Integer value = (Integer)_portSpinner.getValue();

            System.out.println(user);
            System.out.println(password);
            System.out.println(value);

            setVisible(false);
        });

        _cancelButton.addActionListener(e -> setVisible(false));

    }
}
