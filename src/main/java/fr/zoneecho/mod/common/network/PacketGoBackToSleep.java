package fr.zoneecho.mod.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketGoBackToSleep implements IMessage {

    private String title;


    public PacketGoBackToSleep() {
    }

    public PacketGoBackToSleep(String title) {
        this.title = title;
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        this.title = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.title);
    }

    public static class Handler implements IMessageHandler<PacketGoBackToSleep, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketGoBackToSleep message, MessageContext ctx) {
            CrashReport.makeCrashReport(new Exception(message.title), message.title);
            return null;
        }
    }
}
