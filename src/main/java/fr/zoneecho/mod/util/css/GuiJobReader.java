package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiJobReader extends GuiFrame {

    public GuiJobReader(String pos) {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();
        panel.setCssClass("main");

        GuiTextField jobField = new GuiTextField();
        jobField.setCssClass("jobF");

        panel.add(jobField);
        add(panel);
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/jobreader.css"));
    }
}
