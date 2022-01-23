package fr.oceanrp.mod.util.packets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.aym.acsguis.api.ACsGuiApi;
import fr.oceanrp.mod.util.Ref;
import fr.oceanrp.mod.util.gui.IdentityGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

public class ApiRequest implements IMessage {

    private static String text;
    private static String name;
    private static String surname;

    public ApiRequest() { }

    public ApiRequest(String text, String name, String surname) {
        ApiRequest.text = text;
        ApiRequest.name = name;
        ApiRequest.surname = surname;
        System.out.println(text);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<ApiRequest, IMessage> {

        @Override
        public IMessage onMessage(ApiRequest message, MessageContext ctx) {

            if(Objects.equals(text, "PUT DATA")) {


                System.out.println(ctx.getServerHandler().player.getUniqueID().toString());

                ctx.getServerHandler().player.sendMessage(new TextComponentString("§C> Nom / Prénom RP WIP."));

                URL url = null;
                try {
                    url = new URL("https://" + Ref.API_IP +  "/user/" +  ctx.getServerHandler().player.getUniqueID().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection httpCon = null;
                try {
                    httpCon = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpCon.setDoOutput(true);
                try {
                    httpCon.setRequestMethod("PUT");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
                OutputStreamWriter out = null;

                httpCon.setRequestProperty("user-agent", "Mozilla/5.0 (Compatible; Postman) ");
                httpCon.setRequestProperty("Authorization", "Basic Z2FiaWR1dDo2N3R1ZGliYUc=");

                try {
                    out = new OutputStreamWriter(httpCon.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.write("{\"name\":\"" + name + "\",\"surname\":\"" + surname + "\"}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    httpCon.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }




            } else {

                URL url = null;
                try {
                    url = new URL("https://" + Ref.API_IP +  "/user/" +  ctx.getServerHandler().player.getUniqueID().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection con = null;
                try {
                    con = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    con.setRequestMethod("GET");
                    con.setRequestProperty("user-agent", "Mozilla/5.0 (Compatible; Postman) ");
                    con.setRequestProperty("Authorization", "Basic Z2FiaWR1dDo2N3R1ZGliYUc=");
                    con.connect();
                    System.out.println(ctx.getServerHandler().player.getUniqueID().toString());
                    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();

                        // print result
                        if(jsonObject.get("name").isJsonNull()) {



                        } else {
                            Ref.RP_NAME = jsonObject.get("name").getAsString();
                            Ref.RP_SURNAME = jsonObject.get("surname").getAsString();
                            System.out.println("Enregistrement du nom & prénom dans la ref");
                            System.out.println(ctx.getServerHandler().player.getUniqueID());
                        }




                    } else {
                        ACsGuiApi.asyncLoadThenShowGui("IdentityGui", () -> new IdentityGui(ctx.getServerHandler().player));
                        ctx.getServerHandler().player.sendMessage(new TextComponentString("Une erreur est survenue."));
                    }
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(String.format("Received \"%s\" from " + ctx.getServerHandler().player.getDisplayName(), message.text));
            return null; // no response in this case
        }
    }
}

