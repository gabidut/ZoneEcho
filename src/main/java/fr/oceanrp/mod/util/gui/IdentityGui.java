package fr.oceanrp.mod.util.gui;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import fr.dynamx.common.entities.BaseVehicleEntity;
import fr.dynamx.common.entities.vehicles.CarEntity;
import fr.oceanrp.mod.Main;
import fr.oceanrp.mod.util.Ref;
import fr.oceanrp.mod.util.packets.ApiRequest;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import javax.xml.ws.handler.MessageContext;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IdentityGui extends GuiFrame
{

    EntityPlayerMP ctx;

    public IdentityGui(EntityPlayerMP ctx)
    {

        super(new GuiScaler.Identity());
        this.ctx = ctx;
        // setCssCode("stylesheet.css");
        style.setBackgroundColor(Color.TRANSLUCENT);

        setCssClass("home");
        GuiPanel Field = new GuiPanel(0, 20, 0, 0);

        GuiTextField name = new GuiTextField();
        GuiTextField surname = new GuiTextField();

        GuiButton btn = new GuiButton("Valider");
        btn.addClickListener((x,y,bu) -> {

            Main.network.sendToServer(new ApiRequest("PUT DATA", surname.getText(), name.getText()));

        });
        name.getStyle().getYPos().setAbsolute(50);
        surname.getStyle().getYPos().setAbsolute(150);
        Field.add(name);
        Field.add(surname);
        Field.add(btn);
        add(Field);


    }

    public IdentityGui(GuiScaler scale) {
        super(scale);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Arrays.asList(new ResourceLocation(Ref.MODID + "/gui/identity/stylesheet.css"));
    }
}
