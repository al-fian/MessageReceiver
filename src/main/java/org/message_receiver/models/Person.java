package org.message_receiver.models;

public class Person {

    private static int count = 1;
    private int _id;
    private String _name;
    private String _job;
    private AgeCategory _ageCategory;
    private EmploymentCategory _employmentCategory;
    private String _taxId;
    private boolean _usCitizen;
    private Gender _gender;

    public Person(String name, String job, AgeCategory ageCategory,
                  EmploymentCategory employmentCategory, String taxId,
                  boolean usCitizen, Gender gender) {

        _name = name;
        _job = job;
        _ageCategory = ageCategory;
        _employmentCategory = employmentCategory;
        _taxId = taxId;
        _usCitizen = usCitizen;
        _gender = gender;

        _id = count;
        count++;

    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
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

    public void setJob(String job) {
        _job = job;
    }

    public AgeCategory getAgeCategory() {
        return _ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        _ageCategory = ageCategory;
    }

    public EmploymentCategory getEmploymentCategory() {
        return _employmentCategory;
    }

    public void setEmploymentCategory(EmploymentCategory employmentCategory) {
        _employmentCategory = employmentCategory;
    }

    public String getTaxId() {
        return _taxId;
    }

    public void setTaxId(String taxId) {
        _taxId = taxId;
    }

    public boolean isUsCitizen() {
        return _usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        _usCitizen = usCitizen;
    }

    public Gender getGender() {
        return _gender;
    }

    public void setGender(Gender gender) {
        _gender = gender;
    }
}
