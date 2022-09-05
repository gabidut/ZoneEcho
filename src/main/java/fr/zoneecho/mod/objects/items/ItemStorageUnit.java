package fr.zoneecho.mod.objects.items;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.ItemInit;
import fr.zoneecho.mod.util.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStorageUnit extends Item implements IHasModel {
    public ItemStorageUnit(String name)
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        NBTTagCompound nbt;
        if (stack.hasTagCompound())
        {
            nbt = stack.getTagCompound();
        }
        else
        {
            nbt = new NBTTagCompound();
        }

        assert nbt != null;
        nbt.setString("data", "empty");
        stack.setTagCompound(nbt);

        tooltip.add("§cNotice inscripte au dos :");
        tooltip.add("§cCette clé USB peut contenir un rapport ou le chemin d'accès vers un rapport.");
        tooltip.add("§cAttention, tout rapport non mis en réseau est sanctionnable.");
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public void registerModels() {
        ZoneEcho.proxy.registerItemRenderer(this, 0);
    }

}
