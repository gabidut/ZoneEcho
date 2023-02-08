package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.client.css.GuiDelimiter;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketOpenDelimiter implements IMessage {


    public PacketOpenDelimiter() {

    }


    @Override
    public void fromBytes(ByteBuf buf) {


    }

    @Override
    public void toBytes(ByteBuf buf) {
        // ByteBufUtils.writeUTF8String(buf, jobname);
        // buf.writeInt(i);

    }

    public static class Handler implements IMessageHandler<PacketOpenDelimiter, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenDelimiter message, MessageContext ctx) {
            if(Minecraft.getMinecraft().player.isCreative()) {
                ACsGuiApi.asyncLoadThenShowGui("Delimiter", GuiDelimiter::new);
            }

            return null;
        }
    }
}
