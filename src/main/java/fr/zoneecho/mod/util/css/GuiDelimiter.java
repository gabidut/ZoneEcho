package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import fr.nathanael2611.simpledatabasemanager.client.ClientDatabases;
import fr.nathanael2611.simpledatabasemanager.core.DatabaseReadOnly;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiDelimiter extends GuiFrame {
    public GuiDelimiter() {
        super(new GuiScaler.Identity());
        DatabaseReadOnly db = ClientDatabases.getDatabase("zoneecho_utils");

        String dataOfDelimiter = db.getString("delimiter");
        System.out.println(dataOfDelimiter);
        GuiPanel panel = new GuiPanel();
        panel.setCssClass("main");

        GuiPanel content = new GuiPanel();
        content.setCssClass("content");

        GuiButton button = new GuiButton("Confirmer");
        button.setCssClass("button");
        panel.add(button);

        GuiPanel addButton = new GuiPanel();
        addButton.setCssClass("addButton");



        for (int i = 0; i < 5; i++) {

            GuiPanel delimiter = new GuiPanel();
            delimiter.setCssClass("delimiter");

            delimiter.add(new GuiLabel("Zone #1").setCssClass("label"));


            GuiTextField x1 = new GuiTextField();
            x1.setCssClass("x1");

            GuiTextField y1 = new GuiTextField();
            y1.setCssClass("y1");

            GuiTextField z1 = new GuiTextField();
            z1.setCssClass("z1");

            GuiTextField x2 = new GuiTextField();
            x2.setCssClass("x2");

            GuiTextField y2 = new GuiTextField();
            y2.setCssClass("y2");

            GuiTextField z2 = new GuiTextField();
            z2.setCssClass("z2");

            delimiter.add(x1);
            delimiter.add(y1);
            delimiter.add(z1);

            delimiter.add(x2);
            delimiter.add(y2);
            delimiter.add(z2);

            delimiter.getStyle().setOffsetY((int) (delimiter.getStyle().getYPos().getRawValue() + (i * 20)));


            content.add(delimiter);
        }



        panel.add(content);
        add(panel);

    }



    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/delimiter.css"));
    }
}
