package fr.zoneecho.mod.common.utils;

public class Character {
    private String name;
    private String date;
    private String surname;
    private Sex sex;

    // Sex.valueOf()
    public Character(String name, String surname, String date, Sex sex) {
        this.name = name;
        this.date = date;
        this.surname = surname;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String serialize(){
        return name + ";" + surname + ";" + date + ";" + sex.name();
    }

    public static Character deserialize(String s){
        String[] split = s.split(";");
        return new Character(split[0], split[1], split[2], Sex.valueOf(split[3]));
    }

    public enum Sex {
        MALE("Homme"),
        FEMALE("Femme"),
        OTHER("Non-Spécifié");

        Sex(String s) {
        }

    }
}
