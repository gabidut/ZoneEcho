package fr.zoneecho.mod.objects;

public class Rapport {
    public String name;
    public String content;
    public String date;
    public String author;
    public Boolean isLocked;
    public Boolean isLocal;
    public String accessJobAccred;

    public Rapport(String name, String content, String date, String author, Boolean isLocked, Boolean isLocal, String accessJobAccred) {
        this.name = name;
        this.content = " " + content;
        this.date = date;
        this.author = author;
        this.isLocked = isLocked;
        this.isLocal = isLocal;
        this.accessJobAccred = accessJobAccred;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = " " + content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Boolean getLocal() {
        return isLocal;
    }

    public void setLocal(Boolean local) {
        isLocal = local;
    }

    public String getAccessJobAccred() {
        return accessJobAccred;
    }

    public void setAccessJobAccred(String accessJobAccred) {
        this.accessJobAccred = accessJobAccred;
    }

    public static Rapport fromString(String str) {
        String[] split = str.split(";");
        return new Rapport(split[0], split[1], split[2], split[3], Boolean.parseBoolean(split[4]), Boolean.parseBoolean(split[5]), split[6]);
    }

    public static String toString(Rapport rapport) {
        return rapport.getName() + ";" + rapport.getContent() + ";" + rapport.getDate() + ";" + rapport.getAuthor() + ";" + rapport.getLocked() + ";" + rapport.getLocal() + ";" + rapport.getAccessJobAccred();
    }
}
