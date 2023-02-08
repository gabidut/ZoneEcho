package fr.zoneecho.mod.common.utils;

public enum Spe {
    CLIMB("Escalade", 0, 3, PlayableJobs.JobSector.secu),
    STRENTH("Force", 0, 3, PlayableJobs.JobSector.secu),
    COLDBLOOD("Sang froid", 0, 3, PlayableJobs.JobSector.secu),
    GUNMANIEMENT("Maniement des armes", 0, 3, PlayableJobs.JobSector.secu),
    PSYCOLOGY("Psychologie", 0, 3, PlayableJobs.JobSector.med),
    GENERALIST("Généraliste", 0, 3, PlayableJobs.JobSector.med),
    URGENCE("Urgence", 0, 3, PlayableJobs.JobSector.med),
    BIOLOGY("Biologie", 0, 3, PlayableJobs.JobSector.sci),
    NUCLEAR("Nucléaire", 0, 3, PlayableJobs.JobSector.sci),
    CONFINEMENT("Confinement", 0, 3, PlayableJobs.JobSector.sci),
    IRE("Informatique, Robotique et Équipement", 0, 3, PlayableJobs.JobSector.sci),
    MPA("Médecine et psychologie et développement d’Amnésique", 0, 3, PlayableJobs.JobSector.sci);

    private final String name;
    private final int minLevel;
    private final int maxLevel;
    private final PlayableJobs.JobSector sector;
    Spe(String name, int minLevel, int maxLevel, PlayableJobs.JobSector sector) {
        this.name = name;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.sector = sector;
    }

    public String getName() {
        return name;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public PlayableJobs.JobSector getSector() {
        return sector;
    }

}
