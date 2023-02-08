package fr.zoneecho.mod.common.blocks.type;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import net.minecraft.block.material.Material;

public class BlockDynx extends DynamXBlock {
    public BlockDynx(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
    }
}


