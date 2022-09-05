package fr.zoneecho.mod.util.network;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.init.SoundInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketPlayAlarm implements IMessage {

    private int player;

    public PacketPlayAlarm() {
    }
    public PacketPlayAlarm(int player) {
        this.player = player;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.player = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.player);
    }

    public static class Handler implements IMessageHandler<PacketPlayAlarm, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketPlayAlarm message, MessageContext ctx) {
            Databases.getDatabase("zoneecho_utils").setString("lights", "red");
            EntityPlayer player = Minecraft.getMinecraft().player;
            player.playSound(SoundInit.ALARM_ANY, 100.0F, 1.0F);

            return null;
        }
    }
}
