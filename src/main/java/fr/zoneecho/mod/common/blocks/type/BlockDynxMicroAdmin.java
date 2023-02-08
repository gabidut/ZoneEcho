package fr.zoneecho.mod.common.blocks.type;

import fr.dynamx.common.blocks.DynamXBlock;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.util.network.PacketEmergencyOpenGui;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDynxMicroAdmin extends DynamXBlock {
    public BlockDynxMicroAdmin(Material material, String modid, String blockName, String model) {
        super(material, modid, blockName, model);
        setCreativeTab(ZoneEcho.ZONECHO_UTILS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {

            ZoneEcho.network.sendTo(new PacketEmergencyOpenGui(), (EntityPlayerMP) playerIn);
        }
        return true;
    }
}
