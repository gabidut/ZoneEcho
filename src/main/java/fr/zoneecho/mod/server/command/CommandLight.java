package fr.zoneecho.mod.server.command;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.server.utils.LampMode;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandLight extends CommandBase {


    @Override
    public String getName() {
        return "lights";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "lights";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(Objects.equals(args[0], "yellow")) {
            ZoneEcho.alarmMode = LampMode.YELLOW;
            sender.sendMessage(new TextComponentString("Lights set to yellow"));
        } else if (Objects.equals(args[0], "red")) {
            sender.sendMessage(new TextComponentString("Lights set to red"));
            ZoneEcho.alarmMode = LampMode.RED;
        } else {
            ZoneEcho.alarmMode = LampMode.GREY;
            sender.sendMessage(new TextComponentString("Lights set to none"));
        }
        Databases.save();
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        ArrayList<String> list = new ArrayList<>();
        list.add("yellow");
        list.add("red");
        list.add("off");
        return getListOfStringsMatchingLastWord(args, list);
    }
}