package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

public class GuiEchapMenu extends GuiFrame {
    public GuiEchapMenu() {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();

        GuiPanel background = new GuiPanel();
        background.setCssClass("background");

        GuiButton continueButton = new GuiButton("Reprendre le jeu");
        continueButton.setCssClass("buttonc");

        continueButton.addClickListener((mouseX, mouseY, mouseButton) -> {
            ACsGuiApi.closeHudGui();
            Minecraft.getMinecraft().displayGuiScreen(null);
        });

        GuiButton settingsButtun = new GuiButton("Paramètres");
        settingsButtun.setCssClass("buttons");

        settingsButtun.addClickListener((mouseX, mouseY, mouseButton) -> {

            Minecraft.getMinecraft().displayGuiScreen(new GuiOptions(new GuiIngameMenu(), Minecraft.getMinecraft().gameSettings));
        });

        GuiButton ourDiscordButton = new GuiButton("Notre discord");
        ourDiscordButton.setCssClass("buttond");

        ourDiscordButton.addClickListener((mouseX, mouseY, mouseButton) -> {
            try {
                // open the default web browser
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("https://discord.gg/wBnAUajB2c"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

        GuiButton quitButton = new GuiButton("Se déconnecter");
        quitButton.setCssClass("buttonq");

        quitButton.addClickListener((mouseX, mouseY, mouseButton) -> {
            Minecraft.getMinecraft().world.sendQuittingDisconnectingPacket();
            Minecraft.getMinecraft().loadWorld(null);
            ACsGuiApi.asyncLoadThenShowGui("mainmenu", MainMenu::new);
        });

        background.add(continueButton);
        background.add(settingsButtun);
        background.add(ourDiscordButton);
        background.add(quitButton);

        panel.add(background);
        panel.setCssClass("panel");
        add(panel);
    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/echap.css"));
    }
}
