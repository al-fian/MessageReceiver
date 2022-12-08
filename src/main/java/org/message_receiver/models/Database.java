package org.message_receiver.models;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Database {

    private final List<Person> _people;
    private Connection _conn;

    public Database() {
        _people = new LinkedList<>();
    }

    public void addPerson(Person person) {
        _people.add(person);
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(_people);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person[] persons = _people.toArray(new Person[_people.size()]);

        oos.writeObject(persons);

        oos.close();
    }

    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ios = new ObjectInputStream(fis);

        try {
            Person[] persons = (Person[])ios.readObject();

            _people.clear();
            _people.addAll(Arrays.asList(persons));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ios.close();
    }

    public void removePerson(int index) {
        _people.remove(index);
    }

    public void connect() throws Exception {

        if (_conn != null) return;

        try {
            Class.forName("org.sqlite.JDBC");
            _conn = DriverManager.getConnection("jdbc:sqlite:test.db");

            Statement _createTableStatement = _conn.createStatement();
            _createTableStatement.execute(
                    "create table if not exists people" +
                            "(id, name, age, job, employment_status, tax_id, us_citizen, gender)"
            );

        } catch (ClassNotFoundException e) {
            throw new Exception("Driver not found");
        }

        System.out.println("Connected: " + _conn);

    }

    public void disconnect() {

        if (_conn != null) {
            try {
                _conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void save() throws SQLException {

        String _checkSql = "select count(*) as count from people where id=?";
        PreparedStatement _checkStmt = _conn.prepareStatement(_checkSql);

        for(Person person : _people) {

            int id = person.getId();

            _checkStmt.setInt(1, id);

            ResultSet _checkResult = _checkStmt.executeQuery();
            _checkResult.next();

            int count = _checkResult.getInt(1);

            System.out.println("Count person with ID " + id + " is " + count);

        }

        _checkStmt.close();
    }
}
