package fr.zoneecho.mod.objects.blocks;

import fr.nathanael2611.simpledatabasemanager.core.SyncedDatabases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.BlockInit;
import fr.zoneecho.mod.init.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import fr.zoneecho.mod.util.network.PacketOpenDelimiter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class BlockDelimiter extends Block implements IHasModel {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private int usage;
    public BlockDelimiter(String name, Material material, int usage) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_TAB);
        setDefaultState(getDefaultState());
        setBlockUnbreakable();
        this.usage = usage;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        System.out.println("Cliqued ?");
        if(!worldIn.isRemote) {
            System.out.println(playerIn.getHeldItemMainhand().getItem().getRegistryName());
            if(playerIn.inventory.getCurrentItem().isItemEqual(new ItemStack(ItemInit.WRENCH))) {
                SyncedDatabases.sendDatabaseToPlayer(ZoneEcho.dbUtils, (EntityPlayerMP) playerIn);
                ZoneEcho.network.sendTo(new PacketOpenDelimiter(), (EntityPlayerMP) playerIn);
            }
        }
        return true;
    }


    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }
}
