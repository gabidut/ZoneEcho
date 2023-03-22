package fr.zoneecho.mod.client.gui;

import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketConfirmPerso;
import fr.zoneecho.mod.common.proxy.ClientProxy;
import fr.zoneecho.mod.common.utils.PlayableJobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.montoyo.mcef.api.*;
import net.montoyo.mcef.example.ScreenCfg;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.Objects;

public class BrowserScreen extends GuiScreen implements IJSQueryHandler
{
    IBrowser browser;
    private API api;
    private String urlToLoad;

    public BrowserScreen() {
        this("mod://zoneecho/index.html");
    }

    public BrowserScreen(String url) {
        this.urlToLoad = (url);
        this.api = MCEFApi.getAPI();
        this.browser = this.api.createBrowser(this.urlToLoad, true);
        ZoneEcho.logger.debug(urlToLoad);
        this.urlToLoad = null;
        this.api.registerJSQueryHandler(this);

    }

    public void initGui() {
        this.browser.resize(this.mc.displayWidth, this.mc.displayHeight);
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
    }

    public int scaleY(final int y) {
        final double sy = y / (double)this.height * this.mc.displayHeight;
        return (int)sy;
    }

    public void loadURL(final String url) {
        if (this.browser == null) {
            this.urlToLoad = url;
        }
        else {
            this.browser.loadURL(url);
        }
    }

    public void updateScreen() {
        if (this.urlToLoad != null && this.browser != null) {
            this.browser.loadURL(this.urlToLoad);
            this.urlToLoad = null;
        }
    }

    public void drawScreen(final int i1, final int i2, final float f) {
        super.drawScreen(i1, i2, f);
        if (this.browser != null) {
            GlStateManager.disableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.browser.resize(this.mc.displayWidth, this.mc.displayHeight);
            this.browser.draw(0.0, (double)this.height, (double)this.width, 0.0);
            GlStateManager.enableDepth();
        }
    }

    public void onGuiClosed() {
        ZoneEcho.browserScreen = null;
        Keyboard.enableRepeatEvents(false);
        browser.close();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1)
        {
            System.out.println("execute");
            executeJS("window.angular.setText(\"etsettregt\")");
        }
    }
    @Override
    public void handleInput() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == 1) {
                this.closeActiveGui();
                return;
            }
            final boolean pressed = Keyboard.getEventKeyState();
            final char key = Keyboard.getEventCharacter();
            final int modifiers = (Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157)) ? 4 : 0;
            final int num = Keyboard.getEventKey();
            if (this.browser == null) {
                continue;
            }
            if(num == 14 && pressed) {
                this.browser.runJS("document.execCommand('delete');", null);
                return;
            }
            if (pressed) {
                this.browser.injectKeyPressedByKeyCode(num, key, modifiers);
            }
            else {
                this.browser.injectKeyReleasedByKeyCode(num, key, modifiers);
            }
            if (key == '.') {
                final String dot = ".";
                this.browser.injectKeyTyped(dot.charAt(0), 0);
                return;
            }
            if (modifiers == 4 && num == 47 && !pressed) {
                try {
                    final String data = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                    for (final char c : data.toCharArray()) {
                        this.browser.injectKeyTyped(c, 0);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                if (key == '\0') {
                    continue;
                }
                this.browser.injectKeyTyped(key, modifiers);
            }
        }
        while (Mouse.next()) {
            final int btn = Mouse.getEventButton();
            final boolean pressed2 = Mouse.getEventButtonState();
            final int sx = Mouse.getEventX();
            final int sy = Mouse.getEventY();
            final int wheel = Mouse.getEventDWheel();
            if (this.browser != null) {
                final int y = this.mc.displayHeight - sy;
                if (wheel != 0) {
                    this.browser.injectMouseWheel(sx, y, 0, 1, wheel);
                }
                else if (btn == -1) {
                    this.browser.injectMouseMove(sx, y, 0, y < 0);
                }
                else {
                    this.browser.injectMouseButton(sx, y, 0, btn + 1, pressed2, 1);
                }
            }
            if (pressed2) {
                final int x = sx * this.width / this.mc.displayWidth;
                final int y2 = this.height - sy * this.height / this.mc.displayHeight - 1;
                try {
                    this.mouseClicked(x, y2, btn);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void actionPerformed(final GuiButton src) {
        if (this.browser == null) {
            return;
        }
        if (src.id == 0) {
            this.browser.goBack();
        }
        else if (src.id == 1) {
            this.browser.goForward();
        }
        else if (src.id == 3) {
            this.mc.displayGuiScreen(null);
        }
        else if (src.id == 4) {
            final String loc = this.browser.getURL();
            final String vId = null;
            final boolean redo = false;
            if (vId != null || redo) {
                this.mc.displayGuiScreen(new ScreenCfg(this.browser, vId));
            }
        }
    }

    @Override
    public boolean handleQuery(IBrowser b, long queryId, String query, boolean persistent, IJSQueryCallback cb) {

        if(b != null && query.startsWith("$")) {
            if(b.getURL().startsWith("mod://")) {
                if(query.substring(1).split(":")[0].equals("usernameForm")) {
                    String name = query.substring(1).split(":")[1];
                    String surname = query.substring(1).split(":")[2];
                    String date = query.substring(1).split(":")[3];
                    String perso = query.substring(1).split(":")[4];
                    String sex = query.substring(1).split(":")[5];

                    ZoneEcho.network.sendToServer(new PacketConfirmPerso(name, surname, date, perso, sex));
                    ClientProxy.guiToDisplay = 0;
                    Minecraft.getMinecraft().displayGuiScreen(null);
                } else if(query.substring(1).split(":")[0].equals("getDataOfPlayer")) {
                    cb.success(Objects.requireNonNull(PlayableJobs.listPerso.getPersoFromName(ClientProxy.createPersoLastCharName)).getName() + ":" + Objects.requireNonNull(PlayableJobs.listPerso.getPersoFromName(ClientProxy.createPersoLastCharName)).name());
                }



            } else
                cb.failure(403, "Can't access username from external page");

            return true;
        }

        return false;
    }

    public void closeActiveGui() {
        //this.executeJS("vue.closeAll();");
        this.mc.displayGuiScreen((GuiScreen)null);
    }

    public void cancelQuery(final IBrowser iBrowser, final long l) {
    }

    public void openBrowserScreen() {
        Minecraft.getMinecraft().displayGuiScreen(this);
    }

    public void openMenu() {
        this.openBrowserScreen();
    }

    public void executeJS(String jsString) {
        this.browser.runJS(jsString, "");
    }

    public String getTextFromUrl() {
        final String string = this.browser.getURL();
        final String[] splitString = string.split("#");
        return splitString[1];
    }
}