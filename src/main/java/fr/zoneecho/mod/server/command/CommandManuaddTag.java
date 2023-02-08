package fr.zoneecho.mod.server.command;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketOpenComputer;
import fr.zoneecho.mod.common.network.PacketSetBleeding;
import fr.zoneecho.mod.common.utils.PlayableJobs;
import fr.zoneecho.mod.common.utils.Rapport;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;

import java.util.Date;
import java.util.Objects;

public class CommandManuaddTag extends CommandBase {



    @Override
    public String getName() {
        return "lockdowncard";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "lockdowncard";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length >= 1 && sender instanceof EntityPlayer) {
            if (Objects.equals(args[0], "openpc")) {
                ZoneEcho.network.sendTo(new PacketOpenComputer(), (EntityPlayerMP) sender);
                sender.sendMessage(new TextComponentString("§aOuverture de l'ordinateur"));
                return;
            }
            if (args[0].equals("dbgmyself")) {
                Databases.getPlayerData((EntityPlayer) sender).setString("identity", "Sébastien;Chaudrais");
                sender.sendMessage(new TextComponentString(Databases.getPlayerData((EntityPlayer) sender).getString("identity")));
            }
            if(Objects.equals(args[0], "read")) {
                EntityPlayer player = (EntityPlayer) sender;
                ItemStack itemStack = player.getHeldItemMainhand();
                if(player.getPrimaryHand() == EnumHandSide.RIGHT) {
                    player.getHeldItemMainhand();
                } else {
                    player.getHeldItemOffhand();
                }
                if(Objects.isNull(itemStack.getTagCompound())) {
                    sender.sendMessage(new TextComponentString("NO NBT."));
                } else {
                    sender.sendMessage(new TextComponentString("NBT OF HELD ITEM: " + itemStack.getTagCompound()));
                }
            } else if(Objects.equals(args[0], "writeExemple")) {
                EntityPlayer player = (EntityPlayer) sender;
                ItemStack stack = player.getHeldItemMainhand();
                if(player.getPrimaryHand() == EnumHandSide.RIGHT) {
                    player.getHeldItemMainhand();
                } else {
                    player.getHeldItemOffhand();
                }
                NBTTagCompound nbt;
                if (stack.hasTagCompound())
                {
                    nbt = stack.getTagCompound();
                }
                else
                {
                    nbt = new NBTTagCompound();
                }

                assert nbt != null;

                nbt.setString("data", Rapport.toString(new Rapport("Hello, i'm the title", " And this is my content. I can be in &2GREEN or, &cRED, ...", new Date().toString(), "Gabidut76 le best", true, false, PlayableJobs.admin_dir.name())));
                stack.setTagCompound(nbt);
            } else if(Objects.equals(args[0], "writeExemple2")) {
                EntityPlayer player = (EntityPlayer) sender;
                ItemStack stack = player.getHeldItemMainhand();
                if(player.getPrimaryHand() == EnumHandSide.RIGHT) {
                    player.getHeldItemMainhand();
                } else {
                    player.getHeldItemOffhand();
                }
                NBTTagCompound nbt;
                if (stack.hasTagCompound())
                {
                    nbt = stack.getTagCompound();
                }
                else
                {
                    nbt = new NBTTagCompound();
                }

                assert nbt != null;


                nbt.setString("data", Rapport.toString(new Rapport("Hello, i'm the title", "&4Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pulvinar velit eget vestibulum posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In quis mi iaculis, mollis lacus consectetur, luctus erat. Etiam eget hendrerit magna. Cras consequat hendrerit sapien nec pellentesque. Vestibulum a ante at arcu viverra consequat. Proin ornare turpis in hendrerit iaculis. Nunc non ante finibus, iaculis turpis vitae, mattis nibh. Etiam luctus, nisi et maximus rutrum, eros lacus tincidunt metus, ut tempor tellus eros quis mi. ", new Date().toString(), "Gabidut76 le best", true, false, PlayableJobs.admin_dir.name())));
                stack.setTagCompound(nbt);
            } else if(Objects.equals(args[0], "unbleed")) {
                ZoneEcho.network.sendTo(new PacketSetBleeding(false), (EntityPlayerMP) sender);
            } else if(Objects.equals(args[0], "bleed")) {
                ZoneEcho.network.sendTo(new PacketSetBleeding(true), (EntityPlayerMP) sender);
            } else if (Objects.equals(args[0], "test")) {
                EntityPlayerMP player = (EntityPlayerMP) sender;
                System.out.println(player.getLookVec());

                BlockPos pos = player.getPosition().subtract(new Vec3i(player.getLookVec().x, player.getLookVec().y, player.getLookVec().z));

                System.out.println(pos);



                System.out.println(player.world.getBlockState(pos).getBlock());

            } else {
                EntityPlayer p = (EntityPlayer) sender.getCommandSenderEntity();
                assert p != null;
                ItemStack stack = p.inventory.getCurrentItem();
                NBTTagCompound nbt;
                if (stack.hasTagCompound())
                {
                    nbt = stack.getTagCompound();
                }
                else
                {
                    nbt = new NBTTagCompound();
                }

                assert nbt != null;
                nbt.setBoolean(args[0], true);

                stack.setTagCompound(nbt);

                sender.sendMessage(new TextComponentString(p.inventory.getCurrentItem().serializeNBT().toString()));
            }
        }
    }
}