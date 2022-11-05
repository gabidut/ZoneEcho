package fr.zoneecho.mod.util.network;

import fr.zoneecho.mod.util.Things;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;


public class PacketUpdateJobReader implements IMessage {

    private String pos;
    private String job;

    private String players;

    public PacketUpdateJobReader() {
    }

    public PacketUpdateJobReader(String pos, String job, String players) {
        this.pos = pos;
        this.job = job;
        this.players = players;
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = ByteBufUtils.readUTF8String(buf);
        this.job = ByteBufUtils.readUTF8String(buf);
        this.players = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.pos);
        ByteBufUtils.writeUTF8String(buf, this.job);
        ByteBufUtils.writeUTF8String(buf, this.players);
    }

    public static class Handler implements IMessageHandler<PacketUpdateJobReader, IMessage> {
        @Override
        @SideOnly(Side.SERVER)
        public IMessage onMessage(PacketUpdateJobReader message, MessageContext ctx) {
            System.out.println("PacketUpdateJobReader");


            BlockPos pos = Things.parseBlockPosFromString(message.pos);

            World w = ctx.getServerHandler().player.world;

            System.out.println("PacketUpdateJobReader : " + message.job + " " + message.players);

            message.job += ",admin_dir,admin_diradj";
            message.players += ",gabidut76,yan36,Agnis_,Fantomatique,Lenruot";

            Objects.requireNonNull(w.getTileEntity(pos)).getTileData().setString("restricted", message.players + ";" + message.job);

            return null;
        }
    }
}
