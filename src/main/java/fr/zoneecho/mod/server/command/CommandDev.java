package fr.zoneecho.mod.server.command;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.utils.Delimiter;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

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

        if(args.length == 0) {
            List<AxisAlignedBB> aabb = new ArrayList<>();

            new AxisAlignedBB(0, 0, 0, 1,1,1);
            Delimiter delimiter = new Delimiter(aabb);
            System.out.println(delimiter.toString());
            ZoneEcho.dbUtils.setString("delimiter", delimiter.toString());
        }
//        if (Objects.equals(args[0], "addIntranet")) {
//            Intranet.addRapport(new Rapport("Hello, i'm the title", "&4Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pulvinar velit eget vestibulum posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In quis mi iaculis, mollis lacus consectetur, luctus erat. Etiam eget hendrerit magna. Cras consequat hendrerit sapien nec pellentesque. Vestibulum a ante at arcu viverra consequat. Proin ornare turpis in hendrerit iaculis. Nunc non ante finibus, iaculis turpis vitae, mattis nibh. Etiam luctus, nisi et maximus rutrum, eros lacus tincidunt metus, ut tempor tellus eros quis mi. ", new Date().toString(), "Gabidut76 le best", true, false, PlayableJobs.spe_ut.name()));
//        }
//        if (Objects.equals(args[0], "dumpIntranet")) {
//            System.out.println(Intranet.getAllRapports());
//        }

    }
}