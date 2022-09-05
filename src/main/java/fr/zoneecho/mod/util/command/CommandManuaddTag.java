package fr.zoneecho.mod.util.command;

import fr.zoneecho.mod.objects.Rapport;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHandSide;
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
        if(args.length > 1 && Objects.equals(args[0], "read") && sender instanceof EntityPlayer) {
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
        }
        if(args.length > 1 && Objects.equals(args[0], "writeExemple") && sender instanceof EntityPlayer) {
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
            nbt.setString("data", new Rapport("Hello, i'm the title", "And this is my content. I can be in &bBOLD or, &cRED, ...", new Date().toString(), "Gabidut76 le best", true, false).toString());
            stack.setTagCompound(nbt);
        }
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