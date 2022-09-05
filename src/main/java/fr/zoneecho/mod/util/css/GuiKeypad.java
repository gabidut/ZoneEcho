package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.component.button.GuiButton;
import fr.aym.acsguis.component.layout.GridLayout;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiTextArea;
import fr.zoneecho.mod.ZoneEcho;
import fr.zoneecho.mod.init.SoundInit;
import fr.zoneecho.mod.util.network.PacketPlayKeycard;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.Collections;
import java.util.List;

public class GuiKeypad extends GuiFrame {

    public GuiKeypad(String pos, String code) {
        super(new GuiScaler.Identity());

        GuiPanel background = new GuiPanel();
        background.setCssClass("background");

        GuiTextArea saisieText;
        saisieText = (GuiTextArea) new GuiTextArea(0, 0, 0, 0).setCssClass("saisie");
        saisieText.setMaxTextLength(4);

        GuiPanel select = new GuiPanel();
        select.setCssClass("select");

        GuiButton one = new GuiButton("1");
        one.setCssClass("button");

        GuiButton two = new GuiButton("2");
        two.setCssClass("button");

        GuiButton three = new GuiButton("3");
        three.setCssClass("button");

        GuiButton four = new GuiButton("4");
        four.setCssClass("button");

        GuiButton five = new GuiButton("5");
        five.setCssClass("button");

        GuiButton six = new GuiButton("6");
        six.setCssClass("button");

        GuiButton seven = new GuiButton("7");
        seven.setCssClass("button");

        GuiButton eight = new GuiButton("8");
        eight.setCssClass("button");

        GuiButton nine = new GuiButton("9");
        nine.setCssClass("button");

        GuiButton zero = new GuiButton("0");
        zero.setCssClass("button");

        GuiPanel clear = new GuiPanel();
        clear.setCssClass("button");
        clear.setCssId("clear");

        GuiPanel enter = new GuiPanel();
        enter.setCssClass("button");
        enter.setCssId("enter");

        background.add(saisieText);

        select.setLayout(new GridLayout(40, 40, 10, GridLayout.GridDirection.HORIZONTAL, 3));
        select.add(one);
        select.add(two);
        select.add(three);
        select.add(four);
        select.add(five);
        select.add(six);
        select.add(seven);
        select.add(eight);
        select.add(nine);
        select.add(clear);
        select.add(zero);
        select.add(enter);

        one.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "1");
        });

        two.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "2");
        });

        three.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "3");
        });

        four.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "4");
        });

        five.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "5");
        });

        six.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "6");
        });

        seven.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "7");
        });

        eight.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "8");
        });

        nine.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "9");
        });

        zero.addClickListener((x, y, bu) -> {
            saisieText.setText(saisieText.getText() + "0");
        });

        clear.addClickListener((x, y, bu) -> {
            saisieText.setText("");
        });

        enter.addClickListener((x, y, bu) -> {
            String[] split = pos.split(",");
            if(saisieText.getText().equals(code)) {
                Minecraft.getMinecraft().player.playSound(SoundInit.KEYCARD_OK, 1, 1);
                ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(1), new NetworkRegistry.TargetPoint(Minecraft.getMinecraft().world.provider.getDimension(), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), 10));

            } else {
                Minecraft.getMinecraft().player.playSound(SoundInit.KEYCARD_NO, 1, 1);
                ZoneEcho.network.sendToAllAround(new PacketPlayKeycard(0), new NetworkRegistry.TargetPoint(Minecraft.getMinecraft().world.provider.getDimension(), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), 10));

            }
            Minecraft.getMinecraft().displayGuiScreen(null);
        });

        background.add(select);
        add(background);

    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho:css/code.css"));
    }
}
