package fr.zoneecho.mod.common.items.types;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.utils.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWrench extends Item implements IHasModel {
    public ItemWrench(String name)
    {
        //setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_TAB);

        ItemInit.ITEMS.add(this);

    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return ZoneEcho.ZONEECHO_ITEMS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("§4Cet item est strictement réservé au staff.");
        tooltip.add("§4Son utilisation en tant que joueur est passible d'un ban HRP.");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(this, 0);
    }
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int posx, int posy, int posz, int side, float blockx, float blocky, float blockz) {
        return false;
    }
}
