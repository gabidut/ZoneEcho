package fr.zoneecho.mod;


import com.sun.net.httpserver.HttpServer;
import fr.aym.acsguis.api.ACsGuiApi;
import fr.dynamx.api.contentpack.DynamXAddon;
import fr.nathanael2611.modularvoicechat.api.VoiceDispatchEvent;
import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.nathanael2611.simpledatabasemanager.core.SyncedDatabases;
import fr.zoneecho.mod.init.ItemInit;
import fr.zoneecho.mod.objects.Delimiter;
import fr.zoneecho.mod.objects.blocks.*;
import fr.zoneecho.mod.objects.tiles.*;
import fr.zoneecho.mod.proxy.ClientProxy;
import fr.zoneecho.mod.proxy.CommonProxy;
import fr.zoneecho.mod.tabs.MainTab;
import fr.zoneecho.mod.util.Ref;
import fr.zoneecho.mod.util.command.*;
import fr.zoneecho.mod.util.css.GuiPersoCreate;
import fr.zoneecho.mod.util.network.*;
import fr.zoneecho.mod.webserver.MainHandler;
import fr.zoneecho.mod.webserver.RapportsHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

// TODO: UWU mode

@DynamXAddon(modid = Ref.MODID, name = "zoneecho", version = Ref.VERSION)
@Mod(modid = Ref.MODID, name = Ref.NAME, version = Ref.VERSION, dependencies = "before: dynamxmod;after: sdm;required-after:modularvc")
@Mod.EventBusSubscriber(modid = Ref.MODID)
public class ZoneEcho {
    public static final CreativeTabs ZONECHO_TAB = new MainTab("zoneecho", new ItemStack(ItemInit.LOGO));
    public static final CreativeTabs ZONECHO_UTILS = new MainTab("utils", new ItemStack(ItemInit.KEYCARD3));
    public static final CreativeTabs ZONEECHO_ITEMS = new MainTab("roleplay", new ItemStack(ItemInit.LOGO));
    public static final CreativeTabs ZONEECHO_POUBELLE = new MainTab("DONT USE", new ItemStack(Blocks.BARRIER));
    public static final CreativeTabs ZONEECHO_BLOCKCHELOU = new MainTab("Démerde toi", new ItemStack(Blocks.STONE_SLAB2));
    @SidedProxy(clientSide = Ref.CLIENT, serverSide = Ref.COMMON)
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;
    public static Logger logger;
    public static ZoneEcho instance;
    public static Database dbPlayer;
    public static Database dbIntranet;
    @SideOnly(Side.SERVER)
    public static Socket client;
    public static Database dbUtils;
    @SideOnly(Side.SERVER)
    public static ServerSocket server;
    @SideOnly(Side.SERVER)
    public static boolean alarm;

    @SideOnly(Side.CLIENT)
    public static KeyBinding openJobs;

