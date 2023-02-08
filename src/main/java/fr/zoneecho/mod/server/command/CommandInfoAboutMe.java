package fr.zoneecho.mod.server.command;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketGuiAboutMe;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandInfoAboutMe extends CommandBase {


    @Override
    public String getName() {
        return "moi";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "moi";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) sender;
            ZoneEcho.network.sendTo(new PacketGuiAboutMe((EntityPlayerMP) sender), (EntityPlayerMP) sender);
        }
    }
}