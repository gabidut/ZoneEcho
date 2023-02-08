package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.client.css.GuiSpe;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenSpe implements IMessage {
    private String spe;
    private int lvlS1;

    public PacketOpenSpe() {
    }

    public PacketOpenSpe(String spe, int lvlS1) {
        this.spe = spe;
        this.lvlS1 = lvlS1;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        lvlS1 = ByteBufUtils.readVarInt(buf, 5);
        spe = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, lvlS1, 5);
        ByteBufUtils.writeUTF8String(buf, spe);
    }

    public static class Handler implements IMessageHandler<PacketOpenSpe, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenSpe message, MessageContext ctx) {
            ACsGuiApi.asyncLoadThenShowGui("spe", () -> new GuiSpe(message.spe, message.lvlS1));
            return null;
        }
    }
}
