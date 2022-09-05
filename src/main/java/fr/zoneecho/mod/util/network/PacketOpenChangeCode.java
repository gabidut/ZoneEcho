package fr.zoneecho.mod.util.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.util.css.GuiKeypadChangeCode;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenChangeCode implements IMessage {

    public String pos;
    public String code;


    public PacketOpenChangeCode() {
    }

    public PacketOpenChangeCode(String pos, String code) {
        this.pos = pos;
        this.code = code;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = ByteBufUtils.readUTF8String(buf);
        this.code = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.pos);
        ByteBufUtils.writeUTF8String(buf, this.code);
    }


    public static class Handler implements IMessageHandler<PacketOpenChangeCode, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenChangeCode message, MessageContext ctx) {
            ACsGuiApi.asyncLoadThenShowGui("changecodekeypad",() -> new GuiKeypadChangeCode(message.pos, message.code));
            return null;
        }
    }
}
