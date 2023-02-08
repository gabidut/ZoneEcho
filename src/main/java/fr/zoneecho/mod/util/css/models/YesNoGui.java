package fr.zoneecho.mod.util.css.models;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class YesNoGui  extends GuiFrame {

    public YesNoGui(IYesNoCallback callback, String text) {
        super(new GuiScaler.Identity());

        GuiPanel background = new GuiPanel();
        background.setCssClass("background");

        GuiPanel largerPane = new GuiPanel();
        largerPane.setCssClass("largerPane");

        GuiButton yesButton = new GuiButton();
        yesButton.setCssClass("yesButton");

        yesButton.addClickListener((mouseX, mouseY, mouseButton) -> {
            callback.onConfirm(true);
        });

        GuiButton noButton = new GuiButton();
        noButton.setCssClass("noButton");

        noButton.addClickListener((mouseX, mouseY, mouseButton) -> {
            callback.onConfirm(false);
        });

        GuiLabel title = new GuiLabel(text);
        title.setCssClass("title");


        background.add(title);
        background.add(noButton);

        largerPane.add(noButton);

        add(background);
        add(largerPane);
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho", "css/yesno.css"));
    }
}