package fr.zoneecho.mod.util.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandHelp extends CommandBase {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "help";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new TextComponentString("§6-----------------------------------------------------"));
        sender.sendMessage(new TextComponentString("§6ZoneEcho Mod Help"));
        sender.sendMessage(new TextComponentString("§6/moi : Affiche les informations sur votre personnage"));
        sender.sendMessage(new TextComponentString("§6-----------------------------------------------------"));
    }
}