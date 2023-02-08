package fr.zoneecho.mod.server.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommandSpe extends CommandBase {



    @Override
    public String getName() {
        return "spe";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "spe";
    }

    @Override
    @SideOnly(Side.SERVER)
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

    }

}