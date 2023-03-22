package fr.zoneecho.mod.common.proxy;

import com.modularwarfare.client.gui.GuiInventoryModified;
import com.mrcrayfish.obfuscate.client.event.ModelPlayerEvent;
import fr.zoneecho.mod.Ref;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.client.betterInventory.BetterInventoryGui;
import fr.zoneecho.mod.client.css.MainMenu;
import fr.zoneecho.mod.client.gui.BrowserScreen;
import fr.zoneecho.mod.common.blocks.types.BlockCardReader;
import fr.zoneecho.mod.common.items.ItemInit;
import fr.zoneecho.mod.common.tiles.TETVSign;
import fr.zoneecho.mod.common.tiles.TileCardReader;
import fr.zoneecho.mod.common.tiles.TileDoor;
import fr.zoneecho.mod.common.tiles.TileDoorO;
import fr.zoneecho.mod.common.tiles.render.RenderTileDoor;
import fr.zoneecho.mod.common.tiles.render.RenderTileDoorO;
import fr.zoneecho.mod.common.tiles.render.RenderTileKeycardReader;
import fr.zoneecho.mod.common.tiles.render.TETVSignRender;
import fr.zoneecho.mod.common.utils.ChatMode;
import fr.zoneecho.mod.common.utils.PlayerInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class ClientProxy extends CommonProxy {

    public static Boolean isBleeding = false;
    public static Boolean devMode = false;
    public static ChatMode chatMode = ChatMode.HRP;
    public static int guiToDisplay = 0;
    public static String createPersoLastCharName = "";

    @Override
    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        super.registerVariantRenderer(item, meta, filename, id);
    }

    @Override
    public void registerEntityRenderers() {
        super.registerEntityRenderers();
    }

    @SubscribeEvent
    public void onRenderHead(ModelPlayerEvent.SetupAngles.Pre event) {
        event.getModelPlayer().boxList.forEach((box -> box.rotateAngleY = 90F));
    }

    @Override
    public void preInit() {

        ItemStackHandler handler = new ItemStackHandler(5);
        handler.setStackInSlot(0, new ItemStack(ItemInit.LOGO));
        handler.setStackInSlot(2, new ItemStack(ItemInit.COMPONANTPLUG));
        System.out.println("Ici sale merde");
        System.out.println(handler.serializeNBT());
//        Databases.getPlayerData("zoneecho").setObject("inventory", handler.serializeNBT());

        ClientRegistry.bindTileEntitySpecialRenderer(TETVSign.class, new TETVSignRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileDoor.class, new RenderTileDoor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileDoorO.class, new RenderTileDoorO());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCardReader.class, new RenderTileKeycardReader());
        try {
            Display.setIcon(new ByteBuffer[]{inputToByteBuffer(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Ref.MODID, "textures/logo.png")).getInputStream())});
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        super.preInit();
        ZoneEcho.PlayerInventory = new PlayerInventory(Minecraft.getMinecraft().player);
        OBJLoader.INSTANCE.addDomain(Ref.MODID);
    }

    public static ByteBuffer inputToByteBuffer(InputStream paramInputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(paramInputStream);
        int[] arrayOfInt = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 * arrayOfInt.length);
        for (int j : arrayOfInt)
            byteBuffer.putInt(j << 8 | j >> 24 & 0xFF);
        byteBuffer.flip();
        return byteBuffer;
    }

    @SubscribeEvent
    public void usernameRender(RenderLivingEvent.Specials.Pre e) {
        if (!(Minecraft.getMinecraft().player.isCreative() || Minecraft.getMinecraft().player.isSpectator())) {
            e.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void showTextOnTopOfCardReader(RenderHandEvent e) {

        World world = Minecraft.getMinecraft().world;
        EntityPlayer player = Minecraft.getMinecraft().player;

        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 3; j++) {
                for (int k = -1; k < 3; k++) {
                    BlockPos blockPos = new BlockPos(player.posX + i, player.posY + j, player.posZ + k);
                    if (world.getBlockState(blockPos).getBlock() instanceof BlockCardReader) {
//                        System.out.println(world.getBlockState(blockPos).getBlock().getRegistryName());
//                        NBTTagCompound tileDoor = Objects.requireNonNull(world.getTileEntity(blockPos)).getTileData();
//                        System.out.println(tileDoor.getString("securitylevel"));
//                        GL11.glPushMatrix();
//                        EntityRenderer.drawNameplate(Minecraft.getMinecraft().fontRenderer, tileDoor.getString("securitylevel"), (float) player.posX + i, (float) player.posY + j + 1.5f, (float) player.posZ + k, 0, 0, 0, false,false);
//
//                        GL11.glPopMatrix();
                    }
                }
            }
        }
        // for each blockpos, get the tileentity and render the text

    }

    public void drawSplitString(FontRenderer renderer, String str, int x, int y, int wrapWidth, int textColor) {
        str = trimStringNewline(str);
        renderSplitStringCentered(renderer, str, x, y, wrapWidth, textColor);
    }

    private void renderSplitStringCentered(FontRenderer renderer, String str, int x, int y, int wrapWidth, int textColor) {
        List<String> lines = renderer.listFormattedStringToWidth(str, 55);
        for (int i = 0; i < lines.size() && i < 4; i++) {
            String line = (String) lines.get(i);
            x = (wrapWidth + -renderer.getStringWidth(line)) / 2;
            renderer.drawString(line, x, y, textColor);
            y += renderer.FONT_HEIGHT;
        }
    }

    private String trimStringNewline(String text) {
        while (text != null && text.endsWith("\n"))
            text = text.substring(0, text.length() - 1);
        return text;
    }

    @SubscribeEvent
    public void GuieventHandler(GuiOpenEvent e) {
        if (e.getGui() instanceof GuiMainMenu) {
            e.setGui(new MainMenu().getGuiScreen());
            //ACsGuiApi.asyncLoadThenShowGui("mainmenu", MainMenu::new);
        }
        if (e.getGui() instanceof GuiInventoryModified) {
            e.setGui(new BetterInventoryGui(Minecraft.getMinecraft().player.inventoryContainer));
        }
    }

    public static void openWebPage(String url) {
//        ZoneEcho.browserScreen = new BrowserScreen(url);

//        ZoneEcho.browserScreen.openMenu();

    }

    public static void showScreen(String url) {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.currentScreen instanceof BrowserScreen)
            ((BrowserScreen) mc.currentScreen).loadURL(url);
        else
            mc.displayGuiScreen(new BrowserScreen(url));
    }
}
