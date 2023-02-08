package fr.zoneecho.mod.util.css.computer.intranet;

import fr.aym.acsguis.component.layout.GridLayout;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.panel.GuiTabbedPane;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.utils.GuiTextureSprite;
import fr.zoneecho.mod.common.blocks.SoundInit;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class GuiIntranet extends GuiFrame {

    private static String lastAnnonce = "Test e";

    private static int accreditation = 4;
    private static int iteration = 4;

    private static String classe = "B";

    private static String prenom = "Charles-Ferdinand";
    private static String nom = "d'Artois";
    private static String job = "Directeur du site";
    private static String branche = "Administration";

    public GuiIntranet() {
        super(new GuiScaler.Identity());
        setCssClass("background");

        // Sreen

        //Accueil

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

                GuiLabel annoncebartitlelabel = new GuiLabel("    Annonces");
                annoncebartitlelabel.setCssClass("element_title_label");

                //Votre compte

                GuiPanel compte = new GuiPanel();
                compte.setCssClass("element");
                compte.setCssId("elementcompte");

                GuiPanel comptetitle = new GuiPanel();
                comptetitle.setCssClass("element_title");

                GuiPanel comptebartitle = new GuiPanel();
                comptebartitle.setCssClass("element_title_bar");

                GuiLabel comptebartitlelabel = new GuiLabel("    Votre compte");
                comptebartitlelabel.setCssClass("element_title_label");

                //Vos accès

                GuiPanel access = new GuiPanel();
                access.setCssClass("element");
                access.setCssId("elementaccess");

                GuiPanel accesstitle = new GuiPanel();
                accesstitle.setCssClass("element_title");

                GuiPanel accessbartitle = new GuiPanel();
                accessbartitle.setCssClass("element_title_bar");

                GuiLabel accessbartitlelabel = new GuiLabel("   Vos accès");
                accessbartitlelabel.setCssClass("element_title_label");

                GuiPanel accessbox = new GuiPanel();
                accessbox.setCssClass("accessbox");

                accessbox.setLayout(new GridLayout(100, 100, 25, GridLayout.GridDirection.HORIZONTAL, 3));

                GuiPanel access_accred = new GuiPanel();
                access_accred.setCssClass("accessinfo");

                GuiPanel access_accred_bar = new GuiPanel();
                access_accred_bar.setCssClass("accessinfocolorbar");

                GuiLabel access_accred_label = new GuiLabel("Accréditation");
                access_accred_label.setCssClass("accesstype");

                GuiLabel access_accred_value = new GuiLabel("" + accreditation);
                access_accred_value.setCssClass("accesslevel");

                GuiPanel access_iter = new GuiPanel();
                access_iter.setCssClass("accessinfo");

                GuiPanel access_iter_bar = new GuiPanel();
                access_iter_bar.setCssClass("accessinfocolorbar");

                GuiLabel access_iter_label = new GuiLabel("Itération");
                access_iter_label.setCssClass("accesstype");

                GuiLabel access_iter_value = new GuiLabel("" + iteration);
                access_iter_value.setCssClass("accesslevel");

                GuiPanel access_classe = new GuiPanel();
                access_classe.setCssClass("accessinfo");

                GuiPanel access_classe_bar = new GuiPanel();
                access_classe_bar.setCssClass("accessinfocolorbar");

                GuiLabel access_classe_label = new GuiLabel("Classe");
                access_classe_label.setCssClass("accesstype");

                GuiLabel access_classe_value = new GuiLabel("" + classe);
                access_classe_value.setCssClass("accesslevel");

                accessbox.add(access_accred);
                accessbox.add(access_classe);
                accessbox.add(access_iter);

                access_accred.add(access_accred_value);
                access_accred.add(access_accred_label);
                access_accred.add(access_accred_bar);

                access_iter.add(access_iter_value);
                access_iter.add(access_iter_label);
                access_iter.add(access_iter_bar);

                access_classe.add(access_classe_value);
                access_classe.add(access_classe_label);
                access_classe.add(access_classe_bar);

        home.add(annonce);
        home.add(compte);
        home.add(access);

        annonce.add(annoncetitle);
        annoncetitle.add(annoncebartitlelabel);
        annoncetitle.add(annoncebartitle);

        compte.add(comptetitle);
        comptetitle.add(comptebartitlelabel);
        comptetitle.add(comptebartitle);

        access.add(accesstitle);
        accesstitle.add(accessbartitlelabel);
        accesstitle.add(accessbartitle);
        access.add(accessbox);

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

            tabbedPane.selectTab(0);

        add(leftBar);
        add(tabbedPane);
        leftBar.add(whiteBar);
        leftBar.add(logo);

        }

    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/computer/intranet/intranet.css"));
    }
}
