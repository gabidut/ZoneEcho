package fr.zoneecho.mod.server.command;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketPlayAlarm;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandAlarmOther extends CommandBase {


    @Override
    public String getName() {
        return "alarmother";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "alarmother";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        List<EntityPlayer> players = new ArrayList<>(server.getPlayerList().getPlayers());
        if(args.length >= 1) {
            if(Objects.equals(args[0], "any")) {
                for(EntityPlayer player : players) {
                    ZoneEcho.network.sendTo(new PacketPlayAlarm(), (EntityPlayerMP) player);
                }
            }
            if(Objects.equals(args[0], "fire")) {
                for(EntityPlayer player : players) {
                    ZoneEcho.network.sendTo(new PacketPlayAlarm(1), (EntityPlayerMP) player);
                }
            }
            if(Objects.equals(args[0], "generator")) {
                for(EntityPlayer player : players) {
                    ZoneEcho.network.sendTo(new PacketPlayAlarm(2), (EntityPlayerMP) player);
                }
            }
            if(Objects.equals(args[0], "gaz")) {
                for(EntityPlayer player : players) {
                    ZoneEcho.network.sendTo(new PacketPlayAlarm(3), (EntityPlayerMP) player);
                }
            }
            if(Objects.equals(args[0], "problem")) {
                for(EntityPlayer player : players) {
                    ZoneEcho.network.sendTo(new PacketPlayAlarm(4), (EntityPlayerMP) player);
                }
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> list = new ArrayList<>();
        if(args.length == 1) {
            list.add("any");
            list.add("fire");
            list.add("generator");
            list.add("gaz");
            list.add("problem");
        }
        return list;
    }
}