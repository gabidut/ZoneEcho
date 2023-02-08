package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.client.css.computer.GuiReadRapport;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenRapport implements IMessage {

    private NBTTagCompound nbt;

    public PacketOpenRapport() {
    }

    public PacketOpenRapport(NBTTagCompound tagCompound) {
        this.nbt = tagCompound;
        System.out.println(tagCompound);
    }



    @Override
    public void fromBytes(ByteBuf buf) {

        this.nbt = ByteBufUtils.readTag(buf);
        System.out.println(this.nbt);

    }

    @Override
    public void toBytes(ByteBuf buf) {

            ByteBufUtils.writeTag(buf, nbt);
        System.out.println(this.nbt);

    }


    public static class Handler implements IMessageHandler<PacketOpenRapport, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenRapport message, MessageContext ctx) {
            System.out.println(message.nbt);
            ACsGuiApi.asyncLoadThenShowGui("rapport",() -> new GuiReadRapport(message.nbt));
            return null;
        }
    }
}
