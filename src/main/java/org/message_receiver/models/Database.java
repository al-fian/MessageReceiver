package org.message_receiver.models;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    private final ArrayList<Person> _people;

    public Database() {
        _people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        _people.add(person);
    }

    public List<Person> getPeople() {
        return _people;
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
}