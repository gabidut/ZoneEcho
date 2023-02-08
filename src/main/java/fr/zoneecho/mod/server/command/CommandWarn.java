package fr.zoneecho.mod.server.command;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketSendToast;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CommandWarn extends CommandBase {


    @Override
    public String getName() {
        return "warn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "warn";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TextComponentString("§cUsage: /warn <player> <reason>"));
            return;
        }
        EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);

        if (player == null) {
            sender.sendMessage(new TextComponentString("§cPlayer not found."));
            return;
        } else {
            String reason = "";
            for (int i = 1; i < args.length; i++) {
                reason += args[i] + " ";
            }

            Integer warnCount = Databases.getDatabase("warns").getInteger(player.getUniqueID().toString());
            System.out.println(warnCount);
            Databases.getPlayerData(player).setString("warn" + warnCount + 1, Date.from(Instant.now()).toInstant().toEpochMilli()  + ":" + sender.getName() + ":" + reason);
            Databases.getDatabase("warns").setInteger(player.getUniqueID().toString(), warnCount + 1);

            sender.sendMessage(new TextComponentString("§a\uD83D\uDD95 Warned " + player.getName() + " ("+ warnCount + ") for " + reason));
            ZoneEcho.network.sendTo(new PacketSendToast("§6§l\uD83D\uDD95 AVERTISSEMENT.", "§e" + reason), (EntityPlayerMP) player);
        }


    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 1) {
            List<String> players = new ArrayList<>();
            for (EntityPlayer player : server.getPlayerList().getPlayers()) {
                players.add(player.getName());
            }
            return players;
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }
}