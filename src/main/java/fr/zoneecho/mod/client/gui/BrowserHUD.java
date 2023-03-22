package fr.zoneecho.mod.client.gui;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.montoyo.mcef.api.API;
import net.montoyo.mcef.api.IBrowser;
import net.montoyo.mcef.api.MCEFApi;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class BrowserHUD
{
    private static final List<BrowserHUD> huds = new ArrayList<BrowserHUD>();;
    private String url;
    private API api;
    private IBrowser browser;
    private Minecraft mc;

    public BrowserHUD(String url) {
        this.url = url;
        this.api = MCEFApi.getAPI();
        this.browser = this.api.createBrowser(url, true);
        this.mc = Minecraft.getMinecraft();
    }

    public void drawScreen() {
        if (this.browser != null) {
            this.browser.resize(this.mc.displayWidth, this.mc.displayHeight);
            GlStateManager.disableDepth();
            GlStateManager.enableTexture2D();
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            this.browser.draw(0.0, (double)scaledResolution.getScaledHeight(), (double)scaledResolution.getScaledWidth(), 0.0);
            GL11.glDisable(3042);
            GlStateManager.enableDepth();
        }
    }

    public static void addHud(final BrowserHUD browserHud) {
        BrowserHUD.huds.add(browserHud);
    }

    public static void removeHud(final BrowserHUD browserHud) {
        BrowserHUD.huds.remove(browserHud);
    }

    @SubscribeEvent
    public static void onRenderGameOverlayEvent(final RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            for (final BrowserHUD browserHud : BrowserHUD.huds) {
                browserHud.drawScreen();
            }
        }
    }
    @SubscribeEvent
    public static void onRenderGameOverlayEvent(final InputEvent.KeyInputEvent event) {
        if(ClientProxy.guiToDisplay == 1) {
            ZoneEcho.browserScreen = new BrowserScreen("mod://zoneecho/perso/new.html");
            System.out.println(ZoneEcho.browserScreen);
            System.out.println(ZoneEcho.browserHud);
//            ZoneEcho.browserHud.runJS("sperso('" + ClientProxy.createPersoLastCharName + "')");
            ZoneEcho.browserScreen.browser.runJS("sperso('" + ClientProxy.createPersoLastCharName + "')", "");
            ZoneEcho.browserScreen.openMenu();

            System.out.println("Last : " + ClientProxy.createPersoLastCharName);

            ClientProxy.guiToDisplay = 0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)) {
            ZoneEcho.browserScreen = new BrowserScreen("mod://zoneecho/index.html");
            ZoneEcho.browserScreen.openMenu();
        }
    }

    public void runJS(final String js) {
        this.browser.runJS(js, "");
    }
}