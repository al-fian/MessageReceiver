package org.message_receiver.controllers;

import org.message_receiver.models.*;
import org.message_receiver.views.FormEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private final Database _db;

    public Controller() {
        _db = new Database();
    }

    public void addPerson(FormEvent fe) {

        String name = fe.getName();
        String job = fe.getJob();
        int ageCategoryId = fe.getAgeCategory();
        String employmentCategoryValue = fe.getEmployment();
        boolean isUS = fe.isUsCitizen();
        String taxId = fe.getTaxID();
        String genderValue = fe.getGender();

        AgeCategory ageCategory;
        EmploymentCategory employmentCategory;
        Gender gender;

        ageCategory = switch (ageCategoryId) {
            case 0 -> AgeCategory.CHILD;
            case 2 -> AgeCategory.SENIOR;
            default -> AgeCategory.ADULT;
        };

        if (employmentCategoryValue.equals(ConstantValue.EMPLOYED)) {
            employmentCategory = EmploymentCategory.EMPLOYED;
        }
        else if (employmentCategoryValue.equals(ConstantValue.SELF_EMPLOYED)) {
            employmentCategory = EmploymentCategory.SELF_EMPLOYED;
        }
        else {
            employmentCategory = EmploymentCategory.UNEMPLOYED;
        }

        if (genderValue.equals(ConstantValue.MALE)) {
            gender = Gender.MALE;
        }
        else if (genderValue.equals(ConstantValue.FEMALE)) {
            gender = Gender.FEMALE;
        } else {
            gender = Gender.MALE;
        }

        Person person = new Person(name,job,ageCategory,employmentCategory,taxId,isUS,gender);

        _db.addPerson(person);

    }

    public List<Person> getPeople() {
        return _db.getPeople();
    }

    public void saveToFile(File file) throws IOException {
        _db.saveToFile(file);
    }

    public void loadFromFile(File file) throws IOException {
        _db.loadFromFile(file);
    }

    public void removePerson(int index) {
        _db.removePerson(index);
    }

    public void save() throws SQLException {
        _db.save();
    }

    public void load() throws SQLException {
        _db.load();
    }

    public void connect() throws Exception {
        _db.connect();
    }

    public void disconnect() {
        _db.disconnect();
    }

}
