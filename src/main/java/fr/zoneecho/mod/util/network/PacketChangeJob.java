package fr.zoneecho.mod.util.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketChangeJob implements IMessage {

    private String perso;
    int player;
    public PacketChangeJob(EntityPlayer player, String perso) {
        this.perso = perso;
        this.player=player.getEntityId();
    }

    public PacketChangeJob() {

    }


    @Override
    public void fromBytes(ByteBuf buf) {
        /// jobname = ByteBufUtils.readUTF8String(buf);
        // i = buf.readInt();
        this.player= buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        // ByteBufUtils.writeUTF8String(buf, jobname);
        // buf.writeInt(i);
        buf.writeInt(this.player);

    }

    public static class Handler implements IMessageHandler<PacketChangeJob, IMessage> {
        @Override
        @SideOnly(Side.SERVER)
        public IMessage onMessage(PacketChangeJob message, MessageContext ctx) {
            EntityPlayer e = (EntityPlayer) ctx.getServerHandler().player.world.getEntityByID(message.player);


            return null;
        }
    }
}
