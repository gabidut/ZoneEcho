package fr.zoneecho.mod.objects.blocks;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.BlockInit;
import fr.zoneecho.mod.init.ItemInit;
import fr.zoneecho.mod.objects.tiles.TileCardReader;
import fr.zoneecho.mod.objects.tiles.TileJobReader;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import fr.zoneecho.mod.util.network.PacketOpenJobReader;
import fr.zoneecho.mod.util.network.PacketPlayKeycard;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockJobReader extends Block implements IHasModel, ITileEntityProvider {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final PropertyBool DISABLED = PropertyBool.create("disabled");
    public static int tryed = 0;
    public BlockJobReader(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, Boolean.valueOf(false)));
        translucent = true;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));

    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        // return new AxisAlignedBB(0.2D, 0.2D, 0.3D, 0.0D, 0.7D, 0.7D);

        if (state.getValue(FACING) == EnumFacing.NORTH) {
            return new AxisAlignedBB(0.3D, 0.3D, 0.8D, 0.7D, 0.7D, 0.8D);
        } else if (state.getValue(FACING) == EnumFacing.SOUTH) {
            return new AxisAlignedBB(0.31D, 0.3D, 0.0D, 0.71D, 0.7D, 0.1D);
        } else if (state.getValue(FACING) == EnumFacing.WEST) {
            return new AxisAlignedBB(1.0D, 0.3D, 0.3D, 0.8D, 0.7D, 0.7D);
        } else if (state.getValue(FACING) == EnumFacing.EAST) {
            return new AxisAlignedBB(0.0D, 0.3D, 0.25D, 0.1D, 0.7D, 0.7D);
        }
        return null;
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    @ParametersAreNonnullByDefault
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if(worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            assert tileEntity != null;
            tileEntity.getTileData().setString("securitylevel", "0");
            this.setDefaultFacing(worldIn, pos, state);
        }
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
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

        return this.getDefaultState().withProperty(FACING, enumfacing).withProperty(POWERED, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i;

        i = ((EnumFacing)state.getValue(FACING)).getIndex();

        if (((Boolean)state.getValue(POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;

    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public int tickRate(World worldIn)
    {
        return 40;
    }
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return ((Boolean)blockState.getValue(POWERED)).booleanValue() ? 15 : 0;
    }
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (!((Boolean)blockState.getValue(POWERED)).booleanValue())
        {
            return 0;
        }
        else
        {
            return blockState.getValue(FACING) == side ? 15 : 0;
        }
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.world.isRemote) {
            if(playerIn.getHeldItemMainhand().getItem().equals(ItemInit.WRENCH)) {
                ZoneEcho.network.sendTo(new PacketOpenJobReader(pos), (EntityPlayerMP) playerIn.world.getEntityByID(playerIn.getEntityId()));
            } else {
                System.out.println("Block activated");
                playerIn.sendMessage(new TextComponentString(Objects.requireNonNull(worldIn.getTileEntity(pos)).getTileData().toString()));

                String data = worldIn.getTileEntity(pos).getTileData().getString("restricted");

                String players = data.split(";")[0];
                String jobs = data.split(";")[1];

                List<String> playerList = Arrays.asList(players.split(","));
                List<String> jobList = Arrays.asList(jobs.split(","));

                if(playerList.contains(playerIn.getName())) {
                    setPowered(worldIn, pos, state);
                    playerIn.sendMessage(new TextComponentString("You are allowed to use this block"));
                } else {
                    playerIn.sendMessage(new TextComponentString("You are not allowed to use this block"));
                    ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 5));
                }
            }
        }
        return true;
    }

    private void setPowered(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE), 2);
        worldIn.markBlockRangeForRenderUpdate(pos, pos);
        worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
        notifyNeighbors(worldIn, pos, state.getValue(FACING));
        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(1), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 5));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileCardReader();
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (((Boolean)state.getValue(POWERED)).booleanValue())
            {
                    this.checkPressed(state, worldIn, pos);

            }
        }
    }
    private void checkPressed(IBlockState state, World worldIn, BlockPos pos)
    {
        boolean flag1 = ((Boolean)state.getValue(POWERED)).booleanValue();


        if (flag1)
        {
            worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(true)));
            this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
        }

        if (flag1)
        {
            worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(false)));
            this.notifyNeighbors(worldIn, pos, (EnumFacing)state.getValue(FACING));
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
        }
    }
    private void notifyNeighbors(World worldIn, BlockPos pos, EnumFacing facing)
    {

        worldIn.notifyNeighborsOfStateChange(pos, this, true);
        worldIn.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this, true);
        // notify with +1 block in all directions

        int radius = 5;

        for (int x = -(radius); x <= radius; x ++)
        {
            for (int y = -(radius); y <= radius; y ++)
            {
                for (int z = -(radius); z <= radius; z ++)
                {
                    BlockPos pos2 = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    worldIn.notifyNeighborsOfStateChange(pos2, this, true);
                    worldIn.notifyNeighborsOfStateChange(pos2.offset(facing.getOpposite()), this, true);

                    worldIn.markBlockRangeForRenderUpdate(pos2, pos2);
                    worldIn.scheduleUpdate(pos2, this, this.tickRate(worldIn));
                }
            }
        }

    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, POWERED});
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileJobReader();
    }
}
