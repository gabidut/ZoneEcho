package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.client.css.GuiPersoCreate;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenCreatePerso implements IMessage {


    public PacketOpenCreatePerso() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
    }


    public static class Handler implements IMessageHandler<PacketOpenCreatePerso, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenCreatePerso message, MessageContext ctx) {
            ACsGuiApi.asyncLoadThenShowGui("createperso", GuiPersoCreate::new);
            return null;
        }
    }
}
