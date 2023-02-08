package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.blocks.SoundInit;
import fr.zoneecho.mod.util.network.PacketUpdateJobReader;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiJobReader extends GuiFrame {

    public GuiJobReader(String pos) {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();
        panel.setCssClass("main");

        GuiPanel topPanel = new GuiPanel();
        topPanel.setCssClass("top");

        GuiPanel rightPane = new GuiPanel();
        rightPane.setCssClass("right");
        GuiPanel rightPaneImg = new GuiPanel();
        rightPaneImg.setCssClass("right-img");

        panel.add(rightPane);
        panel.add(rightPaneImg);

        GuiTextField text_job = new GuiTextField();
        text_job.setCssClass("text_job");
        text_job.setHintText("Métier(s)");
        panel.add(text_job);

        GuiTextField text_player = new GuiTextField();
        text_player.setCssClass("text_player");
        text_player.setHintText("Joueur(s)");
        panel.add(text_player);

        GuiButton button = new GuiButton();
        button.setClickButtonSound(SoundInit.SOUND_VALID);
        button.setText("Valider");
        button.setCssClass("button");
        panel.add(button);


        button.addClickListener((mouseX, mouseY, mouseButton) -> {
            ZoneEcho.network.sendToServer(new PacketUpdateJobReader(pos, text_job.getText(), text_player.getText()));
            ACsGuiApi.closeHudGui();
        });


        panel.add(topPanel);

        add(panel);
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/jobreader.css"));
    }
}
