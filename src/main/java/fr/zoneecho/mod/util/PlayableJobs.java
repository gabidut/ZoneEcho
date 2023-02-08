package fr.zoneecho.mod.util;

import fr.zoneecho.mod.common.blocks.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PlayableJobs {
    admin_dir(1, "Directeur", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD4, ItemInit.WRENCH)), "admin"),
    admin_diradj(2, "Directeur adjoint", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD4, ItemInit.WRENCH)), "admin"),
    admin_sec(3, "Secrétaire", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "admin"),
    admin_compt(3, "Comptable", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "admin"),
    secu_dir(2, "Directeur sécurité", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "secu"),
    secu_supervisor(1, "Directeur sécurité", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "secu"),
    secu_cmd(2, "Commandant", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "secu"),
    secu_ltn(3, "Lieutenant", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "secu"),
    secu_mjr(4, "Major", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD2, ItemInit.WRENCH)), "secu"),
    secu_sgt(5, "Sergent", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD2, ItemInit.WRENCH)), "secu"),
    secu_cad(6, "Cadet", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD1, ItemInit.WRENCH)), "secu"),
    med_dir(1, "Directeur médical", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD4, ItemInit.WRENCH)), "med"),
    med_chir(2, "Chirurgien", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "med"),
    med_med(3, "Médecin", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD2, ItemInit.WRENCH)), "med"),
    med_inf(4, "Infirmier", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD2, ItemInit.WRENCH)), "med"),
    med_adson(4, "Aide-Soignant", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD1, ItemInit.WRENCH)), "med"),
    sci_dir(1, "Directeur scientifique", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD4, ItemInit.WRENCH)), "sci"),
    sci_sup(2, "Superviseur scientifique", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "sci"),
    sci_ensch(3, "Enseignant-Chercheur", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "sci"),
    sci_chespe(4, "Chercheur spécialisé", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD3, ItemInit.WRENCH)), "sci"),
    sci_che(5, "Chercheur", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD2, ItemInit.WRENCH)), "sci"),
    sci_assis(6, "Assistant", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD1, ItemInit.WRENCH)), "sci"),
    spe_ut(1, "Unité tactique", new BlockPos(0, 100, 0), new ArrayList<>(Arrays.asList(ItemInit.KEYCARD1, ItemInit.WRENCH)), "spe");

    private int id;
    private String name;
    private BlockPos spawn;
    private ArrayList<Item> items;
    private String sector;
    PlayableJobs(int id, String rankname, BlockPos spawnpos, ArrayList<Item> itemsbase, String sector) {
        this.id = id;
        this.name = rankname;
        this.spawn = spawnpos;
        this.items = itemsbase;
        this.sector = sector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockPos getSpawn() {
        return spawn;
    }

    public void setSpawn(BlockPos spawn) {
        this.spawn = spawn;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    public static ArrayList<String> getAllSectors() {
        ArrayList<String> sectors = new ArrayList<>();
        for (PlayableJobs.JobSector job : PlayableJobs.JobSector.values()) {
            sectors.add(job.name());
        }
        return sectors;
    }
    public static PlayableJobs getJobFromName(String name) {
        for (PlayableJobs job : PlayableJobs.values()) {
            if (job.name().equals(name)) {
                return job;
            }
        }
        return null;
    }
    public static ArrayList<PlayableJobs> getAllJobsFromSector(String sector) {
        ArrayList<PlayableJobs> jobs = new ArrayList<>();
        for (PlayableJobs job : PlayableJobs.values()) {
            if (job.getSector().equals(sector)) {
                jobs.add(job);
            }
        }
        return jobs;
    }

    public static PlayableJobs getSupRank(PlayableJobs job) {
        for (PlayableJobs playableJob : getAllJobsFromSector(job.getSector())) {
            if(playableJob.getId() == job.getId() - 1) {
                return playableJob;
            }
        }
        return null;
    }
    public static PlayableJobs getInfRank(PlayableJobs job) {
        for (PlayableJobs playableJob : getAllJobsFromSector(job.getSector())) {
            if(playableJob.getId() == job.getId() + 1) {
                return playableJob;
            }
        }
        return null;
    }

    public static List<String> getAllPlayableJobs(int limit) {
        int i = 0;
        List<String> jobs = new ArrayList<>();
        for (PlayableJobs job : PlayableJobs.values()) {
            if (i < limit) {
                jobs.add(job.name());
                i++;
            }
        }
        return jobs;
    }
    public static List<String> getAllPlayableJobs() {
        List<String> jobs = new ArrayList<>();
        for (PlayableJobs job : PlayableJobs.values()) {
            jobs.add(job.name());
        }
        return jobs;
    }
    public enum JobSector {
        admin(PlayableJobs.admin_compt),
        secu(PlayableJobs.secu_cad),
        med(PlayableJobs.med_med),
        sci(PlayableJobs.sci_assis),
        spe(PlayableJobs.spe_ut);

        private PlayableJobs firstrank;
        JobSector(PlayableJobs firstrank) {
            this.firstrank = firstrank;
        }

        public PlayableJobs getFirstrank() {
            return firstrank;
        }
        public static JobSector getSectorFromName(String name) {
            for (JobSector sector : JobSector.values()) {
                if (sector.name().equals(name)) {
                    return sector;
                }
            }
            return null;
        }

        public String getIdentifiant() {
            return this.name();
        }
    }
    public enum listPerso {
        droppable("Personnage jetable", "Ce personnage est un personnage temporaire qui se supprime lors de votre déconnexion. Il est transférable si vous avez une whitelist valable. Vous pouvez aussi vous en servir pour jouer le MassRP.", true),
        second("Personnage secondaire", "Ce personnage est votre personnage secondaire. Il doit passer par une whitelist. Attention, cependant, vous ne pouvez pas vous en servir pour faire une décourverte majeure en RP.", true),
        main("Personnage principal", "Ce personnage est votre personnage principal. Il doit passer par une whitelist.", true),
        staff("Staff - Dev", "Perso staff for events", false);

        private String name;
        private String description;
        private boolean visible;
        listPerso(String name, String description, boolean visible) {
            this.name = name;
            this.description = description;
            this.visible = visible;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public boolean isVisible() {
            return visible;
        }
        public static ArrayList<listPerso> getAllPerso() {
            ArrayList<listPerso> perso = new ArrayList<>();
            for (listPerso perso1 : listPerso.values()) {
                if(perso1.isVisible()) {
                    perso.add(perso1);
                }
            }
            return perso;
        }
        public static ArrayList<listPerso> getInvisiblePerso() {
            ArrayList<listPerso> perso = new ArrayList<>();
            for (listPerso perso1 : listPerso.values()) {
                if(!perso1.isVisible()) {
                    perso.add(perso1);
                }
            }
            return perso;
        }
        public static listPerso getPersoFromName(String name) {
            for (listPerso perso : listPerso.values()) {
                if (perso.name().equals(name)) {
                    return perso;
                }
            }
            return null;
        }
    }
}
