package fr.zoneecho.mod.common.utils;

public class DataAboutMe {
    private Character c;
    private String job;
    private String speciality;

    public DataAboutMe(Character c, String job, String speciality) {
        this.c = c;
        this.job = job;
        this.speciality = speciality;
    }

    public String getJob() {
        return job;
    }

    public Character getCharacter() {
        return c;
    }


    public String getSpeciality() {
        return speciality;
    }
}
