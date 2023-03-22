package fr.zoneecho.mod.client.tabs;

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
    public ItemStack createIcon() {
        return this.icon;
    }
}
