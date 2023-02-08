package fr.zoneecho.mod.client.css;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.button.GuiCheckBox;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.common.network.PacketCardWriterEnd;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiCardWriter extends GuiFrame {
    public GuiCardWriter(int i) {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();

        GuiTextField proprio = new GuiTextField();
        proprio.setCssId("proprio");
        proprio.setHintText("Nom Prenom");

        GuiTextField rank = new GuiTextField();
        rank.setCssId("rank");
        rank.setHintText("Rang");

        GuiCheckBox inter = new GuiCheckBox();
        inter.setCssId("intern");
        inter.setText("Interne ?");

        GuiButton val = new GuiButton();
        val.setCssId("val");
        val.setText("Valider");

        val.addClickListener((x,y,bu) -> {
            ZoneEcho.network.sendToServer(new PacketCardWriterEnd(proprio.getText(), rank.getText(), i, inter.isChecked() ? 1 : 0, 0));
            ACsGuiApi.closeHudGui();
        });

        panel.add(inter);
        panel.add(val);
        panel.add(proprio);
        panel.add(rank);
        panel.setCssId("panel");
        add(panel);
    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/cardwriter.css"));
    }
}
