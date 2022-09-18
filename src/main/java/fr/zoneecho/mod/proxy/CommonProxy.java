package fr.zoneecho.mod.proxy;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.objects.tiles.TETVSign;
import fr.zoneecho.mod.util.PlayableJobs;
import fr.zoneecho.mod.util.network.PacketSetBleeding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
    public void playerTickEvent(EntityJoinWorldEvent e) {
        if(e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            if(player instanceof EntityPlayerMP) {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;

                System.out.println(playerMP.connection.getNetworkManager().getRemoteAddress());
            }

            if(((EntityPlayer) e.getEntity()).getHealth() <= 8) {
                System.out.println("Bleeding");
                ZoneEcho.network.sendTo(new PacketSetBleeding(), (EntityPlayerMP) e.getEntity());
            }
            PlayableJobs.listPerso.getAllPerso().forEach((listPerso -> {
                System.out.println(Databases.getPlayerData((EntityPlayer) e.getEntity()).isString("whitelist" + listPerso.name()));
                System.out.println(Databases.getPlayerData((EntityPlayer) e.getEntity()).getString("whitelist" + listPerso.name()));
                if(!Databases.getPlayerData((EntityPlayer) e.getEntity()).isString("whitelist" + listPerso.name())) {
                    System.out.println(listPerso.name());
                    e.getEntity().sendMessage(new TextComponentString(listPerso.name()));
                    Databases.getPlayerData((EntityPlayer) e.getEntity()).setString("whitelist" + listPerso.name(), "empty");
                    Databases.save();
                }

            }));
        }
    }

    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerTileEntity(TETVSign.class, new ResourceLocation("zoneecho", "tetvsign"));
    }


}
