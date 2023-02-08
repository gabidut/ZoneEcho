package fr.zoneecho.mod.client.css;

import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiPersoSel extends GuiFrame {
    public GuiPersoSel() {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();
        GuiPanel topPanel = new GuiPanel();

        panel.setCssClass("mainPanel");
        topPanel.setCssClass("topPanel");

        GuiPanel title = new GuiPanel();
        title.setCssClass("title");

        GuiPanel perso1 = new GuiPanel();
        perso1.setCssClass("perso1");

        GuiLabel perso1Label = new GuiLabel("Personnage Principal");
        perso1Label.setCssClass("perso1Label");

        GuiLabel perso1StyleEffect = new GuiLabel("1");
        perso1StyleEffect.setCssClass("perso1StyleEffect");

        perso1.add(perso1Label);
        perso1.add(perso1StyleEffect);

        GuiPanel perso2 = new GuiPanel();
        perso2.setCssClass("perso2");

        GuiLabel perso2Label = new GuiLabel("Personnage Secondaire");
        perso2Label.setCssClass("perso2Label");

        GuiLabel perso2StyleEffect = new GuiLabel("2");
        perso2StyleEffect.setCssClass("perso2StyleEffect");

        perso2.add(perso2Label);
        perso2.add(perso2StyleEffect);

        GuiPanel perso3 = new GuiPanel();
        perso3.setCssClass("perso3");

        GuiLabel perso3Label = new GuiLabel("Personnage Jetable");
        perso3Label.setCssClass("perso3Label");

        GuiLabel perso3StyleEffect = new GuiLabel("J");
        perso3StyleEffect.setCssClass("perso3StyleEffect");

        perso3.add(perso3Label);
        perso3.add(perso3StyleEffect);

        panel.add(perso1);
        panel.add(perso2);
        panel.add(perso3);

        topPanel.add(title);

        add(panel);
        add(topPanel);


    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/perso.css"));
    }
}
