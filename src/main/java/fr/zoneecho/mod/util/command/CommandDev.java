package fr.zoneecho.mod.util.command;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.objects.AccessJobAccred;
import fr.zoneecho.mod.objects.Rapport;
import fr.zoneecho.mod.util.Intranet;
import fr.zoneecho.mod.util.network.PacketSetBleeding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Date;
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
        if(args.length >= 1) {
            if(Objects.equals(args[0], "unbleed")) {
                ZoneEcho.network.sendTo(new PacketSetBleeding(), (EntityPlayerMP) sender);
            }
            if(Objects.equals(args[0], "addIntranet")) {
                Intranet.addRapport(new Rapport("Hello, i'm the title", "&4Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pulvinar velit eget vestibulum posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In quis mi iaculis, mollis lacus consectetur, luctus erat. Etiam eget hendrerit magna. Cras consequat hendrerit sapien nec pellentesque. Vestibulum a ante at arcu viverra consequat. Proin ornare turpis in hendrerit iaculis. Nunc non ante finibus, iaculis turpis vitae, mattis nibh. Etiam luctus, nisi et maximus rutrum, eros lacus tincidunt metus, ut tempor tellus eros quis mi. ", new Date().toString(), "Gabidut76 le best", true, false, new AccessJobAccred(0).convertToString()));
            }
            if(Objects.equals(args[0], "dumpIntranet")) {
                System.out.println(Intranet.getAllRapports());
            }
        } else {
            try {
                ZoneEcho.launchSocket();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}