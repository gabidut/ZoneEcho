package fr.zoneecho.mod.common.utils;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;

import java.util.ArrayList;

public class Intranet {


    public static void addRapport(Rapport r) {
        int lastId = ZoneEcho.dbIntranet.getInteger("id");
        lastId++;
        ZoneEcho.dbIntranet.setString(String.valueOf(lastId), Rapport.toString(r));
        ZoneEcho.dbIntranet.setInteger("id", lastId);
        finishAction();
        ZoneEcho.logger.info("Rapport N°" + lastId + " : " + Rapport.toString(r) + " added.");
    }

    public static void removeRapport(int id) {
        ZoneEcho.dbIntranet.remove(String.valueOf(id));
        finishAction();
        ZoneEcho.logger.info("Rapport N°" + id + " removed.");
    }

    public static ArrayList<Rapport> getAllRapports() {
        ArrayList<Rapport> list = new ArrayList<>();
        for (int i = 1; i < ZoneEcho.dbIntranet.getInteger("id"); i++) {
            list.add(Rapport.fromString(ZoneEcho.dbIntranet.getString(String.valueOf(i))));
        }
        return list;
    }

    private static void finishAction() {
        Databases.save();
    }
}
