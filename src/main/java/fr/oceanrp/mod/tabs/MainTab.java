package fr.oceanrp.mod.tabs;

import fr.oceanrp.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MainTab extends CreativeTabs
{
    public MainTab(String label)
    {
        super(label);

    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemInit.Logo);

    }

}
