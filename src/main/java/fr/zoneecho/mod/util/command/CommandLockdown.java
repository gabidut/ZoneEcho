package fr.zoneecho.mod.util.command;

import fr.zoneecho.mod.ZoneEcho;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandLockdown extends CommandBase {


    @Override
    public String getName() {
        return "lockdown";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "lockdown";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(ZoneEcho.dbUtils.getInteger("isLockdown") == 1) {
            ZoneEcho.dbUtils.setInteger("isLockdown", 0);
            sender.sendMessage(new TextComponentString("Déblocage du site."));
        } else {
            ZoneEcho.dbUtils.setInteger("isLockdown", 1);
            sender.sendMessage(new TextComponentString("Bloquage du site."));
        }


    }
}