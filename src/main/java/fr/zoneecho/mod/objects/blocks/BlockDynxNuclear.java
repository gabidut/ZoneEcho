package fr.zoneecho.mod.objects.blocks;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import net.minecraft.block.material.Material;

public class BlockDynxNuclear extends DynamXBlock {
    public BlockDynxNuclear(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
    }
}


