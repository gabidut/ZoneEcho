package fr.zoneecho.mod.common.blocks.types;

import fr.zoneecho.mod.common.utils.interfaces.IHasModel;
import net.minecraft.block.material.Material;

public class BlockDoorV2 extends net.minecraft.block.BlockDoor implements IHasModel {

    protected BlockDoorV2(Material materialIn) {
        super(materialIn);
    }

    @Override
    public void registerModels() {

    }
}
