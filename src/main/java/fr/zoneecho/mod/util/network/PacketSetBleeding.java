package fr.zoneecho.mod.util.network;

import fr.zoneecho.mod.proxy.ClientProxy;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSetBleeding implements IMessage {

    private Boolean bleeding;

    public PacketSetBleeding() {
        bleeding = true;
    }

    public PacketSetBleeding(Boolean bleeding) {
        this.bleeding = bleeding;
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        bleeding = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(bleeding);
    }


    public static class Handler implements IMessageHandler<PacketSetBleeding, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketSetBleeding message, MessageContext ctx) {
            ClientProxy.isBleeding = message.bleeding;
            return null;
        }
    }
}
