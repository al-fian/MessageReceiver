package org.message_receiver.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PreferenceDialog extends JDialog {

    private final JButton _okButton;
    private final JButton _cancelButton;
    private final JSpinner _portSpinner;
    private final SpinnerNumberModel _spinnerModel;
    private final JTextField _userField;
    private final JPasswordField _passwordField;
    private IPreferenceListener _preferenceListener;

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

        setSize(350,250);
        setLocationRelativeTo(parent);

        layoutControls();

        _okButton.addActionListener(e -> {
            String user = _userField.getText();
            char[] password = _passwordField.getPassword();
            Integer port = (Integer)_portSpinner.getValue();

            if (_preferenceListener != null) {
                _preferenceListener.preferenceSet(user, new String(password), port);
            }

            setVisible(false);
        });

        _cancelButton.addActionListener(e -> setVisible(false));

    }

    public void setPreferenceListener(IPreferenceListener listener) {
        _preferenceListener = listener;
    }

    public void setDefaults(String user, String password, int port) {
        _userField.setText(user);
        _passwordField.setText(password);
        _portSpinner.setValue(port);
    }

    private void layoutControls() {

        JPanel _controlsPanel = new JPanel();
        JPanel _buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
        Border _titledBorder = BorderFactory.createTitledBorder("Database Preference");

        _controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,_titledBorder));

        _controlsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        Insets rightPadding = new Insets(0,0,0,5);
        Insets noPadding = new Insets(0,0,0,0);

        // 1st row
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        _controlsPanel.add(new JLabel("Username: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        _controlsPanel.add(_userField, gc);

        // next row
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        _controlsPanel.add(new JLabel("Password: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        _controlsPanel.add(_passwordField, gc);

        // next row
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        _controlsPanel.add(new JLabel("Port: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        _controlsPanel.add(_portSpinner, gc);

        // buttons panel
        _buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        _buttonsPanel.add(_okButton);
        _buttonsPanel.add(_cancelButton);

        Dimension _buttonSize = _cancelButton.getPreferredSize();
        _okButton.setPreferredSize(_buttonSize);

        // dialog panel
        setLayout(new BorderLayout());
        add(_controlsPanel, BorderLayout.CENTER);
        add(_buttonsPanel, BorderLayout.PAGE_END);

    }
}
