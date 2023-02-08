package fr.zoneecho.mod.common.items.types;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.utils.interfaces.IHasModel;
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
