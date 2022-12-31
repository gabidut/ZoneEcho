package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.zoneecho.mod.util.GuiTemplates;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiPersoCreate extends GuiFrame {
    public GuiPersoCreate() {
        super(new GuiScaler.Identity());

        GuiTemplates.Template panel = new GuiTemplates.Template("Création de votre personnage {principal}.");

        GuiButton security = new GuiButton("Sécurité");
        security.setCssClass("security");

        GuiButton medic = new GuiButton("Médical");
        medic.setCssClass("medic");

        GuiButton scientific = new GuiButton("Scientifique");
        scientific.setCssClass("scientific");

        panel.getRight().add(security);
        panel.getRight().add(medic);
        panel.getRight().add(scientific);

        add(panel.getPanel());
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/jobs.css"));
    }
}
