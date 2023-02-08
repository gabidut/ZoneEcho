package fr.zoneecho.mod.common.blocks.tiles;

import fr.dynamx.common.blocks.TEDynamXBlock;
import fr.dynamx.common.contentpack.type.objects.BlockObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TileEntitySyncClient extends TEDynamXBlock {
    public TileEntitySyncClient(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
    }
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(pos, getBlockMetadata(), getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return writeToNBT(new NBTTagCompound());
    }

    public void sync()
    {
        syncToClient(this);
    }

    public void syncToClient()
    {
        this.markDirty();
        if(!world.isRemote)
        {
            if(world instanceof WorldServer)
            {
                WorldServer server = (WorldServer) world;
                PlayerChunkMapEntry entry = server.getPlayerChunkMap().getEntry(pos.getX() >> 4, pos.getZ() >> 4);
                if(entry != null)
                {
                    SPacketUpdateTileEntity packet = getUpdatePacket();
                    if(packet != null)
                    {
                        entry.sendPacket(packet);
                    }
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound)
    {
        super.readFromNBT(tagCompound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
    {
        super.writeToNBT(tagCompound);
        return tagCompound;
    }

    public static void markBlockForUpdate(World world, BlockPos pos)
    {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
    }

    public static void syncToClient(TileEntity tileEntity)
    {
        World world = tileEntity.getWorld();
        if(world != null)
        {
            BlockPos pos = tileEntity.getPos();
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }
    }
}
