package fr.zoneecho.mod.server.command;

import com.google.common.collect.Lists;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketOpenCreatePerso;
import fr.zoneecho.mod.common.utils.PlayableJobs;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CommandChooseJob extends CommandBase {



    @Override
    public String getName() {
        return "choosejob";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "choosejob";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        if(Objects.equals(args[0], "newperso")) {
            if(args.length < 2) {
                sender.sendMessage(new TextComponentString("§cNulos, /choosejob newperso <personage>"));
                return;
            }
            if(Databases.getPlayerData((EntityPlayer) sender).contains("perso_" + args[1])) {
                sender.sendMessage(new TextComponentString("§cVous avez déjà un personnage : " + Databases.getPlayerData((EntityPlayer) sender).getString("perso_" + args[1])));
            } else {
                System.out.println("CommandPerso : " + Objects.requireNonNull(PlayableJobs.listPerso.getPersoFromName(args[1])).getName());
                ZoneEcho.network.sendTo(new PacketOpenCreatePerso(Objects.requireNonNull(PlayableJobs.listPerso.getPersoFromName(args[1]))), (EntityPlayerMP) sender);
            }
        } else if(Objects.equals(args[0], "delperso")) {
            Databases.getPlayerData((EntityPlayer) sender).remove("perso_"  + args[1]);
        } else {
            sender.sendMessage(new TextComponentString("§cRappel, perso:job perso as ENUM"));
            Databases.getPlayerData((EntityPlayer) sender).setString("job", args[0]);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if(args.length == 2 && Objects.equals(args[0], "newperso"))  {
            return getListOfStringsMatchingLastWord(args, Lists.newArrayList(PlayableJobs.listPerso.getAllPerso()));
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }
}