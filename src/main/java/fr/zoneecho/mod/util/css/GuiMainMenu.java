package fr.zoneecho.mod.util.css;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.aym.acsguis.component.layout.GuiScaler;
import fr.aym.acsguis.component.panel.GuiFrame;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.dynamx.utils.DynamXLoadingTasks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.CPacketLoginStart;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class GuiMainMenu extends GuiFrame {

    public GuiMainMenu() throws IOException {
        super(new GuiScaler.Identity());
        GuiPanel panel = new GuiPanel();
        panel.setCssClass("main");

        GuiLabel title = new GuiLabel("Choissisez votre personnage");
        title.setCssClass("title");
        add(title);

        GuiPanel panel1 = new GuiPanel();
        panel1.setCssClass("pan1");
        GuiLabel label = new GuiLabel("Jouer");
        label.setCssClass("label");
        panel1.add(label);

        GuiPanel panel2 = new GuiPanel();
        panel2.setCssClass("pan2");
        GuiLabel label2 = new GuiLabel("Options");
        label2.setCssClass("label");
        panel2.add(label2);

        GuiPanel panel3 = new GuiPanel();
        panel3.setCssClass("pan3");
        GuiLabel label3 = new GuiLabel("Discord");
        label3.setCssClass("label");
        panel3.add(label3);

        GuiPanel panel4 = new GuiPanel();
        panel4.setCssClass("pan4");
        GuiLabel label4 = new GuiLabel("Quitter");
        label4.setCssClass("label1");
        panel4.add(label4);

        GuiLabel actuTitle = new GuiLabel("Actualités :");
        actuTitle.setCssClass("actuTitle");
        panel.add(actuTitle);

        GuiPanel rl = new GuiPanel();
        rl.setCssClass("rl");
        rl.addClickListener((x,y,u) -> {
            DynamXLoadingTasks.reload(DynamXLoadingTasks.TaskContext.CLIENT, () -> {

            }, ACsGuiApi.CSS_ERROR_TYPE);
        });


        URL obj = new URL("https://api.zone-echo.tk/news/text.txt");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            GuiLabel guiTxt = new GuiLabel(response.toString());
            guiTxt.setCssClass("actuC");
            panel.add(guiTxt);


        } else {
            System.out.println("GET request not worked");
        }



        panel.add(panel1);
        panel.addClickListener((x,y,by) -> {
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiScreenServerList(this.getGuiScreen(), Objects.requireNonNull(Minecraft.getMinecraft().getCurrentServerData())));
            } else {
                Minecraft.getMinecraft().displayGuiScreen(new GuiWorldSelection(this.getGuiScreen()));
            }

        });
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        add(panel);


    }


    AtomicInteger CONNECTION_ID = new AtomicInteger(0);
    NetworkManager networkManager;
    void connect(final String ip, final int port)
    {
        CONNECTION_ID = null;
        (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet())
        {
            public void run()
            {
                InetAddress inetaddress = null;

                try
                {

                    inetaddress = InetAddress.getByName(ip);
                    networkManager = NetworkManager.createNetworkManagerAndConnect(inetaddress, port, mc.gameSettings.isUsingNativeTransport());
                    networkManager.setNetHandler(new NetHandlerLoginClient(networkManager, mc,new GuiMainMenu().getGuiScreen()));
                    networkManager.sendPacket(new C00Handshake(ip, port, EnumConnectionState.LOGIN, true));
                    networkManager.sendPacket(new CPacketLoginStart(mc.getSession().getProfile()));
                }
                catch (UnknownHostException unknownhostexception)
                {
                    try {
                        mc.displayGuiScreen(new GuiMainMenu().getGuiScreen());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception exception)
                {

                    String s = exception.toString();

                    if (inetaddress != null)
                    {
                        String s1 = inetaddress + ":" + port;
                        s = s.replaceAll(s1, "");
                    }
                    try {
                        mc.displayGuiScreen(new GuiMainMenu().getGuiScreen());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }


    @Override
    public List<ResourceLocation> getCssStyles() {
        return Collections.singletonList(new ResourceLocation("zoneecho","css/mainmenu.css"));
    }

    @Override
    public boolean needsCssReload() {
        return false;
    }

}
