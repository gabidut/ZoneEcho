package fr.zoneecho.mod.common.blocks.types;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.tiles.TileEntitySyncClient;
import fr.zoneecho.mod.common.tiles.TileDoorO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDynxDoorO extends DynamXBlock {
    public BlockDynxDoorO(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        setCreativeTab(ZoneEcho.ZONEECHO_POUBELLE);
        setBlockUnbreakable();
        canCollideCheck(this.getDefaultState(), false);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Nullable
    @Override
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
        return null;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
    }
    @Override
    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
        return false;
    }


    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {

            TileEntitySyncClient tileentity = (TileEntitySyncClient) worldIn.getTileEntity(pos);
            assert tileentity != null;
            tileentity.markCollisionsDirty();
            tileentity.markDirty();
            BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            if (!worldIn.isBlockPowered(pos2)) {
                System.out.println("ok1");
                assert tileentity != null;
                tileentity.getTileData().setBoolean("isopening", true);
                tileentity.syncToClient();
            }
            if (!worldIn.isBlockPowered(pos)) {
                System.out.println("ok2");
                assert tileentity != null;
                tileentity.getTileData().setBoolean("isopening", true);
                tileentity.syncToClient();
            }
        }
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileDoorO(blockObjectInfo);
    }
}
