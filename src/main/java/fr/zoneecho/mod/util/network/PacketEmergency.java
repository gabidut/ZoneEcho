package fr.zoneecho.mod.util.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketEmergency implements IMessage {

    private String msg;

    public PacketEmergency() {
    }

    public PacketEmergency(String msg) {
        this.msg = msg;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        msg = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, msg);

    }

    public static class Handler implements IMessageHandler<PacketEmergency, IMessage> {
        @Override
        public IMessage onMessage(PacketEmergency message, MessageContext ctx) {
            System.out.println("packet emergency");
            MinecraftServer server = ctx.getServerHandler().player.getServer();
            assert server != null;
            server.getPlayerList().sendMessage(new TextComponentString(TextFormatting.RED + "[HAUT-PARLEUR GLOBAL] " + message.msg));
            EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
            // TODO playsound to everyrone
            /// ctx.getServerHandler().player.world.playSound(entityPlayerMP.posX, entityPlayerMP.posY, entityPlayerMP.posZ, SoundInit.PING_SPEAKER, SoundCategory.MASTER, 10, 1, 1);
            return null;


        }
    }
}
