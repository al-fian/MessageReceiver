package org.message_receiver.views;

import org.message_receiver.models.ConstantValue;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private final JLabel _nameLabel;
    private final JLabel _jobLabel;
    private final JLabel _ageLabel;
    private final JLabel _employmentLabel;
    private final JTextField _nameField;
    private final JTextField _jobField;
    private final JList<AgeCategory> _ageList;
    private final JComboBox<String> _employmentCombo;
    private final JCheckBox _citizenCheckBox;
    private final JTextField _taxField;
    private final JLabel _taxLabel;
    private final JRadioButton _maleRadio;
    private final JRadioButton _femaleRadio;
    private final ButtonGroup _genderGroup;
    private final JButton _okButton;

    private IFormListener _formListener;

    public FormPanel() {

        Dimension d = getPreferredSize();
        d.width = 250;
        setPreferredSize(d);

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        _nameLabel = new JLabel("Name: ");
        _jobLabel = new JLabel("Job: ");
        _ageLabel = new JLabel("Age: ");
        _employmentLabel = new JLabel("Employment: ");
        _nameField = new JTextField(10);
        _jobField = new JTextField(10);
        _ageList = new JList<>();
        _employmentCombo = new JComboBox<>();
        _citizenCheckBox = new JCheckBox();
        _taxField = new JTextField(10);
        _taxLabel = new JLabel("Tax ID: ");
        _maleRadio = new JRadioButton(ConstantValue.MALE);
        _femaleRadio = new JRadioButton(ConstantValue.FEMALE);
        _genderGroup = new ButtonGroup();
        _okButton = new JButton("OK");

        // set mnemonic on ok button
        _okButton.setMnemonic(KeyEvent.VK_O);

        _nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        _nameLabel.setLabelFor(_nameField);

        // set up gender radios
        _genderGroup.add(_maleRadio);
        _genderGroup.add(_femaleRadio);

        // default
        _maleRadio.setSelected(true);

        // why? to pass a string to ok button as one command
        _maleRadio.setActionCommand("male");
        _femaleRadio.setActionCommand("female");

        // set up tax ID
        _taxField.setEnabled(false);
        _taxLabel.setEnabled(false);

        _citizenCheckBox.addActionListener(e -> {
            boolean isTicked = _citizenCheckBox.isSelected();
            _taxField.setEnabled(isTicked);
            _taxLabel.setEnabled(isTicked);
        });

        // set up list
        DefaultListModel<AgeCategory> _ageModel = new DefaultListModel<>();
        _ageModel.addElement(new AgeCategory(0, "Under 18"));
        _ageModel.addElement(new AgeCategory(1,"18 to 65"));
        _ageModel.addElement(new AgeCategory(2,"65 or over"));
        _ageList.setModel(_ageModel);

        _ageList.setPreferredSize(new Dimension(110,66));
        _ageList.setBorder(BorderFactory.createEtchedBorder());
        _ageList.setSelectedIndex(1);

        // set up combo box
        DefaultComboBoxModel<String> _employmentComboModel = new DefaultComboBoxModel<>();
        _employmentComboModel.addElement(ConstantValue.EMPLOYED);
        _employmentComboModel.addElement(ConstantValue.SELF_EMPLOYED);
        _employmentComboModel.addElement(ConstantValue.UNEMPLOYED);
        _employmentCombo.setModel(_employmentComboModel);
        _employmentCombo.setSelectedIndex(0);

        _okButton.addActionListener(e -> {
            String name = _nameField.getText();
            String job = _jobField.getText();
            AgeCategory ageCategory = _ageList.getSelectedValue();
            String employmentStatus = (String)_employmentCombo.getSelectedItem();
            boolean isUsCitizen = _citizenCheckBox.isSelected();
            String taxID = _taxField.getText();

            String gender = _genderGroup.getSelection().getActionCommand();

            FormEvent fe = new FormEvent(this, name, job, ageCategory.getIndex(),
                    employmentStatus, isUsCitizen, taxID, gender);

            if (_formListener != null) {
                _formListener.formEventOccurred(fe);
            }
        });

        layoutComponents();

    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // 1st row
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
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
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(_jobLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(_jobField, gc);

        // 3rd row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(_ageLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_ageList, gc);

        // 4rd row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(_employmentLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_employmentCombo, gc);

        // 5th row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Is US citizen?"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_citizenCheckBox, gc);

        // 6th row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(_taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_taxField, gc);

        // 7th row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_maleRadio, gc);

        // 8th row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_femaleRadio, gc);

        // 9th row
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(_okButton, gc);
    }

    public void setFormListener(IFormListener formListener) {
        _formListener = formListener;
    }
}

class AgeCategory {

    private final int _index;
    private final String _text;
    public AgeCategory(int index, String text) {
        _index = index;
        _text = text;
    }

    public int getIndex() {
        return _index;
    }

    @Override
    public String toString() {
        return _text;
    }
}
