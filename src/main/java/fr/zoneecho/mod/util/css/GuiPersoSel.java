package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.api.ACsGuiApi;
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

        GuiPanel panel1 = new GuiPanel();
        panel1.setCssId("panel1");


        GuiPanel job1 = new GuiPanel();
        job1.setCssId("job1");



        GuiLabel job1Label = new GuiLabel("Personage principal");
        job1Label.setText("Personage principal");
        job1Label.setCssClass("persoLabel");
        job1.add(job1Label);
        panel1.add(job1);
        GuiLabel job1Label1 = new GuiLabel("1");
        job1Label1.setCssClass("persoLabel2");
        panel1.add(job1Label1);

        GuiPanel job2 = new GuiPanel();
        GuiLabel job2Label = new GuiLabel("Personage secondaire");
        job2Label.setText("Personage secondaire");
        job2Label.setCssClass("persoLabel");
        job2.setCssId("job2");
        job2.add(job2Label);
        panel1.add(job2);
        GuiLabel job2Label1 = new GuiLabel("2");
        job2Label1.setCssClass("persoLabel3");
        panel1.add(job2Label1);

        GuiPanel job3 = new GuiPanel();
        GuiLabel job3Label = new GuiLabel("Personage jetable");
        job3Label.setText("Personage jetable");
        job3Label.setCssClass("persoLabel");
        job3.add(job3Label);
        job3.setCssId("job3");
        panel1.add(job3);
        GuiLabel job3Label1 = new GuiLabel("J");
        job3Label1.setCssClass("persoLabel4");
        panel1.add(job3Label1);

        job3.addClickListener((e,x,y) -> {
            ACsGuiApi.closeHudGui();
            ACsGuiApi.asyncLoadThenShowGui("job", GuiJobsSel::new);
        });

        job2.addClickListener((e,x,y) -> {
            ACsGuiApi.closeHudGui();
            // ZoneEcho.network.sendToServer(new PacketChangeJob(PlayableJobs.listPerso.second.name(), 1));
        });

        job1.addClickListener((e,x,y) -> {
           // ZoneEcho.network.sendToServer(new PacketChangeJob(PlayableJobs.listPerso.main.name(), 1));
        });

        add(panel1);
    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/perso.css"));
    }
}
