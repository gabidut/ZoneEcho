package fr.zoneecho.mod.util.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.util.css.GuiFirstLogin;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetupFirstConnect implements IMessage {

    public PacketSetupFirstConnect() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PacketSetupFirstConnect, IMessage> {
        @Override
        public IMessage onMessage(PacketSetupFirstConnect message, MessageContext ctx) {
            ACsGuiApi.asyncLoadThenShowGui("firstlogin", GuiFirstLogin::new);
            return null;
        }
    }
}
