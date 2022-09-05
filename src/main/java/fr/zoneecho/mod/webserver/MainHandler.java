package fr.zoneecho.mod.webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.io.OutputStream;

public class MainHandler implements HttpHandler {
    @SideOnly(Side.SERVER)
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Hello World!";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
