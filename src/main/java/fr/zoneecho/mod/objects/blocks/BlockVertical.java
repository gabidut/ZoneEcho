package fr.zoneecho.mod.objects.blocks;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.BlockInit;
import fr.zoneecho.mod.init.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Objects;

public class BlockVertical extends Block implements IHasModel {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockVertical(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_TAB);
        setDefaultState(getDefaultState());
        setBlockUnbreakable();
        translucent = false;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

        if (state.getValue(FACING) == EnumFacing.NORTH) {
            return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
        } else if (state.getValue(FACING) == EnumFacing.SOUTH) {
            return new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
        } else if (state.getValue(FACING) == EnumFacing.WEST) {
            return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
        } else if (state.getValue(FACING) == EnumFacing.EAST) {
            return new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }


        return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.south());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.north());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.east());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.west());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

}
