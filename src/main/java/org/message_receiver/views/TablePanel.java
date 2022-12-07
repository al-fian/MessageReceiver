package org.message_receiver.views;

import org.message_receiver.models.Person;
import org.message_receiver.models.PersonTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class TablePanel extends JPanel {

    private final JTable _table;
    private final PersonTableModel _personTableModel;
    private final JPopupMenu _popup;

    public TablePanel() {

        _personTableModel = new PersonTableModel();
        _table = new JTable(_personTableModel);
        _popup = new JPopupMenu();

        JMenuItem _removeMenuItem = new JMenuItem("Delete row");
        _popup.add(_removeMenuItem);

        _table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    _popup.show(_table, e.getX(), e.getY());
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(_table), BorderLayout.CENTER);
    }

    public void setData(List<Person> p) {
        _personTableModel.setData(p);
    }

    public void refresh() {
        _personTableModel.fireTableDataChanged();
    }
}
