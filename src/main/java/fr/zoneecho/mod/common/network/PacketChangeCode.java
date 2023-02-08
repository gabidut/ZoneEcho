package fr.zoneecho.mod.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketChangeCode implements IMessage {

    String pos, code;

    public PacketChangeCode() {
    }

    public PacketChangeCode(String pos, String code) {
        this.pos = pos;
        this.code = code;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        pos = ByteBufUtils.readUTF8String(buf);
        code = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, pos);
        ByteBufUtils.writeUTF8String(buf, code);
    }


    public static class Handler implements IMessageHandler<PacketChangeCode, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketChangeCode m, MessageContext ctx) {
            return null;
        }
    }
}
