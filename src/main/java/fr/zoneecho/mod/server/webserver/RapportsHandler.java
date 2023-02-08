package fr.zoneecho.mod.server.webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.zoneecho.mod.common.utils.Intranet;
import fr.zoneecho.mod.common.utils.Rapport;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.io.OutputStream;

public class RapportsHandler implements HttpHandler {
    @SideOnly(Side.SERVER)
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder rep = new StringBuilder();

        Intranet.getAllRapports().forEach(rapport -> {
            rep.append(Rapport.toString(rapport)).append("$");
        });


        String response = rep.toString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
