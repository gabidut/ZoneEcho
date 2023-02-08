package fr.zoneecho.mod.objects.items;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStaff extends Item implements IHasModel {
    public ItemStaff(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONEECHO_ITEMS);
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
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(this, 0);
    }
}
