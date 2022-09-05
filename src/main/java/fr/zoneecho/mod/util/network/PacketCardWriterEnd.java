package fr.zoneecho.mod.util.network;

import fr.zoneecho.mod.init.ItemInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class PacketCardWriterEnd implements IMessage {
    private String name;
    private String job;
    private int lvl;
    private int intern;
    private int bypasslockdown;

    public PacketCardWriterEnd() {
    }

    public PacketCardWriterEnd(String name, String job, int lvl, int intern, int bypasslockdown) {
        this.name = name;
        this.job = job;
        this.lvl = lvl;
        this.intern = intern;
        this.bypasslockdown = bypasslockdown;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
        job = ByteBufUtils.readUTF8String(buf);
        lvl = ByteBufUtils.readVarInt(buf, 5);
        intern = ByteBufUtils.readVarInt(buf, 5);
        bypasslockdown = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        ByteBufUtils.writeUTF8String(buf, job);
        ByteBufUtils.writeVarInt(buf, lvl, 5);
        ByteBufUtils.writeVarInt(buf, intern, 5);
        ByteBufUtils.writeVarInt(buf, bypasslockdown, 5);
    }

    public static class Handler implements IMessageHandler<PacketCardWriterEnd, IMessage> {
        @Override
        public IMessage onMessage(PacketCardWriterEnd message, MessageContext ctx) {
            boolean intern = false;
            intern = message.intern == 1;
            System.out.println(ctx.getServerHandler().player.getName());
            EntityPlayerMP p = ctx.getServerHandler().player;

            ItemStack item = new ItemStack(ItemInit.DEBUG_TOOL);
            if(message.lvl == 0) {
                 item = new ItemStack(ItemInit.KEYCARD0);
            }
            if(message.lvl == 1) {
                 item = new ItemStack(ItemInit.KEYCARD1);
            }
            if(message.lvl == 2) {
                 item = new ItemStack(ItemInit.KEYCARD2);
            }
            if(message.lvl == 3) {
                 item = new ItemStack(ItemInit.KEYCARD3);
            }
            if(message.lvl == 4) {
                 item = new ItemStack(ItemInit.KEYCARD4);
            }
            if(message.lvl == 5) {
                item = new ItemStack(ItemInit.KEYCARD5);
            }

            item.setStackDisplayName(TextFormatting.RED + "Carte d'accreditation.");


            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound display = new NBTTagCompound();
            NBTTagList list = new NBTTagList();

            if(intern) {
                String msgbool = TextFormatting.DARK_RED + "Cette personne est un interne. Merci de toujours être accompagné d'un supérieur.";
                list.appendTag(new NBTTagString( msgbool));
            }
            list.appendTag(new NBTTagString( TextFormatting.RED + "Carte de : " + message.name));
            list.appendTag(new NBTTagString( TextFormatting.RED + "Secteur : " + message.job));
            display.setTag("Lore", list);
            nbt.setTag("display", display);
            item.setTagCompound(nbt);

            p.inventory.addItemStackToInventory(item);
            return null;
        }
    }
}
