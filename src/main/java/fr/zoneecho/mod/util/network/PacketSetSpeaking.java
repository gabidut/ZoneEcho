package fr.zoneecho.mod.util.network;

import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class PacketSetSpeaking implements IMessage {
    private String name;
    private int val;

    public PacketSetSpeaking() {
    }

    public PacketSetSpeaking(String name, int val) {
        this.name = name;
        this.val = val;
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
        val = ByteBufUtils.readVarInt(buf, 2);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        ByteBufUtils.writeVarInt(buf, val, 1);
    }

    public static class Handler implements IMessageHandler<PacketSetSpeaking, IMessage> {
        @Override
        public IMessage onMessage(PacketSetSpeaking message, MessageContext ctx) {
            Database db = Databases.getPlayerData(message.name);
            db.setInteger("speak", message.val);
            return null;
        }
    }
}
