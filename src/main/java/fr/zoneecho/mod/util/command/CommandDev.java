package fr.zoneecho.mod.util.command;

import fr.zoneecho.mod.ZoneEcho;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class CommandDev extends CommandBase {



    @Override
    public String getName() {
        return "testcommand";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "testcommand";
    }

    @Override
    @SideOnly(Side.SERVER)
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        try {
            ZoneEcho.launchSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}