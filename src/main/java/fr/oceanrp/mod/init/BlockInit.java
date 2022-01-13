package fr.oceanrp.mod.init;

import fr.oceanrp.mod.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    // Road
    public static final Block ASPHALT_BLOCK = new BlockBase("asphalt_block", Material.IRON);
    public static final Block ASPHALT_BLOCK_EGOUT = new BlockBase("asphalt_block_egout", Material.IRON);
    public static final Block ASPHALT_BLOCK_LINE_1 = new BlockBase("asphalt_block_line_1", Material.IRON);
    public static final Block ASPHALT_BLOCK_MIDDLE = new BlockBase("asphalt_block_middle", Material.IRON);
    public static final Block ASPHALT_BLOCK_MIDDLE_TURN = new BlockBase("asphalt_block_middle_turn", Material.IRON);
    public static final Block ASPHALT_BLOCK_MIDDLE_TURN_2 = new BlockBase("asphalt_block_middle_turn_2", Material.IRON);
}
