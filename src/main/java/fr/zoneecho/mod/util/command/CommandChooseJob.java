package fr.zoneecho.mod.util.command;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandChooseJob extends CommandBase {



    @Override
    public String getName() {
        return "choosejob";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "choosejob";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        sender.sendMessage(new TextComponentString("§cRappel, perso:job perso as ENUM"));
        Databases.getPlayerData((EntityPlayer) sender).setString("job", args[0]);
    }

}