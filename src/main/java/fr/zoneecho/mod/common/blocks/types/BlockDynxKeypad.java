package fr.zoneecho.mod.common.blocks.types;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.tiles.TileEntitySyncClient;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.network.PacketOpenChangeCode;
import fr.zoneecho.mod.common.network.PacketOpenKeypad;
import fr.zoneecho.mod.common.tiles.TileKeyPad;
import fr.zoneecho.mod.common.utils.Things;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDynxKeypad extends DynamXBlock {
    public BlockDynxKeypad(Material material, String modid, String blockName, String model) {

        super(material, modid, blockName, model);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)  {
            TileEntitySyncClient tileentity = (TileEntitySyncClient) worldIn.getTileEntity(pos);

            if (playerIn.inventory.getCurrentItem().getItem().equals(ItemInit.WRENCH)) {
                assert tileentity != null;
                ZoneEcho.network.sendTo(new PacketOpenChangeCode(Things.blockPosToString(pos), tileentity.getTileData().getString("code")), (EntityPlayerMP) playerIn);
            } else {
                ZoneEcho.network.sendTo(new PacketOpenKeypad(pos, worldIn), (EntityPlayerMP) playerIn);
            }
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {

        return new TileKeyPad(this.blockObjectInfo);
    }

}
