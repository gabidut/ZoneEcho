package fr.zoneecho.mod.init;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.objects.blocks.*;
import fr.zoneecho.mod.objects.blocks.slab.decoration.BlockDoubleSlabBase;
import fr.zoneecho.mod.objects.blocks.slab.decoration.BlockHalfSlabBase;
import fr.zoneecho.mod.objects.blocks.stairs.BlockStairs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    // BASE BUILD
    public static final Block WHITE_WALL = new BlockBase("white_wall", Material.IRON);
    public static final Block RED_WALL = new BlockBase("red_wall", Material.IRON);
    public static final Block GREEN_WALL = new BlockBase("green_wall", Material.IRON);
    public static final Block BLUE_WALL = new BlockBase("blue_wall", Material.IRON);
    public static final Block YELLOW_WALL = new BlockBase("yellow_wall", Material.IRON);
    public static final Block GREY_WALL = new BlockBase("grey_wall", Material.IRON);
    public static final Block CYAN_WALL = new BlockBase("cyan_wall", Material.IRON);
    public static final Block METAL_BLOCK = new BlockBase("metal_block", Material.IRON);

    // ALARMS
    public static final Block ALARM = new BlockAlarm("alarm", Material.IRON, 1);

    // VERTICAL SLABS
    public static final Block VERTICAL_SLAB_WHITE = new BlockVertical("vertical_white_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_RED = new BlockVertical("vertical_red_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_GREEN = new BlockVertical("vertical_green_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_BLUE = new BlockVertical("vertical_blue_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_YELLOW = new BlockVertical("vertical_yellow_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_GREY = new BlockVertical("vertical_grey_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_CYAN = new BlockVertical("vertical_cyan_wall", Material.IRON);
    public static final Block VERTICAL_SLAB_METAL = new BlockVertical("vertical_metal_wall", Material.IRON);
    // VERTICAL LINE
    // public static final Block VERTICAL_SLAB_GREY_LINE = new BlockVertical("vertical_cyan_wall", Material.IRON);

    // STAIRS
    public static final Block STAIRS_METAL = new BlockStairs("stairs_metal", Blocks.STONE.getDefaultState());
    public static final Block STAIRS_TOP_LINE_BLUE = new BlockStairs("white_wall_stairs", Blocks.STONE.getDefaultState());
    // TOP LINE
    public static final Block TOP_LINE_BLUE = new BlockBase("top_line_blue", Material.IRON);
    public static final Block TOP_LINE_CYAN = new BlockBase("top_line_cyan", Material.IRON);
    public static final Block TOP_LINE_GREEN = new BlockBase("top_line_green", Material.IRON);
    public static final Block TOP_LINE_GREY = new BlockBase("top_line_grey", Material.IRON);
    public static final Block TOP_LINE_RED = new BlockBase("top_line_red", Material.IRON);
    public static final Block TOP_LINE_YELLOW = new BlockBase("top_line_yellow", Material.IRON);

    // SLICED WALLS
    public static final Block SLICED_WALL_BLUE = new BlockBase("sliced_wall_blue", Material.IRON);
    public static final Block SLICED_WALL_CYAN = new BlockBase("sliced_wall_cyan", Material.IRON);
    public static final Block SLICED_WALL_RED = new BlockBase("sliced_wall_red", Material.IRON);
    public static final Block SLICED_WALL_YELLOW = new BlockBase("sliced_wall_yellow", Material.IRON);
    public static final Block SLICED_WALL_GREEN = new BlockBase("sliced_wall_green", Material.IRON);
    public static final Block SLICED_WALL_GREY = new BlockBase("sliced_wall_grey", Material.IRON);
    public static final Block WHITE_CYLINDER = new BlockPillar("white_cylinder", Material.IRON);


    // GROUND BLOCK
    public static final Block GROUND = new BlockBase("ground", Material.IRON);

    // OTHER
    public static final Block CARD_READER = new BlockCardReader("card_reader", Material.IRON);
    public static final Block JOB_READER = new BlockJobReader("job_reader", Material.IRON);
    public static final Block BUILD_SUPPORT = new BlockUtils("build_support", Material.IRON);
    public static final Block BUILD_SUPPORT_NORTH = new BlockUtils("build_support_north", Material.IRON);
    public static final Block BUILD_SUPPORT_SOUTH = new BlockUtils("build_support_south", Material.IRON);
    public static final Block PLANT = new BlockUtils("plant", Material.IRON);
    public static final Block TOP_LAMP = new BlockLamp("top_lamp", Material.IRON);
    public static final Block TOP_LAMP_OFF = new BlockLampOff("top_lamp_off", Material.IRON);
    public static final Block GRILLE1 = new BlockGrille("grille1", Material.IRON);
    public static final Block GRILLE2 = new BlockGrille("grille2", Material.IRON);
    public static final Block BUILDSUPPORT = new BlockUtils("buildsupport", Material.IRON);
    public static final Block COMPUTER = new BlockUtils("computer", Material.IRON);
    public static final Block CARDWRITER = new BlockCardWriter("cardwriter", Material.IRON);


    // DEV ITEMS
    public static final Block TESTDOORDEV = new BlockTestDoor("testdoordev", Material.IRON);
    public static final Block DOOR = new BlockDoor("door", Material.IRON);



    // SLABS
    public static final BlockSlab METAL_SLAB_DOUBLE = new BlockDoubleSlabBase("metal_slab_double", Material.IRON, ZoneEcho.ZONECHO_TAB, BlockInit.METAL_SLAB_HALF);
    public static final BlockSlab METAL_SLAB_HALF = new BlockHalfSlabBase("metal_slab_half", Material.IRON, ZoneEcho.ZONECHO_TAB, BlockInit.METAL_SLAB_HALF, BlockInit.METAL_SLAB_DOUBLE);

}