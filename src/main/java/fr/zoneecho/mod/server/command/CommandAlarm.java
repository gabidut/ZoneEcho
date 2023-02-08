package fr.zoneecho.mod.server.command;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketPlayAlarm;
import fr.zoneecho.mod.common.network.PacketSetBleeding;
import io.netty.buffer.Unpooled;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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




        if ("on".equals(Databases.getDatabase("zoneecho_utils").getString("alarm"))) {
            Databases.getDatabase("zoneecho_utils").setString("alarm", "off");
            for(EntityPlayer player : players) {
                EntityPlayerMP pmp = (EntityPlayerMP) player;
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
                packetbuffer.writeString(player.getSoundCategory().getName());
                packetbuffer.writeString("");
                pmp.connection.sendPacket(new SPacketCustomPayload("MC|StopSound", packetbuffer));
            }
        } else {
            Databases.getDatabase("zoneecho_utils").setString("alarm", "on");
            for(EntityPlayer player : players) {
                ZoneEcho.network.sendTo(new PacketPlayAlarm(), (EntityPlayerMP) player);
            }
        }

        ZoneEcho.network.sendToAll(new PacketSetBleeding());
        Thread th = new Thread(() -> {
            while (Objects.equals(Databases.getDatabase("zoneecho_utils").getString("alarm"), "on")){
                try {
                    ZoneEcho.network.sendToAll(new PacketSetBleeding(true));
                    Thread.sleep(1000);
                    ZoneEcho.network.sendToAll(new PacketSetBleeding(false));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        th.start();

    }
}