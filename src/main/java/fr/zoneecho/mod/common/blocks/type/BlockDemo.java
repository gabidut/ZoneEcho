package fr.zoneecho.mod.common.blocks.type;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.BlockInit;
import fr.zoneecho.mod.common.blocks.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class BlockDemo extends Block implements IHasModel {

    public BlockDemo(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
        translucent = true;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));

    }


    @Override
    public void registerModels() {

    }
}
