package fr.oceanrp.mod;


import fr.dynamx.api.contentpack.DynamXAddon;
import fr.dynamx.api.events.PhysicsEntityEvent;
import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.oceanrp.mod.handler.IdentityHandler;
import fr.oceanrp.mod.init.ItemInit;
import fr.oceanrp.mod.proxy.CommonProxy;
import fr.oceanrp.mod.tabs.MainTab;
import fr.oceanrp.mod.tabs.RouteTab;
import fr.oceanrp.mod.util.Ref;
import fr.oceanrp.mod.util.command.CommandDev;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import java.awt.*;


@DynamXAddon(modid = Ref.MODID, name = "OceanRP", version = "1.0.0")
@Mod(modid = Ref.MODID, name = Ref.NAME, version = Ref.VERSION)
@Mod.EventBusSubscriber(modid = Ref.MODID)
public class Main {
    public static final CreativeTabs OCEANRP_TAB = new MainTab("oceanrp");
    public static final CreativeTabs BOISSON_TAB = new MainTab("boisson");
    public static final CreativeTabs ALIMENT_TAB = new MainTab("aliment");
    public static final CreativeTabs IDENTITY_TAB = new MainTab("Systeme didentitee");
    public static final CreativeTabs ROUTE_TABS = new RouteTab("Route");



    @SidedProxy(clientSide = Ref.CLIENT, serverSide = Ref.COMMON)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static Logger logger;
    @SubscribeEvent
    public static void initModule(PhysicsEntityEvent.CreateEntityModulesEvent<BaseVehicleEntity> event)
    {
    }
    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        proxy.preInit();

        MinecraftForge.EVENT_BUS.register(new IdentityHandler());
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e) {};

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        // Executez au démarrage du serveur
        event.registerServerCommand(new CommandDev());
    }

    public static final Minecraft MC = Minecraft.getMinecraft();

    private static final ResourceLocation emptyLife = new ResourceLocation(Ref.MODID, "textures/gui/emptylife.png");
    private static final ResourceLocation fullLife = new ResourceLocation(Ref.MODID, "textures/gui/fulllife.png");

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent e) {
        EntityPlayer entityPlayer = e.player;
        if(e.player.getHeldItemMainhand().getItem() == ItemInit.IdentityCard) {
            System.out.println("Player have identity card in hand");

        }
    }
    @SubscribeEvent
    public static void renderGameOverlayPre(RenderGameOverlayEvent.Pre event)
    {
        if(event.getType().equals(RenderGameOverlayEvent.ElementType.HEALTH))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void renderGameOverlayPost(RenderGameOverlayEvent.Post event)
    {
        if(event.getType().equals(RenderGameOverlayEvent.ElementType.ALL))
        {
            GL11.glColor4f(1, 1, 1, 1);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(770, 771);
            int width = event.getResolution().getScaledWidth();

            if(!MC.player.capabilities.disableDamage)
                drawHealth(event.getResolution(), MC.player);

            String s = "OceanRP - DEV";
            Gui.drawRect(width - 5 - MC.fontRenderer.getStringWidth(s), 2, width - 2, 4 + MC.fontRenderer.FONT_HEIGHT, Integer.MIN_VALUE);
            MC.fontRenderer.drawString(s, width - 3 - MC.fontRenderer.getStringWidth(s), 4, Color.WHITE.getRGB());

            // Gui.drawRect(width - 5 - MC.fontRenderer.getStringWidth(s), 2, width - 2, 2 + MC.fontRenderer.FONT_HEIGHT, Color.white.getRGB());


        }
    }

    private static void drawHealth(ScaledResolution res, EntityPlayer player)
    {
        MC.getTextureManager().bindTexture(emptyLife);
        Gui.drawScaledCustomSizeModalRect(res.getScaledWidth() - 70, res.getScaledHeight() - 76, 0, 0, 64, 64, 64, 64, 64, 64);

        int percent = (int)(player.getHealth() * 64 / player.getMaxHealth());
        if(percent > 0)
        {
            MC.getTextureManager().bindTexture(fullLife);
            Gui.drawScaledCustomSizeModalRect(res.getScaledWidth() - 70, res.getScaledHeight() - 76 + (64 - percent), 0, 64 - percent, 64, percent, 64, percent, 64, 64);
        }
    }

}


