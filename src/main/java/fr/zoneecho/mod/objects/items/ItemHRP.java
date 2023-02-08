package fr.zoneecho.mod.objects.items;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nullable;

public class ItemHRP extends Item implements IHasModel {
    public ItemHRP(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ZoneEcho.ZONECHO_TAB);
        ItemInit.ITEMS.add(this);
        setMaxDamage(1);
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return ZoneEcho.ZONEECHO_ITEMS;
    }

    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(this, 0);
    }
}
