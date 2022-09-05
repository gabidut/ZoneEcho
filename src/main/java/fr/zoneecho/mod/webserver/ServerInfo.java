package fr.zoneecho.mod.webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;


public class ServerInfo implements HttpHandler {

    @SideOnly(Side.SERVER)
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "ok," + Objects.requireNonNull(Minecraft.getMinecraft().getConnection()).currentServerMaxPlayers;
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
