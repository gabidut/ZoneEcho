package fr.zoneecho.mod.util.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeKeypad implements IMessage {

    public PacketChangeKeypad() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
    }


    public static class Handler implements IMessageHandler<PacketChangeKeypad, IMessage> {
        @Override
        public IMessage onMessage(PacketChangeKeypad message, MessageContext ctx) {
            System.out.println("wip");
            return null;
        }
    }
}