    public static void launchSocket() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/trigger", new MainHandler());
        server.createContext("/rapports", new RapportsHandler());
        server.start();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) throws InterruptedException, IOException, NoSuchAlgorithmException {


        logger = e.getModLog();
        instance = this;
        proxy.preInit();
        /* network */
        network = NetworkRegistry.INSTANCE.newSimpleChannel("zoneecho");

        /* DEV ZONE */
        List<AxisAlignedBB> ls = new ArrayList<>();
        ls.add(new AxisAlignedBB(0, 0, 0, 1, 1, 1));
        ls.add(new AxisAlignedBB(0, 0, 0, 2, 2, 2));
        ls.add(new AxisAlignedBB(0, 0, 0, 3, 3, 3));

        Delimiter exemple = new Delimiter(ls);

        System.out.println(exemple.getDelimiter());
        System.out.println(exemple.toString());

        System.out.println(Delimiter.fromString(exemple.toString()).getDelimiter());

        if(e.getSide().isClient()) {


            openJobs = new KeyBinding("JobsGUI", Keyboard.KEY_NUMPAD5, "key.categories.gameplay");

            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/cardwriter.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/broadcast.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/speciality.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/code.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/jobs.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/aboutme.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/jobs.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/perso.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/hud/lowhealth.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/mainmenu.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/computer/homeos.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/computer/intranet/intranet.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/changecode.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/computer/rapport.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/jobreader.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/delimiter.css"));

            if(Ref.optiFPS) {
                logger.info("FPS optimiser enabled.");
            } else {
                logger.info("FPS optimiser disabled.");
            }
        } else {
            launchSocket();
            alarm = false;
        }
        network.registerMessage(PacketCardWriter.Handler.class, PacketCardWriter.class, 0, Side.CLIENT);
        network.registerMessage(PacketCardWriterEnd.Handler.class, PacketCardWriterEnd.class, 1, Side.SERVER);
        network.registerMessage(PacketEmergency.Handler.class, PacketEmergency.class, 2, Side.SERVER);
        network.registerMessage(PacketEmergencyOpenGui.Handler.class, PacketEmergencyOpenGui.class, 3, Side.CLIENT);
        network.registerMessage(PacketInitSpe.Handler.class, PacketInitSpe.class, 4, Side.SERVER);
        network.registerMessage(PacketOpenSpe.Handler.class, PacketOpenSpe.class, 5, Side.CLIENT);
        network.registerMessage(PacketSetSpeaking.Handler.class, PacketSetSpeaking.class, 6, Side.SERVER);
        network.registerMessage(PacketOpenKeypad.Handler.class, PacketOpenKeypad.class, 7, Side.CLIENT);
        network.registerMessage(PacketPlayKeycard.Handler.class, PacketPlayKeycard.class, 8, Side.CLIENT);
        network.registerMessage(PacketChangeJob.Handler.class, PacketChangeJob.class, 9, Side.SERVER);
        network.registerMessage(PacketGuiAboutMe.Handler.class, PacketGuiAboutMe.class, 10, Side.CLIENT);
        network.registerMessage(PacketPlayAlarm.Handler.class, PacketPlayAlarm.class, 11, Side.CLIENT);
        network.registerMessage(PacketOpenComputer.Handler.class, PacketOpenComputer.class, 12, Side.SERVER);
        network.registerMessage(PacketOpenChangeCode.Handler.class, PacketOpenChangeCode.class, 13, Side.CLIENT);
        network.registerMessage(PacketChangeCode.Handler.class, PacketChangeCode.class, 14, Side.SERVER);
        network.registerMessage(PacketSetBleeding.Handler.class, PacketSetBleeding.class, 15, Side.CLIENT);
        network.registerMessage(PacketOpenRapport.Handler.class, PacketOpenRapport.class, 16, Side.CLIENT);
        network.registerMessage(PacketOpenJobReader.Handler.class, PacketOpenJobReader.class, 17, Side.CLIENT);
        network.registerMessage(PacketUpdateJobReader.Handler.class, PacketUpdateJobReader.class, 18, Side.SERVER);
        network.registerMessage(PacketSetupFirstConnect.Handler.class, PacketSetupFirstConnect.class, 19, Side.CLIENT);
        network.registerMessage(PacketOpenCreatePerso.Handler.class, PacketOpenCreatePerso.class, 20, Side.CLIENT);
        network.registerMessage(PacketOpenDelimiter.Handler.class, PacketOpenDelimiter.class, 21, Side.CLIENT);
        network.registerMessage(PacketSyncDelimiterToPlayer.Handler.class, PacketSyncDelimiterToPlayer.class, 22, Side.CLIENT);

        SyncedDatabases.add("zoneecho_playerdata");
        dbPlayer = Databases.getDatabase("zoneecho_playerdata");
        SyncedDatabases.add("zoneecho_utils");
        dbUtils = Databases.getDatabase("zoneecho_utils");
        SyncedDatabases.add("zoneecho_intranet");
        dbIntranet = Databases.getDatabase("zoneecho_intranet");

        MinecraftForge.EVENT_BUS.register(this);

    }
    public static BlockDynxDoor door;
    public static BlockDynxDoorO dooropen;
    public static BlockDynxTVSign tvsign;

    public static BlockDynxLampV2 lampV2Yellow;
    public static BlockDynxLampV2 lampV2Red;
    public static BlockDynxLampV2 lampV2fGray;

    @DynamXAddon.AddonEventSubscriber
    public static void init() {
        System.out.println("Init DynamXAddon.");
        BlockDynx micro = new BlockDynx(Material.ANVIL, Ref.MODID, "micro", "micro/micro.obj");
        BlockDynx table = new BlockDynx(Material.ANVIL, Ref.MODID, "table", "table/table.obj");
        BlockDynx chair = new BlockDynx(Material.ANVIL, Ref.MODID, "chair", "chair/chair.obj");
        BlockDynx deco1 = new BlockDynx(Material.ANVIL, Ref.MODID, "deco1", "deco1/deco1.obj");
        BlockDynx deco2 = new BlockDynx(Material.ANVIL, Ref.MODID, "deco2", "deco2/deco2.obj");
        BlockDynx cloak = new BlockDynx(Material.ANVIL, Ref.MODID, "cloak", "cloak/cloak.obj");
        BlockDynx tapis = new BlockDynx(Material.SAND, Ref.MODID, "tapis", "tapis/tapis.obj");
        BlockDynx alarme = new BlockDynx(Material.SAND, Ref.MODID, "alarme", "alarm/alarm.obj");
        BlockDynx alarmew = new BlockDynx(Material.SAND, Ref.MODID, "alarmewall", "alarmwall/alarm.obj");
        BlockDynx tv = new BlockDynx(Material.ANVIL, Ref.MODID, "tv", "tv/tv.obj");
        BlockDynxLight wallight = new BlockDynxLight(Material.ANVIL, Ref.MODID, "wallight", "wallight/wallight.obj");
        BlockDynxLight console = new BlockDynxLight(Material.ANVIL, Ref.MODID, "console", "console/console.obj");
        BlockDynxMicroAdmin microAdmin = new BlockDynxMicroAdmin(Material.ANVIL, Ref.MODID, "micro_admin", "micro/micro.obj");
        BlockDynx centrifugeuse = new BlockDynx(Material.ANVIL, Ref.MODID, "centrifugeuse", "centrifugeuse/centrigugeuse.obj");
        BlockDynx papers = new BlockDynx(Material.ANVIL, Ref.MODID, "Papiers", "papers/papers.obj");
        BlockDynx screen = new BlockDynx(Material.ANVIL, Ref.MODID, "Écran", "screen/screen.obj");
        BlockDynx screen2 = new BlockDynx(Material.ANVIL, Ref.MODID, "Écran2", "screen2/screen2.obj");
        BlockDynx screen3 = new BlockDynx(Material.ANVIL, Ref.MODID, "Écran3", "screen3/screen3.obj");
        BlockDynxKeypad keypad = new BlockDynxKeypad(Material.ANVIL, Ref.MODID, "keypad", "keypad/keypad.obj");
        BlockDynxNuclear nuclear_panel = new BlockDynxNuclear(Material.ANVIL, Ref.MODID, "nuclear_panel", "nuclear_panel/nuclear_panel.obj");
        BlockDynxTVSign tvsign = new BlockDynxTVSign(Material.ANVIL, Ref.MODID, "tvsign", "tvsign/tele.obj");
        BlockDynx displaytwoscreen = new BlockDynx(Material.ANVIL, Ref.MODID, "display", "display/display.obj");

        lampV2Yellow = new BlockDynxLampV2(Material.SAND, Ref.MODID, "lampY", "lamp/yellow.obj", 1);
        lampV2fGray = new BlockDynxLampV2(Material.SAND, Ref.MODID, "lampG", "lamp/gray.obj", 3);
        lampV2Red = new BlockDynxLampV2(Material.SAND, Ref.MODID, "lampR", "lamp/red.obj", 2);

        BlockDynx fer = new BlockDynx(Material.ANVIL, Ref.MODID, "fer", "fer/fer.obj");
        BlockDynx tv2 = new BlockDynx(Material.ANVIL, Ref.MODID, "tv2", "tv2/tv.obj");
        // DOOR
        door = new BlockDynxDoor(Material.ANVIL, Ref.MODID, "Porte", "door/door.obj");
        dooropen = new BlockDynxDoorO(Material.ANVIL, Ref.MODID, "PorteO", "dooropen/dooropen.obj");

    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent e) {

        GameRegistry.registerTileEntity(TileCardReader.class, new ResourceLocation(Ref.MODID, "card_reader"));
        GameRegistry.registerTileEntity(TileKeyPad.class, new ResourceLocation(Ref.MODID, "keypad"));
        GameRegistry.registerTileEntity(TileDoorO.class, new ResourceLocation(Ref.MODID, "dooro"));
        GameRegistry.registerTileEntity(TileLamp.class, new ResourceLocation(Ref.MODID, "lamp"));
        GameRegistry.registerTileEntity(TileDoor.class, new ResourceLocation(Ref.MODID, "door"));
    }

    @SubscribeEvent
    public void onStartedSpeak(VoiceDispatchEvent event) {
        Database db = Databases.getPlayerData(event.getSpeaker());
        if(db.getInteger("speak") == 1) {
            event.getSpeaker().sendMessage(new TextComponentString("Attention, vous parlez en global."));
            event.setCanceled(true);
            event.dispatchToAllExceptSpeaker(100);
        }
        if (event.getSpeaker().getActiveItemStack() == null) {
            return;
        }
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent e) {
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandWhitelist());
        event.registerServerCommand(new CommandLockdown());
        event.registerServerCommand(new CommandPInfo());
        event.registerServerCommand(new CommandDev());
        event.registerServerCommand(new CommandManuaddTag());
        event.registerServerCommand(new CommandLight());
        event.registerServerCommand(new CommandManualSocket());
        event.registerServerCommand(new CommandInfoAboutMe());
        event.registerServerCommand(new CommandAlarm());
        event.registerServerCommand(new CommandAlarmOther());
        event.registerServerCommand(new CommandMakeMeBug());
        event.registerServerCommand(new CommandChooseJob());
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onEvent(InputEvent.KeyInputEvent event)
    {
        if(openJobs.isPressed())
        {
            ACsGuiApi.asyncLoadThenShowGui("jobs", GuiPersoCreate::new);
            // ACsGuiApi.asyncLoadThenShowGui("computer", GuiHomeOS::new);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onEvent(RenderGameOverlayEvent.Post event)
    {
        if(ClientProxy.isBleeding) {
            GL11.glPushMatrix();
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/damage.png"));
            Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight(), event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public static void PlayerDamageEvent2(LivingDamageEvent e) {
        if(!e.getEntity().world.isRemote) {
            if(e.getEntity() instanceof EntityPlayer) {
                if(((EntityPlayer) e.getEntity()).getHealth() <= 8) {
                    ZoneEcho.network.sendTo(new PacketSetBleeding(), (EntityPlayerMP) e.getEntity());
                }
            }
        }
    }
}

