package fr.zoneecho.mod.common.network;

import fr.zoneecho.mod.client.css.ErrorToast;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketSendToast implements IMessage {

    private String title;
    private String content;


    public PacketSendToast() {
    }

    public PacketSendToast(String title, String content) {
        this.title = title;
        this.content = content;
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        this.title = ByteBufUtils.readUTF8String(buf);
        this.content = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.title);
        ByteBufUtils.writeUTF8String(buf, this.content);
    }

    public static class Handler implements IMessageHandler<PacketSendToast, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketSendToast message, MessageContext ctx) {
            Minecraft.getMinecraft().getToastGui().add(new ErrorToast(ErrorToast.Type.NARRATOR_TOGGLE, new TextComponentString(message.title), new TextComponentString(message.content)));

            return null;
        }
    }
}
