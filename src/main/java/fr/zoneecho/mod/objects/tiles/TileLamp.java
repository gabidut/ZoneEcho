package fr.zoneecho.mod.objects.tiles;


import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.tiles.TileEntitySyncClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

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
    public void update() {
        if(!world.isRemote) {
            if(Objects.equals(Databases.getDatabase("zoneecho_utils").getString("lamps"), "yellow")) {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Yellow.getDefaultState());
            } else if (Objects.equals(Databases.getDatabase("zoneecho_utils").getString("lamps"), "red")) {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Red.getDefaultState());
            } else {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2fGray.getDefaultState());
            }
            syncToClient(Objects.requireNonNull(getWorld().getTileEntity(getPos())));
            upd();
            this.markDirty();
            markCollisionsDirty();
            markDirty();

        }
    }
    private void upd() {
        getWorld().notifyNeighborsOfStateChange(getPos(), ZoneEcho.lampV2fGray, false);
        getWorld().notifyLightSet(getPos());
    }
}
