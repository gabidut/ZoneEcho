package fr.zoneecho.mod.objects;

import fr.zoneecho.mod.util.PlayableJobs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.Objects;

public class AccessJobAccred {

    private final Object allowed;

    public AccessJobAccred(PlayableJobs.JobSector sector) {
        this.allowed = sector;
    }
    public AccessJobAccred(ArrayList<EntityPlayer> players) {
        this.allowed = players;
    }
    public AccessJobAccred(int accred) {
        this.allowed = accred;
    }

    /**
     * @Deprecated Use {@link #convertToString()} (EntityPlayer)} instead.
     */

    @Override
    @Deprecated
    public String toString() {
        return convertToString();
    }

    public String convertToString() {
        if(allowed instanceof PlayableJobs.JobSector) {
            return "js." + ((PlayableJobs.JobSector) allowed).getIdentifiant();
        } else if(allowed instanceof ArrayList) {
            StringBuilder sb = new StringBuilder();
            for (EntityPlayer player : (ArrayList<EntityPlayer>) allowed) {
                sb.append(player.getName()).append(";");
            }
            return "ps." + sb;
        } else if(allowed instanceof Integer) {
            return "ac." + allowed;
        } else {
            return "ev.";
        }
    }
    public boolean isAccredditation() {
        if(convertToString().startsWith("ac.")) {
            try {
                Integer accred = Integer.parseInt(convertToString().substring(3));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean isPlayerList() {
        if(convertToString().startsWith("ps.")) {

            String[] players = convertToString().substring(3).split(";");
            return players.length != 0;
        } else {
            return false;
        }
    }

    public static boolean isJobSector(String valueAsString) {
        if(valueAsString.startsWith("js.")) {
            PlayableJobs.JobSector jobSector = PlayableJobs.JobSector.getSectorFromName(valueAsString.substring(3));
            return !Objects.isNull(jobSector);
        } else {
            return false;
        }
    }

    public boolean isJobSector() {
        if(convertToString().startsWith("js.")) {
            PlayableJobs.JobSector jobSector = PlayableJobs.JobSector.getSectorFromName(convertToString());
            return !Objects.isNull(jobSector);
        } else {
            return false;
        }
    }

    public boolean isAcredAllowed(Integer accred) {
        if(isAccredditation()) {
            return accred >= Integer.parseInt(convertToString().substring(3));
        } else {
            return false;
        }
    }
    public boolean isPlayerAllowed( EntityPlayer player) {
        if(isPlayerList()) {
            String[] players = convertToString().substring(3).split(";");
            for (String player1 : players) {
                if(player1.equals(player.getName())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    public boolean isJobSectorAllowed(PlayableJobs.JobSector jobSector) {
        if(isJobSector(convertToString())) {
            return jobSector.getIdentifiant().equals(convertToString().substring(3));
        } else {
            return false;
        }
    }
    public static AccessJobAccred fromString(String str) {
        if(str.startsWith("js.")) {
            return new AccessJobAccred(PlayableJobs.JobSector.getSectorFromName(str.substring(3)));
        } else if(str.startsWith("ps.")) {
            String[] players = str.substring(3).split(";");
            ArrayList<EntityPlayer> playerList = new ArrayList<>();
            for (String player : players) {
                playerList.add(Minecraft.getMinecraft().world.getPlayerEntityByName(player));
            }
            return new AccessJobAccred(playerList);
        } else if(str.startsWith("ac.")) {
            return new AccessJobAccred(Integer.parseInt(str.substring(3)));
        } else {
            return null;
        }
    }
}
