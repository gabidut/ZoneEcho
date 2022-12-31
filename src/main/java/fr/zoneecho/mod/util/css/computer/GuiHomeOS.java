package fr.zoneecho.mod.util.css.computer;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.zoneecho.mod.util.css.computer.intranet.GuiIntranet;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GuiHomeOS extends GuiFrame {
    public GuiHomeOS() {
        super(new GuiScaler.Identity());
        setCssClass("background");

        GuiPanel hour = new GuiPanel();
        hour.setCssClass("hour");

        GuiPanel hour2 = new GuiPanel();
        hour2.setCssId("hour2");

        GuiLabel hourLabel = new GuiLabel("");
        hourLabel.setCssClass("hourlabel");

        hourLabel.addTickListener(() -> {
            SimpleDateFormat formatMinutes = new SimpleDateFormat("mm");
            String getMinutes = formatMinutes.format(new Date());

            SimpleDateFormat formatHours = new SimpleDateFormat("HH");
            String getHours = formatHours.format(new Date());
            hourLabel.setText(getHours + ":" + getMinutes);
        });

        GuiPanel intranet = new GuiPanel();
        intranet.setCssClass("button");
        intranet.setCssId("intranet");

        GuiPanel intranet_bottom = new GuiPanel();
        intranet_bottom.setCssClass("button_bottom");
        intranet.add(intranet_bottom);

        GuiLabel intranet_label = new GuiLabel("Intranet");
        intranet_label.setCssClass("labelbutton");
        intranet.add(intranet_label);

        GuiPanel intranet_icon = new GuiPanel();
        intranet_icon.setCssClass("icon");
        intranet_icon.setCssId("intranet_icon");
        intranet.add(intranet_icon);

        intranet.addClickListener((x, y, bu) -> {
            ACsGuiApi.asyncLoadThenShowGui("intranet", GuiIntranet::new);
        });

        GuiPanel bdd = new GuiPanel();
        bdd.setCssClass("button");
        bdd.setCssId("bdd");

        GuiPanel bdd_bottom = new GuiPanel();
        bdd_bottom.setCssClass("button_bottom");
        bdd.add(bdd_bottom);

        GuiLabel bdd_label = new GuiLabel("Base de données");
        bdd_label.setCssClass("labelbutton");
        bdd.add(bdd_label);

        GuiPanel bdd_icon = new GuiPanel();
        bdd_icon.setCssClass("icon");
        bdd_icon.setCssId("bdd_icon");
        bdd.add(bdd_icon);

        bdd.addClickListener((x, y, bu) -> {
            bdd_label.setText(TextFormatting.RED + "Indisponible");
        });

        add(intranet);
        add(bdd);

        add(hour);
        hour.add(hourLabel);
        hour.add(hour2);

    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho:css/computer/homeos.css"));
    }
}
