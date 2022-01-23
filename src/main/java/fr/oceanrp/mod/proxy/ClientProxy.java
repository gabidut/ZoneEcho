package fr.oceanrp.mod.proxy;

import fr.oceanrp.mod.Main;
import fr.oceanrp.mod.client.ClientEventHandler;
import fr.oceanrp.mod.util.packets.ApiRequest;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy
{


    @Override
    public void registerItemRenderer(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        super.registerVariantRenderer(item, meta, filename, id);
    }

    @Override
    public void registerEntityRenderers()
    {
        super.registerEntityRenderers();	
    }

    @Override
    public void preInit()
    {

        super.preInit();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());

    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRenderPlayer(RenderPlayerEvent.Pre event) {

    }

    public void setupLayers(RenderPlayer renderer) {

    }
}
