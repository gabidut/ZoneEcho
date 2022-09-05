package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiSpe extends GuiFrame {

    public GuiSpe(String spe, int lvlS1) {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();
        GuiLabel speL = new GuiLabel(spe);
        speL.setText("Spécialité : " + spe);
        speL.setCssId("speL");

        GuiLabel lvl = new GuiLabel(String.valueOf(lvlS1));
        lvl.setText("Niveau : " + lvlS1);
        lvl.setCssId("lvlL");

        GuiButton val = new GuiButton();
        val.setText("OK");
        val.setCssId("btnok");

        GuiLabel xp = new GuiLabel(String.valueOf(lvlS1));
        xp.setText("XP : " + lvlS1 + " / 100");
        xp.setCssId("xp");

        panel.add(xp);
        panel.add(val);
        panel.add(lvl);
        panel.add(speL);
        panel.setCssId("panel");
        add(panel);
    }

    public GuiSpe() {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();
        GuiLabel speL = new GuiLabel("");
        speL.setText("unexpected usage");

        // panel.add(inter);
        panel.add(speL);
        panel.setCssId("panel");
        add(panel);
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/speciality.css"));
    }
}
