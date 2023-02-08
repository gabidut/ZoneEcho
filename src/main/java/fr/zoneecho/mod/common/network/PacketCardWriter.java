package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.client.css.GuiCardWriter;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketCardWriter implements IMessage {
    private int lvl;
    public PacketCardWriter() {
    }

    public PacketCardWriter(int lvl) {
        this.lvl = lvl;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        lvl = ByteBufUtils.readVarInt(buf,5);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf,lvl, 5);
    }

    public static class Handler implements IMessageHandler<PacketCardWriter, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketCardWriter message, MessageContext ctx) {
            ACsGuiApi.asyncLoadThenShowGui("cardwriter", () -> new GuiCardWriter(message.lvl));
            return null;
        }
    }
}
