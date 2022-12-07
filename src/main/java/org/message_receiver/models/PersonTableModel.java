package org.message_receiver.models;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private List<Person> _people;
    private final String[] _columnNames = {"ID", "Name", "Job", "Age", "Employment", "US Citizen", "TaxID"};

    public void setData(List<Person> people) {
        _people = people;
    }
    @Override
    public int getRowCount() {
        return _people.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int column) {
        return _columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Person person = _people.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> person.getId();
            case 1 -> person.getName();
            case 2 -> person.getJob();
            case 3 -> person.getAgeCategory();
            case 4 -> person.getEmploymentCategory();
            case 5 -> person.isUsCitizen();
            case 6 -> person.getTaxId();
            default -> null;
        };

    }
}
