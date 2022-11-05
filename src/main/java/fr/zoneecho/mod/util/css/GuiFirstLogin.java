package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.component.entity.GuiEntityRender;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiFirstLogin extends GuiFrame {
    public GuiFirstLogin() {
        super(new GuiScaler.Identity());

        GuiPanel panel1 = new GuiPanel();
        panel1.setCssId("panel1");

        GuiPanel playerpanel = new GuiPanel();
        playerpanel.setCssId("playerpanel");
        GuiEntityRender player = new GuiEntityRender(Minecraft.getMinecraft().player);
        player.setCssId("player");
        panel1.add(player);
        panel1.add(playerpanel);

        GuiPanel topBar = new GuiPanel();
        topBar.setCssId("topBar");
        panel1.add(topBar);

        GuiLabel playerlabel = new GuiLabel(Minecraft.getMinecraft().player.getDisplayNameString());
        playerlabel.setCssClass("textdefault");
        playerlabel.setCssId("playerlabel");
        panel1.add(playerlabel);

        add(panel1);
    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/aboutme.css"));
    }
}
