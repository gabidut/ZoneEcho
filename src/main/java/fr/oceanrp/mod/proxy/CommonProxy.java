package fr.oceanrp.mod.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonProxy
{

    public void registerItemRenderer(Item item, int meta)
    {

    }

    public void registerVariantRenderer(Item item, int meta, String filename, String id)
    {

    }

    public void registerEntityRenderers()
    {

    }


    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent e) {

    }

    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
