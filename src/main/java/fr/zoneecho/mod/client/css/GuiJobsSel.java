package fr.zoneecho.mod.client.css;

import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiJobsSel extends GuiFrame {
    public GuiJobsSel() {
        super(new GuiScaler.Identity());

        GuiPanel panel1 = new GuiPanel();
        panel1.setCssId("panel1");

        GuiPanel job1 = new GuiPanel();
        job1.setCssId("job1");
        GuiLabel job1Label = new GuiLabel("Scientifique");
        job1Label.setText("Scientifique");
        job1Label.setCssClass("jobLabel");
        job1.add(job1Label);
        panel1.add(job1);

        GuiPanel job2 = new GuiPanel();
        GuiLabel job2Label = new GuiLabel("Sécurité");
        job2Label.setText("Sécurité");
        job2Label.setCssClass("jobLabel");
        job2.setCssId("job2");
        job2.add(job2Label);
        panel1.add(job2);

        GuiPanel job3 = new GuiPanel();
        GuiLabel job3Label = new GuiLabel("Médecine");
        job3Label.setText("Médecine");
        job3Label.setCssClass("jobLabel");
        job3.add(job3Label);
        job3.setCssId("job3");
        panel1.add(job3);

        GuiPanel job4 = new GuiPanel();
        GuiLabel job4Label = new GuiLabel("Administration");
        job4Label.setText("Administration");
        job4Label.setCssClass("jobLabel");
        job4.add(job4Label);
        job4.setCssId("job4");
        panel1.add(job4);

        GuiPanel job5 = new GuiPanel();
        GuiLabel job5Label = new GuiLabel("DI&ST");
        job5Label.setText("DI&ST");
        job5Label.setCssClass("jobLabel");
        job5.add(job5Label);
        job5.setCssId("job5");
        panel1.add(job5);

        GuiPanel job6 = new GuiPanel();
        GuiLabel job6Label = new GuiLabel("SCP");
        job6Label.setText("SCP");
        job6Label.setCssClass("jobLabel");
        job6.add(job6Label);
        job6.setCssId("job6");
        panel1.add(job6);

        job1.addClickListener((e,x,y) -> {
            // ZoneEcho.network.sendToServer(new PacketChangeJob("sci"));
        });

        add(panel1);
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/jobs.css"));
    }
}
