package fr.zoneecho.mod.common.blocks.type.decoration;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockHalfSlabBase extends BlockSlabBase implements IHasModel {
    public BlockHalfSlabBase(String name, Material material, CreativeTabs tab, BlockSlab half, BlockSlab doubleSlab) {
        super(name, material, half);
        setCreativeTab(ZoneEcho.ZONEECHO_BLOCKCHELOU);
        ItemInit.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(world.getBlockState(pos).getBlock());
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(ItemSlab.getItemFromBlock(this), 0);
    }
}