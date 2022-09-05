package fr.zoneecho.mod.util.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.util.css.computer.GuiHomeOS;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenComputer implements IMessage {


    public PacketOpenComputer() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
    }


    public static class Handler implements IMessageHandler<PacketOpenComputer, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenComputer message, MessageContext ctx) {
            ACsGuiApi.asyncLoadThenShowGui("computer",() -> new GuiHomeOS());
            return null;
        }
    }
}
