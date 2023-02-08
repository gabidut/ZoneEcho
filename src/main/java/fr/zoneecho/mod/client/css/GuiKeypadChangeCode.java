package fr.zoneecho.mod.client.css;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketChangeCode;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiKeypadChangeCode extends GuiFrame {

    public GuiKeypadChangeCode(String pos, String code) {
        super(new GuiScaler.AdjustToScreenSize(true, 1920, 1080));
        setCssClass("background");

        GuiPanel title = new GuiPanel();
        title.setCssClass("title");

        GuiLabel titlelabel = new GuiLabel("Outil d'Administration");
        titlelabel.setCssClass("titlelabel");

        GuiTextField codes = new GuiTextField();
        codes.setCssClass("code");
        codes.setMaxTextLength(4);

        codes.setText(code);

        GuiButton send = new GuiButton("Enregistrer");
        send.setCssClass("send");

        send.addClickListener((x, y, bu) -> {
            Minecraft.getMinecraft().displayGuiScreen(null);
            ZoneEcho.network.sendToServer(new PacketChangeCode(pos, codes.getText()));
        });
        title.add(titlelabel);
        add(title);
        add(codes);
        add(send);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho:css/changecode.css"));
    }
}
