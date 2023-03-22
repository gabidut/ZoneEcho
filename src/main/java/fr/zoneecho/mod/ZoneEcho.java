package fr.zoneecho.mod;


import com.sun.net.httpserver.HttpServer;
import fr.aym.acsguis.api.ACsGuiApi;
import fr.dynamx.api.contentpack.DynamXAddon;
import fr.nathanael2611.modularvoicechat.api.VoiceDispatchEvent;
import fr.nathanael2611.simpledatabasemanager.core.Database;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.nathanael2611.simpledatabasemanager.core.SyncedDatabases;
import fr.zoneecho.mod.client.css.ErrorToast;
import fr.zoneecho.mod.client.css.GuiEchapMenu;
import fr.zoneecho.mod.client.gui.BrowserHUD;
import fr.zoneecho.mod.client.gui.BrowserScreen;
import fr.zoneecho.mod.client.tabs.MainTab;
import fr.zoneecho.mod.common.blocks.types.*;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.network.*;
import fr.zoneecho.mod.common.proxy.ClientProxy;
import fr.zoneecho.mod.common.proxy.CommonProxy;
import fr.zoneecho.mod.common.tiles.*;
import fr.zoneecho.mod.common.utils.Delimiter;
import fr.zoneecho.mod.server.command.*;
import fr.zoneecho.mod.server.utils.LampMode;
import fr.zoneecho.mod.server.webserver.MainHandler;
import fr.zoneecho.mod.server.webserver.RapportsHandler;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
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
import org.lwjgl.LWJGLException;
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

    public static LampMode alarmMode = LampMode.YELLOW;
    @SideOnly(Side.CLIENT)
    public static KeyBinding openJobs;

    @SideOnly(Side.CLIENT)
    public static IInventory PlayerInventory;

    @SideOnly(Side.CLIENT)
    public static BrowserScreen browserScreen;
    @SideOnly(Side.CLIENT)
    public static BrowserHUD browserHud;

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
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/echap.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/inventory.css"));
            ACsGuiApi.registerStyleSheetToPreload(new ResourceLocation("zoneecho","css/yesno.css"));

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
        network.registerMessage(PacketOpenComputer.Handler.class, PacketOpenComputer.class, 23, Side.CLIENT);
        network.registerMessage(PacketSendToast.Handler.class, PacketSendToast.class, 24, Side.CLIENT);
        network.registerMessage(PacketGoBackToSleep.Handler.class, PacketGoBackToSleep.class, 25, Side.CLIENT);
        network.registerMessage(PacketConfirmPerso.Handler.class, PacketConfirmPerso.class, 26, Side.SERVER);
        

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
        BlockDynx smokesensor = new BlockDynx(Material.ANVIL, Ref.MODID, "smokesensor", "smokesensor/smokesensor.obj");

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
        MinecraftForge.EVENT_BUS.register(BrowserHUD.class);
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
        event.registerServerCommand(new CommandWarn());
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onEvent(InputEvent.KeyInputEvent event) throws LWJGLException {
        if(openJobs.isPressed())
        {
//            ACsGuiApi.asyncLoadThenShowGui("jobs", GuiPersoCreate::new);
            // ACsGuiApi.asyncLoadThenShowGui("computer", GuiHomeOS::new);
            // Display.setDisplayMode(new DisplayMode(1920, 1080));
            //ACsGuiApi.asyncLoadThenShowGui("YesNoGui", () -> new YesNoGui(System.out::println, "Êtes-vous sûr(e) de vouloir supprimer votre personnage principal ? (Aucun retour en arrière n’est possible)"));
            //ACsGuiApi.asyncLoadThenShowGui("inventory", GuiBetterInventory::new);

        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F8))
        {
            Minecraft.getMinecraft().getToastGui().add(new ErrorToast(ErrorToast.Type.NARRATOR_TOGGLE, new TextComponentString("Debug"), new TextComponentString("Mode développeur activé.")));
            ClientProxy.devMode = true;
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void preRenderGameOverlat(RenderGameOverlayEvent.Pre event) {
        if(!ClientProxy.devMode) {
            if(event.getType().equals(RenderGameOverlayEvent.ElementType.HEALTH)) event.setCanceled(true);
            if(event.getType().equals(RenderGameOverlayEvent.ElementType.FOOD)) event.setCanceled(true);
            if(event.getType().equals(RenderGameOverlayEvent.ElementType.ARMOR)) event.setCanceled(true);
            if(event.getType().equals(RenderGameOverlayEvent.ElementType.EXPERIENCE)) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onEvent(RenderGameOverlayEvent.Post event)
    {
        if(ClientProxy.isBleeding) {
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/damage.png"));
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.08F);
            Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight(), event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void healthRender(RenderGameOverlayEvent.Post event) {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.ALL) && !ClientProxy.devMode) {

            GL11.glColor4f(1, 1, 1, 1);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(770, 771);
            int width = event.getResolution().getScaledWidth();

            int x = width - 100;

            ScaledResolution scaledresolution = event.getResolution();

            //HEALTH

            if (!Minecraft.getMinecraft().player.capabilities.disableDamage) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/health.png"));
                // Gui.drawModalRectWithCustomSizedTexture(x, scaledresolution.getScaledHeight() - 76, 0, 0, 15, 70, 15, 70);
                Gui.drawScaledCustomSizeModalRect(scaledresolution.getScaledWidth() - 50, scaledresolution.getScaledHeight() - 76, 0, 0, 15, 70, 15, 70, 15, 70);

                int percent = (int) (Minecraft.getMinecraft().player.getHealth() * 70 / Minecraft.getMinecraft().player.getMaxHealth());
                if (percent > 0) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/fullhealth.png"));
                    // Gui.drawScaledCustomSizeModalRect(x, scaledresolution.getScaledHeight() - 76, 0, 0, 15, percent, 15, (70 - percent), 15, 70);
                    Gui.drawScaledCustomSizeModalRect(scaledresolution.getScaledWidth() - 50, scaledresolution.getScaledHeight() - 76 + (70 - percent), 0, 70 - percent, 15, percent, 15, percent, 15, 70);
                }

                // FOOD
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/food.png"));
                // Gui.drawModalRectWithCustomSizedTexture(x, scaledresolution.getScaledHeight() - 76, 0, 0, 15, 70, 15, 70);
                Gui.drawScaledCustomSizeModalRect(scaledresolution.getScaledWidth() - 30, scaledresolution.getScaledHeight() - 76, 0, 0, 15, 70, 15, 70, 15, 70);

                int percentFood = (int) (Minecraft.getMinecraft().player.getFoodStats().getFoodLevel() * 70 / 20);
                if (percentFood > 0) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/fullfood.png"));
                    // Gui.drawScaledCustomSizeModalRect(x, scaledresolution.getScaledHeight() - 76, 0, 0, 15, percent, 15, (70 - percent), 15, 70);
                    Gui.drawScaledCustomSizeModalRect(scaledresolution.getScaledWidth() - 30, scaledresolution.getScaledHeight() - 76 + (70 - percentFood), 0, 70 - percentFood, 15, percentFood, 15, percentFood, 15, 70);
                }

                // ARMOR
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/shield.png"));
                // Gui.drawModalRectWithCustomSizedTexture(x, scaledresolution.getScaledHeight() - 76, 0, 0, 15, 70, 15, 70);
                Gui.drawScaledCustomSizeModalRect(scaledresolution.getScaledWidth() - 70, scaledresolution.getScaledHeight() - 76, 0, 0, 15, 70, 15, 70, 15, 70);

                int percentArmor = (int) (Minecraft.getMinecraft().player.getTotalArmorValue() * 70 / 20);
                if (percentArmor > 0) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, "textures/hud/fullshield.png"));
                    // Gui.drawScaledCustomSizeModalRect(x, scaledresolution.getScaledHeight() - 76, 0, 0, 15, percent, 15, (70 - percent), 15, 70);
                    Gui.drawScaledCustomSizeModalRect(scaledresolution.getScaledWidth() - 70, scaledresolution.getScaledHeight() - 76 + (70 - percentArmor), 0, 70 - percentArmor, 15, percentArmor, 15, percentArmor, 15, 70);
                }
            }
        }
    }



    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPauseOpened(GuiOpenEvent e) {
        if (e.getGui() instanceof GuiIngameMenu) {
            ACsGuiApi.asyncLoadThenShowGui("pause", GuiEchapMenu::new);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void betterChat(ClientChatEvent e) {
        if(!e.getOriginalMessage().startsWith("/")) {
            e.setMessage(ClientProxy.chatMode.getValue() + " " + e.getMessage());
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

