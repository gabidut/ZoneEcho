package fr.zoneecho.mod.util.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.util.css.GuiKeypad;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenKeypad implements IMessage {


    private int x;
    private int y;
    private int z;



    public PacketOpenKeypad() {
    }


    public PacketOpenKeypad(BlockPos pos, World worldIn) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();


    }


    @Override
    public void fromBytes(ByteBuf buf) {
        x = ByteBufUtils.readVarShort(buf);
        y = ByteBufUtils.readVarShort(buf);
        z = ByteBufUtils.readVarShort(buf);


    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarShort(buf, x);
        ByteBufUtils.writeVarShort(buf, y);
        ByteBufUtils.writeVarShort(buf, z);
    }


    public static class Handler implements IMessageHandler<PacketOpenKeypad, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenKeypad message, MessageContext ctx) {
            // System.out.println(Objects.requireNonNull(message.worldIn.getTileEntity(new BlockPos(message.x, message.y, message.z))).getTileData());

            // World world = ctx.getServerHandler().player.world;

            // NBTTagCompound t = world.getTileEntity(new BlockPos(message.x, message.y, message.z)).getTileData();

            // System.out.println(world);

            // String code = Objects.requireNonNull(message.worldIn.getTileEntity(new BlockPos(message.x, message.y, message.z))).getTileData().getString("code");

            ACsGuiApi.asyncLoadThenShowGui("keypad",() -> new GuiKeypad(message.x + "," + message.y + "," + message.z, "1234"));
            return null;
        }
    }
}
