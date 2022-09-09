package fr.zoneecho.mod.util.command;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.util.network.PacketSetBleeding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Objects;

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
        if(args.length > 1) {
            if(Objects.equals(args[0], "unbleed")) {
                ZoneEcho.network.sendTo(new PacketSetBleeding(), (EntityPlayerMP) sender);
            }
        }
        try {
            ZoneEcho.launchSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}