package fr.zoneecho.mod.util.command;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.util.PlayableJobs;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandPInfo extends CommandBase {


    @Override
    public String getName() {
        return "pinfo";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "pinfo";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player;
        if(args.length == 1) {
            player = server.getPlayerList().getPlayerByUsername(args[0]);
        } else {
            player = (EntityPlayerMP) sender;
        }
        if(player != null) {
            sender.sendMessage(new TextComponentString("§6" + player.getName() + "§r : " + player.getUniqueID()));
            if (Databases.getPlayerData(player).isString("job")) {
                sender.sendMessage(new TextComponentString("§6Job§r : " + Databases.getPlayerData(player).getString("job")));
            } else {
                sender.sendMessage(new TextComponentString("§6Job§r : " + "None"));
            }
            PlayableJobs.listPerso.getAllPerso().forEach((listPerso -> {
                if(Databases.getPlayerData(player).isString("whitelist" + listPerso.name())) {
                    sender.sendMessage(new TextComponentString("§6Whitelist " + listPerso.getName() + "§r : " + Databases.getPlayerData(player).getString("whitelist" + listPerso.name())));
                }
            }));
        } else {
            sender.sendMessage(new TextComponentString("§6Player not found§r"));
        }
    }
}