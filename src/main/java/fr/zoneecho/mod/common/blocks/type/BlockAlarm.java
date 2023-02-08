package fr.zoneecho.mod.common.blocks.type;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.BlockInit;
import fr.zoneecho.mod.common.blocks.ItemInit;
import fr.zoneecho.mod.common.blocks.SoundInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Objects;

public class BlockAlarm extends Block implements IHasModel {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private int usage;
    public BlockAlarm(String name, Material material, int usage) {
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
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        System.out.println(1);
        if(!worldIn.isRemote && usage == 1) {
            System.out.println(2);
            if(worldIn.isBlockPowered(pos)) {
                System.out.println(3);
                for(EntityPlayer player : worldIn.getEntitiesWithinAABB(EntityPlayer.class, new net.minecraft.util.math.AxisAlignedBB(pos.add(-5, -5, -5), pos.add(5, 5, 5)))) {
                    System.out.println(4);
                    player.playSound(SoundInit.ALARM_ANY, 1.0F, 1);
                }
            }
        }
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        if(!worldIn.isRemote) {
            if(playerIn.inventory.getCurrentItem().isItemEqual(new ItemStack(ItemInit.DEBUG_TOOL))) {
                playerIn.sendMessage(new TextComponentString("This is an easter egg 👀"));
                playerIn.sendMessage(new TextComponentString("Non mais cherche pas ça fais rien"));
            }
        }
    }

    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }
}
