package fr.zoneecho.mod.client.css;

import fr.aym.acsguis.component.entity.GuiEntityRender;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.zoneecho.mod.common.utils.DataAboutMe;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiAboutMe extends GuiFrame {
    public GuiAboutMe(DataAboutMe data) {
        super(new GuiScaler.Identity());

        GuiPanel left = new GuiPanel();
        left.setCssClass("left");
        GuiPanel right = new GuiPanel();
        right.setCssClass("right");

        GuiPanel title = new GuiPanel();
        title.setCssClass("title");

        GuiLabel job2 = new GuiLabel("Métier : " + data.getJob());
        job2.setCssClass("job");

        GuiLabel skills = new GuiLabel("Département :");
        skills.setCssClass("skills");

        GuiLabel speciality = new GuiLabel("Spécialité :");
        speciality.setCssClass("speciality");

        GuiLabel spelvl = new GuiLabel("Niveau :");
        spelvl.setCssClass("spelvl");

        GuiLabel accreditation = new GuiLabel("Accréditation :");
        accreditation.setCssClass("accreditation");

        GuiLabel internalClass = new GuiLabel("Classe :");
        internalClass.setCssClass("internalClass");

        GuiPanel title1 = new GuiPanel();
        title1.setCssClass("title1");

        GuiEntityRender render = new GuiEntityRender(Minecraft.getMinecraft().player);
        render.setCssClass("render");

        GuiPanel renderBackground = new GuiPanel();
        renderBackground.setCssClass("renderBackground");

        GuiLabel name = new GuiLabel(data.getName());
        name.setCssClass("name");

        GuiLabel Surname = new GuiLabel(data.getSurname());
        Surname.setCssClass("surname");

        GuiLabel birth = new GuiLabel("01/01/2000");
        birth.setCssClass("birth");

        GuiLabel job = new GuiLabel("Membre du personnel de la Zone-Echo.");
        job.setCssClass("job2");

        left.add(title);
        left.add(job2);
        left.add(skills);
        left.add(speciality);
        left.add(accreditation);
        left.add(internalClass);
        left.add(spelvl);

        right.add(title1);
        right.add(renderBackground);
        renderBackground.add(render);
        right.add(name);
        right.add(Surname);
        right.add(birth);

        add(left);
        add(right);
    }


        @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/aboutme.css"));
    }
}
