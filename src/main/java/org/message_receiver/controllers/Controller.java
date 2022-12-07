package org.message_receiver.controllers;

import org.message_receiver.models.*;
import org.message_receiver.views.FormEvent;

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

        switch(ageCategoryId) {
            case 0:
                ageCategory = AgeCategory.CHILD;
                break;
            case 2:
                ageCategory = AgeCategory.SENIOR;
                break;
            default:
                ageCategory = AgeCategory.ADULT;
        }

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


}
