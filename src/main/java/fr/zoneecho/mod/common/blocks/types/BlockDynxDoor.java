package fr.zoneecho.mod.common.blocks.types;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.tiles.TileEntitySyncClient;
import fr.zoneecho.mod.common.tiles.TileDoor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDynxDoor extends DynamXBlock {
    public BlockDynxDoor(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return true;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {

            TileEntitySyncClient tileentity = (TileEntitySyncClient) worldIn.getTileEntity(pos);

            BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            if (worldIn.isBlockPowered(pos2)) {
                System.out.println("ok1");
                assert tileentity != null;
                tileentity.getTileData().setBoolean("isopening", true);
                tileentity.syncToClient();
            }
            if (worldIn.isBlockPowered(pos)) {
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
        return new TileDoor(blockObjectInfo);
    }

}
