package fr.oceanrp.mod.tabs;

import fr.oceanrp.mod.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RouteTab extends CreativeTabs
{
    public RouteTab(String label)
    {
        super(label);

    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(Item.getItemFromBlock(BlockInit.ASPHALT_BLOCK));
    }

}
