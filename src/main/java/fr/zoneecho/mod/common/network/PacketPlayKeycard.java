package fr.zoneecho.mod.common.network;

import fr.zoneecho.mod.common.blocks.SoundInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketPlayKeycard implements IMessage {

    private int player;

    public PacketPlayKeycard() {
    }
    public PacketPlayKeycard(int player) {
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

    public static class Handler implements IMessageHandler<PacketPlayKeycard, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketPlayKeycard message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            if(message.player == 1) {
                player.playSound(SoundInit.KEYCARD_OK, 1.0F, 1.0F);
            } else {
                player.playSound(SoundInit.KEYCARD_NO, 1.0F, 1.0F);
            }

            return null;
        }
    }
}
