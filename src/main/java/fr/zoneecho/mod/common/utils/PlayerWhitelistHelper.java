package fr.zoneecho.mod.common.utils;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerWhitelistHelper {
    public static boolean isRankWhitelisted(EntityPlayer p, PlayableJobs rank, PlayableJobs.listPerso list) {
        if(!Databases.getPlayerData(p).isString("whitelist")) {
            return false;
        } else {
            System.out.println(Databases.getPlayerData(p).isString("whitelist"));
            String[] ranks = Databases.getPlayerData(p).getString("whitelist").split(",");
            return Arrays.asList(ranks).contains(rank.name());
        }
    }
    public static boolean isWhitelistedInSpecificSector(PlayableJobs.JobSector sector, EntityPlayer player, PlayableJobs.listPerso list) {
        if(Databases.getPlayerData(player).getString("whitelist").equals("empty")) {
            return false;
        } else {
            String[] ranks = Databases.getPlayerData(player).getString("whitelist").split(",");
            for(String rank : ranks) {
                System.out.println(rank);
                System.out.println(Objects.requireNonNull(PlayableJobs.getJobFromName(rank)));
                if(Objects.equals(Objects.requireNonNull(PlayableJobs.getJobFromName(rank)).getSector(), sector.name())) {
                    return true;
                }
            }
            return false;
        }
    }
    public static String getWhitelistedInSpecificSector(PlayableJobs.JobSector sector, EntityPlayer player, PlayableJobs.listPerso list) {
        if(Databases.getPlayerData(player).getString("whitelist").equals("empty")) {
            return "Sans métier";
        } else {
            String[] ranks = Databases.getPlayerData(player).getString("whitelist").split(",");
            for(String rank : ranks) {
                System.out.println(rank);
                System.out.println(Objects.requireNonNull(PlayableJobs.getJobFromName(rank)));
                if(Objects.equals(Objects.requireNonNull(PlayableJobs.getJobFromName(rank)).getSector(), sector.name())) {
                    return Objects.requireNonNull(PlayableJobs.getJobFromName(rank)).name();
                }
            }
            return "Sans métier";
        }
    }
    public static void rankup(EntityPlayer p, PlayableJobs currank, PlayableJobs.listPerso list) {
        if(currank.getId() != 1) {
            System.out.println(Databases.getPlayerData(p).getString("whitelist"));
            String ranks = Databases.getPlayerData(p).getString("whitelist");
            Databases.getPlayerData(p).setString("whitelist", ranks.replaceAll(currank.name(), Objects.requireNonNull(PlayableJobs.getSupRank(currank)).name()));
            System.out.println(Objects.requireNonNull(PlayableJobs.getSupRank(currank)).name());
            System.out.println(Databases.getPlayerData(p).getString("whitelist"));
        } else {
            p.sendMessage(new TextComponentString("§6Vous avez déjà le rang le plus haut"));
        }
    }
    public static void unrank(EntityPlayer p, PlayableJobs currank, PlayableJobs.listPerso list) {
        if(currank.getId() != 1) {
            System.out.println(Databases.getPlayerData(p).getString("whitelist"));
            String ranks = Databases.getPlayerData(p).getString("whitelist");
            Databases.getPlayerData(p).setString("whitelist", ranks.replaceAll(currank.name(), Objects.requireNonNull(PlayableJobs.getInfRank(currank)).name()));
            System.out.println(Objects.requireNonNull(PlayableJobs.getSupRank(currank)).name());
            System.out.println(Databases.getPlayerData(p).getString("whitelist"));
        } else {
            p.sendMessage(new TextComponentString("§6Vous avez déjà le rang le plus bas"));
        }
    }
    public static ArrayList<String> getAllWhitelistedJobs(EntityPlayer p) {
        if(!Databases.getPlayerData(p).isString("whitelist")) {
            return new ArrayList<String>(Collections.singleton("empty"));
        } else {
            return new ArrayList<>(Arrays.asList(Databases.getPlayerData(p).getString("whitelist").split(",")));
        }
    }
    public static String getPlayerRankAsString(EntityPlayer p) {
        if(!Databases.getPlayerData(p).isString("whitelist")) {
            return "empty";
        } else {
            return Databases.getPlayerData(p).getString("whitelist");
        }
    }
    public static void whitelistRank(EntityPlayer player, PlayableJobs rank, PlayableJobs.listPerso list) {
        if(!Databases.getPlayerData(player).isString("whitelist")) {
            Databases.getPlayerData(player).setString("whitelist", rank.name());
        } else {
            if(!Databases.getPlayerData(player).getString("whitelist").contains(rank.name())) {
                if(Databases.getPlayerData(player).getString("whitelist").equals("empty")) {
                    System.out.println(Databases.getPlayerData(player).getString("whitelist"));
                    Databases.getPlayerData(player).setString("whitelist", rank.name());
                    Databases.save();
                } else {
                    System.out.println(Databases.getPlayerData(player).getString("whitelist"));
                    Databases.getPlayerData(player).setString("whitelist", Databases.getPlayerData(player).getString("whitelist") + "," + rank.name());                }
                    Databases.save();
            }

        }
    }
    public static String parseSectorFromRank(String job) {
        Pattern pattern = Pattern.compile("^(.+?)_", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(job);
        if(matcher.find()) {
            return matcher.group(1);
        } else {
            return "empty";
        }
    }
}
