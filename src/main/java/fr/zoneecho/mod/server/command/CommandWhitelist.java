package fr.zoneecho.mod.server.command;

import com.google.common.collect.Lists;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.common.utils.PlayableJobs;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class CommandWhitelist extends CommandBase {


    @Override
    public String getName() {
        return "jobswhitelist";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "jobswhitelist";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length >= 1) {
            if(getEntity(server, sender, args[0]) instanceof EntityPlayerMP) {
                EntityPlayerMP p = (EntityPlayerMP) getEntity(server, sender, args[0]);
                if(Objects.equals(args[2], "whitelist")) {
                    Databases.getPlayerData(p).setString("whitelist" + args[1], args[3]);
                    System.out.println("Whitelist " + args[1] + " for " + p.getName() + " to " + args[3]);
                    System.out.println(Databases.getPlayerData(p).getString("whitelist" + args[1]));
                    Databases.save();
                }
                if(Objects.equals(args[2], "rankup")) {
                    p.sendMessage(new TextComponentString("§2Bravo ! Vous avez été rankup au rang de : §a" + Objects.requireNonNull(PlayableJobs.getSupRank(Objects.requireNonNull(PlayableJobs.getJobFromName(Databases.getPlayerData(p).getString("whitelist" + args[1]))))).name() + " §a sur votre §2" + Objects.requireNonNull(PlayableJobs.listPerso.getPersoFromName(args[1])).getName()));
                    sender.sendMessage(new TextComponentString("§6Vous avez rankup " + p.getName() + " au rang de : " + PlayableJobs.getSupRank(Objects.requireNonNull(PlayableJobs.getJobFromName(Databases.getPlayerData(p).getString("whitelist" + args[1])))) + "§6 sur le perso : " + args[1] ));
                    Databases.getPlayerData(p).setString("whitelist" + args[1], Objects.requireNonNull(PlayableJobs.getSupRank(Objects.requireNonNull(PlayableJobs.getJobFromName(Databases.getPlayerData(p).getString("whitelist" + args[1]))))).name());
                    Databases.save();
                }
                if(Objects.equals(args[2], "unrank")) {
                    p.sendMessage(new TextComponentString("§4Attention ! Vous avez été rétrogradé au rang de : §c" + Objects.requireNonNull(PlayableJobs.getSupRank(Objects.requireNonNull(PlayableJobs.getJobFromName(Databases.getPlayerData(p).getString("whitelist" + args[1]))))).name() + " §4 sur votre §c" + Objects.requireNonNull(PlayableJobs.listPerso.getPersoFromName(args[1])).getName()));
                    sender.sendMessage(new TextComponentString("§6Vous avez unrank " + p.getName() + " au rang de : " + PlayableJobs.getInfRank(Objects.requireNonNull(PlayableJobs.getJobFromName(Databases.getPlayerData(p).getString("whitelist" + args[1])))) + "§6 sur le perso : " + args[1] ));
                    Databases.getPlayerData(p).setString("whitelist" + args[1], Objects.requireNonNull(PlayableJobs.getInfRank(Objects.requireNonNull(PlayableJobs.getJobFromName(Databases.getPlayerData(p).getString("whitelist" + args[1]))))).name());
                    Databases.save();
                }
                if(Objects.equals(args[2], "seen")) {
                    String rank = Databases.getPlayerData(p).getString("whitelist" + args[1]);
                    sender.sendMessage(new TextComponentString("§6" + p.getName() + " : " + rank));
                    Databases.save();
                }
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if(args.length == 2)  {
        return getListOfStringsMatchingLastWord(args, Lists.newArrayList(PlayableJobs.listPerso.getAllPerso()));
        } else if (args.length == 3 && !Objects.equals(args[2], "droppable")) {
            return getListOfStringsMatchingLastWord(args, Lists.newArrayList("whitelist", "rankup", "unrank", "seen"));
        } else if (args.length == 4 && args[1].equals("whitelist")) {
            return Lists.newArrayList(PlayableJobs.getAllPlayableJobs(5));
        } else if (args.length == 5 && args[1].equals("rankup") || args.length == 5 && args[1].equals("unrank")) {
            return getListOfStringsMatchingLastWord(args, Lists.newArrayList(PlayableJobs.listPerso.getAllPerso()));
        } else {
            return super.getTabCompletions(server, sender, args, targetPos);
        }
    }
}