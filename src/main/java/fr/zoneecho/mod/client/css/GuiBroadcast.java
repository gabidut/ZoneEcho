package fr.zoneecho.mod.client.css;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiTextArea;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketEmergency;
import fr.zoneecho.mod.common.network.PacketSetSpeaking;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiBroadcast extends GuiFrame {
    public GuiBroadcast() {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();

        GuiTextArea message = new GuiTextArea();
        message.setCssId("msg");
        message.setHintText("Inserez votre message...");

        GuiButton val = new GuiButton();
        val.setCssId("val");
        val.setText("Valider");


        val.addClickListener((x,y,bu) -> {
            ZoneEcho.network.sendToServer(new PacketEmergency(message.getText()));
            ACsGuiApi.closeHudGui();
        });

        GuiButton speakglob = new GuiButton();
        speakglob.setCssId("speak");
        speakglob.setText("Parler en global");

        speakglob.addClickListener((x,y,bu) -> {
            ZoneEcho.network.sendToServer(new PacketSetSpeaking(Minecraft.getMinecraft().player.getDisplayNameString(), 1));
            ACsGuiApi.closeHudGui();
        });
        panel.add(speakglob);

        GuiButton unspeakglob = new GuiButton();
        unspeakglob.setCssId("unspeak");
        unspeakglob.setText("Ne plus parler en global");

        unspeakglob.addClickListener((x,y,bu) -> {

            ZoneEcho.network.sendToServer(new PacketSetSpeaking(Minecraft.getMinecraft().player.getDisplayNameString(), 0));
            ACsGuiApi.closeHudGui();
        });
        panel.add(unspeakglob);

        panel.add(message);
        panel.add(val);
        panel.setCssId("panel");
        add(panel);
    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/broadcast.css"));
    }
}
