package fr.oceanrp.mod.init;

import fr.oceanrp.mod.Main;
import fr.oceanrp.mod.blocks.BlockRoad;
import fr.oceanrp.mod.blocks.slab.road.RoadBlockDoubleSlabBase;
import fr.oceanrp.mod.blocks.slab.road.RoadBlockHalfSlabBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    // Road
    public static final Block ASPHALT_BLOCK = new BlockRoad("asphalt_block", Material.IRON);
    public static final Block ASPHALT_BLOCK_EGOUT = new BlockRoad("asphalt_block_egout", Material.IRON);
    public static final Block ASPHALT_BLOCK_LINE_1 = new BlockRoad("asphalt_block_line_1", Material.IRON);
    public static final Block ASPHALT_BLOCK_LINE_TURN_1 = new BlockRoad("asphalt_block_line_turn_1", Material.IRON);
    public static final Block ASPHALT_BLOCK_LINE_TURN_2 = new BlockRoad("asphalt_block_line_turn_2", Material.IRON);
    public static final Block ASPHALT_BLOCK_MIDDLE = new BlockRoad("asphalt_block_middle", Material.IRON);
    public static final Block ASPHALT_BLOCK_MIDDLE_TURN = new BlockRoad("asphalt_block_middle_turn", Material.IRON);
    public static final BlockSlab ASPHALT_BLOCK_SLAB_DOUBLE = new RoadBlockDoubleSlabBase("asphalt_block_slab_double", Material.IRON, Main.ROUTE_TABS, BlockInit.ASPHALT_BLOCK_SLAB_HALF);
    public static final BlockSlab ASPHALT_BLOCK_SLAB_HALF = new RoadBlockHalfSlabBase("asphalt_block_slab_half", Material.IRON, Main.ROUTE_TABS, BlockInit.ASPHALT_BLOCK_SLAB_HALF, BlockInit.ASPHALT_BLOCK_SLAB_DOUBLE);
    public static final BlockSlab CONCRETE_SLAB_DOUBLE = new RoadBlockDoubleSlabBase("concrete_slab_double", Material.IRON, Main.ROUTE_TABS, BlockInit.CONCRETE_SLAB_HALF);
    public static final BlockSlab CONCRETE_SLAB_HALF = new RoadBlockHalfSlabBase("concrete_slab_half", Material.IRON, Main.ROUTE_TABS, BlockInit.CONCRETE_SLAB_HALF, BlockInit.CONCRETE_SLAB_DOUBLE);


}
