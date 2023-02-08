package fr.zoneecho.mod.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileJobReader extends TileEntity {
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        nbtTag.getInteger("restricted");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return super.writeToNBT(compound);
    }
}
