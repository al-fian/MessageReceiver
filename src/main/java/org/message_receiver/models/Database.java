package org.message_receiver.models;

import java.util.ArrayList;
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
}
