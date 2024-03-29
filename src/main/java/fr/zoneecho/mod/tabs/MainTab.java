package fr.zoneecho.mod.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MainTab extends CreativeTabs
{
    private String label;
    private ItemStack icon;
    public MainTab(String label, ItemStack icon)
    {
        super(label);
        this.label = label;
        this.icon = icon;
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return this.icon;
    }

}
