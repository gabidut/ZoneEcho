package fr.oceanrp.mod.tabs;

import fr.oceanrp.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockTab extends CreativeTabs
{
    public BlockTab(String label)
    {
        super(label);

    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemInit.Logo);

    }

}