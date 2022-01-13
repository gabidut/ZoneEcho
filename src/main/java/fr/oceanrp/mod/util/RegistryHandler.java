package fr.oceanrp.mod.util;

import fr.oceanrp.mod.init.BlockInit;
import fr.oceanrp.mod.init.ItemInit;
import fr.oceanrp.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
// Execute as mod start SO NO FUCKING REGISTER IN MAIN OR COMMONPROXY, FUCK YOU
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        System.out.println("Enregistrement des items");

        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        System.out.println("Enregistrement des blocks");
        System.out.println(BlockInit.BLOCKS.toArray(new Block[0]));
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }
    @SubscribeEvent
    public static void OnModelRegister(ModelRegistryEvent event)
    {
        for (Item item : ItemInit.ITEMS) {
            if(item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }
        for (Block block : BlockInit.BLOCKS) {
            if(block instanceof IHasModel) {
                ((IHasModel)block).registerModels();
            }
        }
    }
}
