package fr.zoneecho.mod.common.utils;

public class DataAboutMe {
    private String name;
    private String surname;
    private String job;
    private String speciality;

    public DataAboutMe(String name, String surname, String job, String speciality) {
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getJob() {
        return job;
    }

    public String getSpeciality() {
        return speciality;
    }
}
