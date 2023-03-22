package fr.zoneecho.mod.common.tiles;


import fr.dynamx.common.DynamXContext;
import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.tiles.TileEntitySyncClient;
import fr.zoneecho.mod.server.utils.LampMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileLamp extends TileEntitySyncClient implements ITickable {

    private BlockObject b;
    public TileLamp(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
        System.out.println("Used constructor with BlockObject");
        this.b = blockObjectInfo;
    }

    public TileLamp() {
        super(null);
        System.out.println("Used empty constructor");
    }

    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        return tagCompound;
    }

    @Override
    public void update() {
        if(!this.world.isRemote) {
            getTileData().setString("state", ZoneEcho.alarmMode.name());

            if(ZoneEcho.alarmMode.equals(LampMode.YELLOW)) {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Yellow.getDefaultState());
            } else if (ZoneEcho.alarmMode.equals(LampMode.RED)) {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2Red.getDefaultState());
            } else {
                getWorld().setBlockState(getPos(), ZoneEcho.lampV2fGray.getDefaultState());
            }
            sync();
        }


        DynamXContext.getPhysicsWorld().schedule(this::markCollisionsDirty);
        this.computeBoundingBox();
        markDirty();

        markBlockForUpdate(this.world, this.pos);
        getWorld().notifyLightSet(getPos());


    }
}
