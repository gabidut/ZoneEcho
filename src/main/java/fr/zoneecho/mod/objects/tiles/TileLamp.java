package fr.zoneecho.mod.objects.tiles;


import fr.dynamx.common.DynamXContext;
import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.tiles.TileEntitySyncClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

import java.util.Objects;

public class TileLamp extends TileEntitySyncClient implements ITickable {

    private BlockObject b;
    private String state = "";
    public TileLamp(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
        this.b = blockObjectInfo;
    }

    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        nbtTag.getInteger("state");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setString("state", state);
        return tagCompound;
    }

    @Override
    public void update() {
        if(!this.world.isRemote) {
            if(Objects.isNull(Databases.getDatabase("zoneecho_utils").getString("lamps")) || Objects.equals(Databases.getDatabase("zoneecho_utils").getString("lamps"), "")) { // I don't understand why but sometimes the database is null
                Databases.getDatabase("zoneecho_utils").setString("lamps", "yellow");
                getTileData().setString("state", "yellow");
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Yellow.getDefaultState());
            }
            getTileData().setString("state", Databases.getDatabase("zoneecho_utils").getString("lamps"));
            getTileData().setString("state", "yellow");
            sync();
        }
        if(Objects.equals(getTileData().getString("state"), "yellow")) {
            getWorld().setBlockState(getPos(), ZoneEcho.lampV2Yellow.getDefaultState());
        } else if (Objects.equals(getTileData().getString("state"), "red")) {
            getWorld().setBlockState(getPos(), ZoneEcho.lampV2Red.getDefaultState());
        } else {
            getWorld().setBlockState(getPos(), ZoneEcho.lampV2fGray.getDefaultState());
        }

        DynamXContext.getPhysicsWorld().schedule(this::markCollisionsDirty);
        this.computeBoundingBox();
        markDirty();

        markBlockForUpdate(this.world, this.pos);
        getWorld().notifyLightSet(getPos());


    }
}
