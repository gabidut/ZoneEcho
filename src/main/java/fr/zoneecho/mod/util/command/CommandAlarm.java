package fr.zoneecho.mod.util.command;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.util.network.PacketPlayAlarm;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class CommandAlarm extends CommandBase {


    @Override
    public String getName() {
        return "alarm";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "alarm";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        // Databases.getDatabase("zoneecho_utils").setString("lights", "red");
        List<EntityPlayer> players = new ArrayList<>(server.getPlayerList().getPlayers());

        for(EntityPlayer player : players) {
            ZoneEcho.network.sendTo(new PacketPlayAlarm(), (EntityPlayerMP) player);
        }



    }
}