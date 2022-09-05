package fr.zoneecho.mod.objects.blocks;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import net.minecraft.block.material.Material;

public class BlockDynxLight extends DynamXBlock {
    public BlockDynxLight(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        setLightLevel(50.0F);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
    }
}


