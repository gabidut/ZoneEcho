package fr.zoneecho.mod.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileCardReader extends TileEntity {
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    String securitylevel = "";

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        securitylevel = tagCompound.getString("securitylevel");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setString("securitylevel",securitylevel);
        return tagCompound;
    }

    public void setSecuritylevel(String code) {
        this.securitylevel = code;
    }

    public String getSecuritylevel() {
        return this.securitylevel;
    }



}
