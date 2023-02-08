package fr.zoneecho.mod.common.tiles;


import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import fr.dynamx.common.blocks.TEDynamXBlock;
import fr.dynamx.common.contentpack.type.objects.BlockObject;
import fr.dynamx.utils.optimization.MutableBoundingBox;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.tiles.TileEntitySyncClient;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TileDoorO extends TileEntitySyncClient implements ITickable {

    public TileDoorO(BlockObject<?> blockObjectInfo) {
        super(blockObjectInfo);
    }

    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        nbtTag.getInteger("step");
        nbtTag.getInteger("step2");
        nbtTag.getBoolean("isopening");
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return true;
    }


    @Override
    public void update() {
        if(getTileData().getBoolean("isopening")) {

            if(getTileData().getInteger("step") >= 60) {
                world.setBlockState(getPos(), ZoneEcho.door.getDefaultState());
                ((TEDynamXBlock) Objects.requireNonNull(world.getTileEntity(getPos()))).setRotation(this.getRotation());
            }
            if(getTileData().getInteger("step") >= 20) {
                markDirty();
                markCollisionsDirty();
            }
            getTileData().setInteger("step", getTileData().getInteger("step") + 2);
            getTileData().setInteger("step2", getTileData().getInteger("step2") - 2);
            markCollisionsDirty();

        }
    }

    @Override
    public List<MutableBoundingBox> getUnrotatedCollisionBoxes() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public CompoundCollisionShape getPhysicsCollision() {
        return new CompoundCollisionShape();
    }
}
