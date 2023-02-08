package fr.zoneecho.mod.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketInitSpe implements IMessage {

    public PacketInitSpe() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
    }


    public static class Handler implements IMessageHandler<PacketInitSpe, IMessage> {
        @Override
        public IMessage onMessage(PacketInitSpe message, MessageContext ctx) {
            System.out.println(ctx.getServerHandler().player.getUniqueID());
            // ZoneEcho.db.setInteger(String.valueOf(ctx.getServerHandler().player.getUniqueID()), 1);
            ctx.getServerHandler().player.sendMessage(new TextComponentString("Bienvenue, " + ctx.getServerHandler().player.getDisplayName() + " sur le serveur !"));
            // Database pData = Databases.getPlayerData(ctx.getServerHandler().player);
            // pData.setString("spe", "Aucune");
            // pData.setInteger("level", 0);
            return null;
        }
    }
}
