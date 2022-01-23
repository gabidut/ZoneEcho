package fr.oceanrp.mod.blocks.slab.road;

import fr.oceanrp.mod.init.ItemInit;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;

public class RoadBlockHalfSlabBase extends RoadBlockSlabBase {
    public RoadBlockHalfSlabBase(String name, Material material, CreativeTabs tab, BlockSlab half, BlockSlab doubleSlab) {
        super(name, material, half);
        setCreativeTab(tab);
        ItemInit.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
    }

    @Override
    public boolean isDouble() {
        return false;
    }
}