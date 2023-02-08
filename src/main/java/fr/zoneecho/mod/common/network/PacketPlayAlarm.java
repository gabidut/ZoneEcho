package fr.zoneecho.mod.common.network;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.common.blocks.SoundInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketPlayAlarm implements IMessage {
    /*
    ids:
    0: alarm-any

     */
    private int id;

    public PacketPlayAlarm() {
        this.id = 0;
    }
    public PacketPlayAlarm(int id) {
        this.id = id;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.id);
    }

    public static class Handler implements IMessageHandler<PacketPlayAlarm, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)

        public IMessage onMessage(PacketPlayAlarm message, MessageContext ctx) {
            if(message.id == 0) {
                Databases.getDatabase("zoneecho_utils").setString("lights", "red");
                EntityPlayer player = Minecraft.getMinecraft().player;
                player.playSound(SoundInit.ALARM_ANY, 100.0F, 1.0F);
            }
            if(message.id == 1) {
                Databases.getDatabase("zoneecho_utils").setString("lights", "red");
                EntityPlayer player = Minecraft.getMinecraft().player;
                player.playSound(SoundInit.SOUND_FIRE, 100.0F, 1.0F);
            }
            if(message.id == 2) {
                Databases.getDatabase("zoneecho_utils").setString("lights", "red");
                EntityPlayer player = Minecraft.getMinecraft().player;
                player.playSound(SoundInit.SOUND_GENERATOR, 100.0F, 1.0F);
            }
            if(message.id == 3) {
                Databases.getDatabase("zoneecho_utils").setString("lights", "red");
                EntityPlayer player = Minecraft.getMinecraft().player;
                player.playSound(SoundInit.SOUND_GAZ, 100.0F, 1.0F);
            }
            if(message.id == 4) {
                Databases.getDatabase("zoneecho_utils").setString("lights", "red");
                EntityPlayer player = Minecraft.getMinecraft().player;
                player.playSound(SoundInit.SOUND_PROBLEM, 100.0F, 1.0F);
            }

            return null;
        }
    }
}
