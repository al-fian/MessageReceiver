package org.message_receiver.views;

import org.message_receiver.models.Message;
import org.message_receiver.models.Utils;

import javax.swing.*;
import java.awt.*;

public class MessageListRenderer implements ListCellRenderer {

    private JPanel _panel;
    private JLabel _label;
    private Color _selectedColor;
    private Color _normalColor;

    public MessageListRenderer() {
        _panel = new JPanel();
        _label = new JLabel();

        _selectedColor = new Color(210, 210,255);
        _normalColor = Color.white;

        _label.setIcon(Utils.createIcon("/images/Information24.gif"));

        _panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        _panel.add(_label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus) {

        Message _message = (Message) value;
        _label.setText(_message.getTitle());
        _panel.setBackground(cellHasFocus ? _selectedColor : _normalColor);

        return _panel;
    }
}
