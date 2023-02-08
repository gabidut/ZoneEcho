package fr.zoneecho.mod.common.blocks.types.stairs;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.BlockInit;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.utils.interfaces.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;

import java.util.Objects;

public class BlockStairs extends net.minecraft.block.BlockStairs implements IHasModel {
    public BlockStairs(String name, IBlockState modelState) {
        super(modelState);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }
    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(ItemSlab.getItemFromBlock(this), 0);
    }
}
