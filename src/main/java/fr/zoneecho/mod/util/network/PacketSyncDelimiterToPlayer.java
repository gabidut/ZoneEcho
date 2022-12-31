package fr.zoneecho.mod.util.network;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketSyncDelimiterToPlayer implements IMessage {

    int player;

    public PacketSyncDelimiterToPlayer() {
    }

    public PacketSyncDelimiterToPlayer(EntityPlayer player) {
        this.player = player.getEntityId();
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.player = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.player);
    }

    public static class Handler implements IMessageHandler<PacketSyncDelimiterToPlayer, IMessage> {
        @Override
        @SideOnly(Side.SERVER)
        public IMessage onMessage(PacketSyncDelimiterToPlayer message, MessageContext ctx) {
            EntityPlayer e = (EntityPlayer) ctx.getServerHandler().player.world.getEntityByID(message.player);
            assert e != null;
            Databases.getPlayerData(e).setString("delimiter", ZoneEcho.dbUtils.getString("delimiter"));
            ZoneEcho.network.sendTo(new PacketOpenDelimiter(), (EntityPlayerMP) e);

            return null;
        }
    }
}
