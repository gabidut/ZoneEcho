package fr.zoneecho.mod.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketKeypad implements IMessage {


    String pos, text, color;

    public PacketKeypad() {}

    public PacketKeypad(String pos){

        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, this.pos);

    }

    public static class Handler implements IMessageHandler<PacketKeypad, IMessage> {
        @Override
        @SideOnly(Side.SERVER)
        public IMessage onMessage(PacketKeypad m, MessageContext ctx) {

            return null;
        }
    }
}
