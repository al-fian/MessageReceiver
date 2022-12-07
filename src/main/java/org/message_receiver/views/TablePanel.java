package org.message_receiver.views;

import org.message_receiver.models.Person;
import org.message_receiver.models.PersonTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class TablePanel extends JPanel {

    private final JTable _table;
    private final PersonTableModel _personTableModel;

    public TablePanel() {

        _personTableModel = new PersonTableModel();
        _table = new JTable(_personTableModel);

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
