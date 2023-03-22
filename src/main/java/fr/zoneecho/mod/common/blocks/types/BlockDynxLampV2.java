package fr.zoneecho.mod.common.blocks.types;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.tiles.TileLamp;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockDynxLampV2 extends DynamXBlock {
    private int data;
    public BlockDynxLampV2(Material material, String modid, String blockName, String model, int data) {
        super(material, modid, blockName, model);
        this.data = data;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        if(data == 1) {
            return 15;
        } else if(data == 2) {
            return 10;
        } else {
            return 0;
        }
    }


    @Override
    public CreativeTabs getCreativeTab() {
        if(data == 1) {
            return ZoneEcho.ZONECHO_UTILS;
        } else if(data == 2) {
            return ZoneEcho.ZONEECHO_POUBELLE;
        } else {
            return ZoneEcho.ZONEECHO_POUBELLE;
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(!worldIn.isRemote) {
            System.out.println("Side here");
            System.out.println(ZoneEcho.alarmMode);
        }
        super.updateTick(worldIn, pos, state, rand);
    }

    @Override
    public boolean requiresUpdates() {
        return true;
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        System.out.println("Calling createTileEntity");
        return new TileLamp(blockObjectInfo);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}


