package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.client.css.GuiJobReader;
import fr.zoneecho.mod.common.utils.Things;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketOpenJobReader implements IMessage {

    private String pos;

    public PacketOpenJobReader() {
    }

    public PacketOpenJobReader(BlockPos pos) {
        this.pos = Things.blockPosToString(pos);
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.pos);
    }

    public static class Handler implements IMessageHandler<PacketOpenJobReader, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenJobReader message, MessageContext ctx) {
            System.out.println("PacketOpenJobReader");
            ACsGuiApi.asyncLoadThenShowGui("jobreader", () -> new GuiJobReader(message.pos));
            return null;
        }
    }
}
