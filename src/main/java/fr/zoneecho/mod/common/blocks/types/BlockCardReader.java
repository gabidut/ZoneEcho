package fr.zoneecho.mod.common.blocks.types;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.BlockInit;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.network.PacketPlayKeycard;
import fr.zoneecho.mod.common.tiles.TileCardReader;
import fr.zoneecho.mod.common.utils.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.Random;

public class BlockCardReader extends Block implements IHasModel, ITileEntityProvider {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final PropertyBool DISABLED = PropertyBool.create("disabled");
    public static int tryed = 0;
    public BlockCardReader(String name, Material material) {
        super(material);
//        //setUnlocalizedName(name);
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
            return new AxisAlignedBB(0.3D, 0.3D, 1.0D, 0.7D, 0.7D, 0.8D);
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
            tileEntity.getTileData().setInteger("securitylevel", 1);
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
                Objects.requireNonNull(worldIn.getTileEntity(pos)).getTileData().setFloat("rotate", 270F);
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
                Objects.requireNonNull(worldIn.getTileEntity(pos)).getTileData().setFloat("rotate", 180F);
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
                Objects.requireNonNull(worldIn.getTileEntity(pos)).getTileData().setFloat("rotate", 90F);
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
                Objects.requireNonNull(worldIn.getTileEntity(pos)).getTileData().setFloat("rotate", 0F);
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
        EnumFacing enumfacing = EnumFacing.byHorizontalIndex(meta);

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
        return 90;
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
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        assert tileEntity != null;
        boolean lockdownbypass = false;

        if (playerIn.getActiveHand() == EnumHand.MAIN_HAND) {
            if (playerIn.inventory.getCurrentItem().hasTagCompound()) {
                ItemStack stack = playerIn.inventory.getCurrentItem();
                NBTTagCompound nbt;
                if (stack.hasTagCompound()) {
                    nbt = stack.getTagCompound();
                } else {
                    nbt = new NBTTagCompound();
                }

                assert nbt != null;
                if (nbt.hasKey("lockdownbypass")) {
                    lockdownbypass = true;
                }
                if (nbt.hasKey("openeverthing")) {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE), 2);
                    worldIn.markBlockRangeForRenderUpdate(pos, pos);
                    worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
                    notifyNeighbors(worldIn, pos, state.getValue(FACING));
                }
            }
            if (ZoneEcho.dbUtils.getInteger("isLockdown") != 1 || lockdownbypass) {
                if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.KEYCARD1)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 1) {
                        setPowered(worldIn, pos, state);
                    } else {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas le niveau requis !"));
                        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
                    }
                }
                if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.KEYCARD2)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 2) {
                        setPowered(worldIn, pos, state);
                    } else {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas le niveau requis !"));
                        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
                    }
                }
                if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.KEYCARD3)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 3) {
                        setPowered(worldIn, pos, state);
                    } else {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas le niveau requis !"));
                        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
                    }
                }
                if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.KEYCARD4)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 4) {
                        setPowered(worldIn, pos, state);
                    } else {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas le niveau requis !"));
                        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
                    }
                }
                if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.KEYCARD5)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 5) {
                        setPowered(worldIn, pos, state);
                    } else {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas le niveau requis !"));
                        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
                    }
                }
                if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.KEYCARD0)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 0) {
                        setPowered(worldIn, pos, state);
                    } else {
                        playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "Vous n'avez pas le niveau requis !"));
                        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
                    }
                }

                if(playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.WRENCH)) {
                    if (tileEntity.getTileData().getInteger("securitylevel") <= 5) {
                        tileEntity.getTileData().setInteger("securitylevel", tileEntity.getTileData().getInteger("securitylevel") + 1);
                        playerIn.sendMessage(new TextComponentString(TextFormatting.GREEN + "Niveau de sécurité augmenté au niveau " + tileEntity.getTileData().getInteger("securitylevel")));
                        ZoneEcho.network.sendTo(new PacketPlayKeycard(0), (EntityPlayerMP) playerIn);
                    } else {
                        tileEntity.getTileData().setInteger("securitylevel", 0);
                    }
                }
            } else {
                playerIn.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "Lecteur inactif."));
            }
        }
        return true;
    }

    private void setPowered(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE), 2);
        worldIn.markBlockRangeForRenderUpdate(pos, pos);
        worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
        notifyNeighbors(worldIn, pos, state.getValue(FACING));
        ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(1), new NetworkRegistry.TargetPoint(worldIn.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileCardReader();
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (!worldIn.isRemote)
        {
                if (!((Boolean)state.getValue(POWERED)).booleanValue())
                {
                    this.checkPressed(state, worldIn, pos);
                }
        }
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
        return null;
    }
}
