package fr.zoneecho.mod.common.utils;

import net.montoyo.mcef.MCEF;
import net.montoyo.mcef.api.IScheme;
import net.montoyo.mcef.api.ISchemeResponseData;
import net.montoyo.mcef.api.ISchemeResponseHeaders;
import net.montoyo.mcef.api.SchemePreResponse;
import net.montoyo.mcef.example.ModScheme;
import net.montoyo.mcef.utilities.Log;

import java.io.IOException;
import java.io.InputStream;

public class ZoneEchoMCEFSchem implements IScheme {
    private String contentType = null;
    private InputStream is = null;
    @Override
    public SchemePreResponse processRequest(String url) {
        url = url.substring("mod://".length());
        int pos = url.indexOf(47);
        if (pos < 0) {
            return SchemePreResponse.NOT_HANDLED;
        } else {
            String mod = this.removeSlashes(url.substring(0, pos));
            String loc = this.removeSlashes(url.substring(pos + 1));
            if (mod.length() > 0 && loc.length() > 0 && mod.charAt(0) != '.' && loc.charAt(0) != '.') {
                this.is = ModScheme.class.getResourceAsStream("/assets/" + mod.toLowerCase() + "/html/" + loc.toLowerCase());
                if (this.is == null) {
                    Log.warning("Resource " + url + " NOT found!", new Object[0]);
                    return SchemePreResponse.NOT_HANDLED;
                } else {
                    this.contentType = null;
                    pos = loc.lastIndexOf(46);
                    if (pos >= 0 && pos < loc.length() - 2) {
                        this.contentType = MCEF.PROXY.mimeTypeFromExtension(loc.substring(pos + 1));
                    }

                    return SchemePreResponse.HANDLED_CONTINUE;
                }
            } else {
                Log.warning("Invalid URL " + url, new Object[0]);
                return SchemePreResponse.NOT_HANDLED;
            }
        }
    }

    private String removeSlashes(String loc) {
        int i;
        for(i = 0; i < loc.length() && loc.charAt(i) == '/'; ++i) {
        }

        return loc.substring(i);
    }

    public void getResponseHeaders(ISchemeResponseHeaders rep) {
        if (this.contentType != null) {
            rep.setMimeType(this.contentType);
        }

        rep.setStatus(200);
        rep.setStatusText("OK");
        rep.setResponseLength(-1);
    }

    public boolean readResponse(ISchemeResponseData data) {
        try {
            int ret = this.is.read(data.getDataArray(), 0, data.getBytesToRead());
            if (ret <= 0) {
                this.is.close();
            }

            data.setAmountRead(Math.max(ret, 0));
            return ret > 0;
        } catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }

}
