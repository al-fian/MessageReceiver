package org.message_receiver.views;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String _name;
    private String _job;
    private int _ageCategory;
    private String _employment;
    private String _taxID;
    private boolean _USCitizen;
    private String _gender;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String job, int ageCategory,
                     String employment, boolean USCitizen, String taxID, String gender) {
        super(source);

        _name = name;
        _job = job;
        _ageCategory = ageCategory;
        _employment = employment;
        _taxID = taxID;
        _USCitizen = USCitizen;
        _gender = gender;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getJob() {
        return _job;
    }

    public int getAgeCategory() {
        return _ageCategory;
    }

    public String getEmployment() {
        return _employment;
    }

    public boolean isUsCitizen() {
        return _USCitizen;
    }

    public String getTaxID() {
        return _taxID;
    }
    public String getGender() {
        return _gender;
    }

    public void setJob(String job) {
        _job = job;
    }
}
