package fr.zoneecho.mod.util.css.computer;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.panel.GuiTabbedPane;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.utils.GuiTextureSprite;
import fr.zoneecho.mod.common.blocks.SoundInit;
import fr.zoneecho.mod.objects.Rapport;
import fr.zoneecho.mod.util.css.computer.intranet.GuiIntranet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiReadRapport extends GuiFrame {
    public GuiReadRapport(NBTTagCompound nbt) {
        super(new GuiScaler.Identity());
        System.out.println(nbt);
        if(nbt.hasKey("data")) {
           if(!nbt.getString("data").equals("empty")) {


            Rapport rapport = Rapport.fromString(nbt.getString("data"));


            setCssClass("background");
            GuiPanel home = new GuiPanel();
            home.setCssClass("screen");

            //Annonces

            GuiPanel annonce = new GuiPanel();
            annonce.setCssClass("element");
            annonce.setCssId("elementannonce");

            GuiPanel annoncetitle = new GuiPanel();
            annoncetitle.setCssClass("element_title");

            GuiPanel annoncebartitle = new GuiPanel();
            annoncebartitle.setCssClass("element_title_bar");

            //Votre compte

            GuiPanel compte = new GuiPanel();
            compte.setCssClass("element");
            compte.setCssId("elementcompte");

            GuiPanel comptetitle = new GuiPanel();
            comptetitle.setCssClass("element_title");

            GuiPanel comptebartitle = new GuiPanel();
            comptebartitle.setCssClass("element_title_bar");


            //Administration

            GuiPanel administration = new GuiPanel();
            administration.setCssClass("screen");

            GuiPanel courriels = new GuiPanel();
            courriels.setCssClass("screen");

            GuiPanel documents = new GuiPanel();
            documents.setCssClass("screen");

            GuiPanel alarmes = new GuiPanel();
            alarmes.setCssClass("screen");

            GuiPanel personnels = new GuiPanel();
            personnels.setCssClass("screen");

            //Left bar

            GuiPanel leftBar = new GuiPanel();
            leftBar.setCssClass("leftbar");

            GuiPanel whiteBar = new GuiPanel();
            whiteBar.setCssClass("whitebar");

            GuiPanel logo = new GuiPanel();
            logo.setCssClass("logo");

            //Gui Tabbed Pane

            GuiTabbedPane tabbedPane = new GuiTabbedPane();
            tabbedPane.setCssClass("tabbedpane");

            tabbedPane.addTab("    Accueil   ", home);
            tabbedPane.addTab("Administration", administration);
            tabbedPane.addTab("  Courriels   ", courriels);
            tabbedPane.addTab("  Documents   ", documents);
            tabbedPane.addTab("    Alarmes   ", alarmes);
            tabbedPane.addTab("  Personnels  ", personnels);

            tabbedPane.getTabButton(0).setCssClass("buttonbar");
            tabbedPane.getTabButton(1).setCssClass("buttonbar");
            tabbedPane.getTabButton(2).setCssClass("buttonbar");
            tabbedPane.getTabButton(3).setCssClass("buttonbar");
            tabbedPane.getTabButton(4).setCssClass("buttonbar");
            tabbedPane.getTabButton(5).setCssClass("buttonbar");

            tabbedPane.getTabButton(0).setCssId("homeb");
            tabbedPane.getTabButton(1).setCssId("adminb");
            tabbedPane.getTabButton(2).setCssId("courrielsb");
            tabbedPane.getTabButton(3).setCssId("documentsb");
            tabbedPane.getTabButton(4).setCssId("alarmesb");
            tabbedPane.getTabButton(5).setCssId("personnelsb");

            tabbedPane.getTabButton(0).setClickButtonSound(SoundInit.CLICK_BUTTON_SOUND);
            tabbedPane.getTabButton(1).setClickButtonSound(SoundInit.CLICK_BUTTON_SOUND);
            tabbedPane.getTabButton(2).setClickButtonSound(SoundInit.CLICK_BUTTON_SOUND);
            tabbedPane.getTabButton(3).setClickButtonSound(SoundInit.CLICK_BUTTON_SOUND);
            tabbedPane.getTabButton(4).setClickButtonSound(SoundInit.CLICK_BUTTON_SOUND);
            tabbedPane.getTabButton(5).setClickButtonSound(SoundInit.CLICK_BUTTON_SOUND);

            GuiTextureSprite homeIcon = new GuiTextureSprite(new ResourceLocation("zoneecho", "textures/computer/intranet/home.png"));
            GuiTextureSprite adminIcon = new GuiTextureSprite(new ResourceLocation("zoneecho", "textures/computer/intranet/admin.png"));
            GuiTextureSprite courrielsIcon = new GuiTextureSprite(new ResourceLocation("zoneecho", "textures/computer/intranet/courriels.png"));
            GuiTextureSprite documentsIcon = new GuiTextureSprite(new ResourceLocation("zoneecho", "textures/computer/intranet/documents.png"));
            GuiTextureSprite alarmesIcon = new GuiTextureSprite(new ResourceLocation("zoneecho", "textures/computer/intranet/alarmes.png"));
            GuiTextureSprite personnelsIcon = new GuiTextureSprite(new ResourceLocation("zoneecho", "textures/computer/intranet/personnels.png"));

            tabbedPane.getTabButton(0).setIconTexture(homeIcon).setIconPadding(20).setIconHeight(15).setIconWidth(15);
            tabbedPane.getTabButton(1).setIconTexture(adminIcon).setIconPadding(20).setIconHeight(15).setIconWidth(15);
            tabbedPane.getTabButton(2).setIconTexture(courrielsIcon).setIconPadding(20).setIconHeight(15).setIconWidth(15);
            tabbedPane.getTabButton(3).setIconTexture(documentsIcon).setIconPadding(20).setIconHeight(15).setIconWidth(15);
            tabbedPane.getTabButton(4).setIconTexture(alarmesIcon).setIconPadding(20).setIconHeight(15).setIconWidth(15);
            tabbedPane.getTabButton(5).setIconTexture(personnelsIcon).setIconPadding(20).setIconHeight(15).setIconWidth(15);

            tabbedPane.selectTab(3);

            for (int i = 0; i < 5; i++) {
             tabbedPane.getTabButton(i).addClickListener((x,y ,z) -> {
              ACsGuiApi.closeHudGui();
              ACsGuiApi.asyncLoadThenShowGui("intranet", GuiIntranet::new);
             });
            }

            GuiPanel globalRapport = new GuiPanel();
            GuiPanel titleRapport = new GuiPanel();
            GuiPanel barTitleRapport = new GuiPanel();
            globalRapport.setCssClass("globalrapport");

            GuiPanel leftAndRightSeparator = new GuiPanel();
            leftAndRightSeparator.setCssClass("leftandrightseparator");




            GuiPanel leftPanel = new GuiPanel();
            GuiPanel rightPanel = new GuiPanel();

            leftPanel.setCssClass("leftpanel");
            rightPanel.setCssClass("rightpanel");

            GuiLabel content = new GuiLabel(rapport.getContent().replaceAll("&", "§"));
            content.setCssClass("content");
            content.setMaxTextLength(999999999);
            content.setText(rapport.getContent().replaceAll("&", "§"));


            System.out.println(rapport.getContent().replaceAll("&", "§"));
            rightPanel.add(content);
            leftPanel.add(leftAndRightSeparator);
            globalRapport.add(titleRapport);

            globalRapport.add(rightPanel);
            globalRapport.add(leftPanel);


            titleRapport.setCssClass("titleText");
            barTitleRapport.setCssClass("leftbartitle");

            GuiPanel whiteLeft = new GuiPanel();
            whiteLeft.setCssClass("whiteleft");
            barTitleRapport.add(whiteLeft);

            GuiLabel title = new GuiLabel(rapport.getName());
            title.setCssClass("title");
            barTitleRapport.add(title);
            documents.add(globalRapport);
            documents.add(globalRapport);
            documents.add(barTitleRapport);


            add(leftBar);
            add(tabbedPane);
            leftBar.add(whiteBar);
            leftBar.add(logo);
           } else {
               setCssClass("background-error");
               add(new GuiLabel("§f> Error, no data inside key."));
               add(new GuiLabel("§f> SYSTEM EXIT.").setCssClass("a"));
           }
        }
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho:css/computer/rapport.css"));
    }

    @Override
    public boolean doesPauseGame() {
        return false;
    }
}
