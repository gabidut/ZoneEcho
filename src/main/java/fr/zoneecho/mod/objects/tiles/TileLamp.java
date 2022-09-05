package fr.zoneecho.mod.objects.tiles;


import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.tiles.TileEntitySyncClient;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class TileLamp extends TileEntitySyncClient implements ITickable {

    private int newState = 0;
    public TileLamp(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
    }

    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        nbtTag.getInteger("state");
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return true;
    }


    @Override
    public void update() {
        if(!world.isRemote) {
            if(Objects.equals(Databases.getDatabase("zoneecho_utils").getString("lamps"), "yellow")) {
                upd();
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Yellow.getDefaultState());
            } else if (Objects.equals(Databases.getDatabase("zoneecho_utils").getString("lamps"), "red")) {
                upd();
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Red.getDefaultState());
            } else {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2fGray.getDefaultState());
                upd();
            }
            syncToClient(Objects.requireNonNull(getWorld().getTileEntity(getPos())));
        }
    }
    private void upd() {
        getWorld().notifyNeighborsOfStateChange(getPos(), ZoneEcho.lampV2fGray, false);
        getWorld().notifyLightSet(getPos());
    }
}