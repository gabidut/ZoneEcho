package fr.zoneecho.mod.server.command;

import fr.zoneecho.mod.common.utils.Things;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class CommandManualSocket extends CommandBase {



    @Override
    public String getName() {
        return "socket";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "socket";
    }

    @Override
    @SideOnly(Side.SERVER)
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws WrongUsageException {
        if(args.length >= 1) {
            try {
                Things.sendGET("warnplayer?player=" + args[0] + "&reason=" + args[1]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new WrongUsageException("/socket <id>");
        }
    }
}