package fr.oceanrp.mod.objects.items;

import fr.oceanrp.mod.Main;
import fr.oceanrp.mod.init.ItemInit;
import fr.oceanrp.mod.util.interfaces.IHasModel;
import net.minecraft.item.Item;

public class ItemNourriture extends Item implements IHasModel
{
    public ItemNourriture(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.ALIMENT_TAB);
        setMaxStackSize(5);
        ItemInit.ITEMS.add(this);

    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0);
    }

}
