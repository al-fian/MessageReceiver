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
    private IPersonTableListener _personTableListener;

    public TablePanel() {

        _personTableModel = new PersonTableModel();
        _table = new JTable(_personTableModel);
        _popup = new JPopupMenu();

        JMenuItem _removeMenuItem = new JMenuItem("Delete row");
        _popup.add(_removeMenuItem);

        _table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int row = _table.rowAtPoint(e.getPoint());
                _table.getSelectionModel().setSelectionInterval(row, row);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    _popup.show(_table, e.getX(), e.getY());
                }
            }
        });

        _removeMenuItem.addActionListener(e -> {
            int row = _table.getSelectedRow();

            if (_personTableListener != null) {
                _personTableListener.rowDeleted(row);
                _personTableModel.fireTableRowsDeleted(row, row);
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

    public void setPersonTableListener(IPersonTableListener listener) {
        _personTableListener = listener;
    }
}
