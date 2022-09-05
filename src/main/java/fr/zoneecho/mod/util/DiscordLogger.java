package fr.zoneecho.mod.util;

import fr.zoneecho.mod.ZoneEcho;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class DiscordLogger {

    private String message;
    private String authkey;

    public DiscordLogger(String message) throws IOException {
        Properties props = new Properties();
        props.load(new FileReader(new File("secur.properties")));
        this.message = message;
        this.authkey = props.getProperty("zoneechoapitoken");
    }

    public DiscordLogger(preregistred_messages message) throws IOException {

        Properties props = new Properties();
        props.load(new FileReader(new File("secur.properties")));


        this.message = message.getMessage();
        this.authkey = props.getProperty("zoneechoapitoken");
    }

    public void send() throws IOException {
        getrequest(this.message, this.authkey);
    }

    private void getrequest(String data, String token) throws IOException {
        URL obj = new URL(Ref.BASEURLAPI + "authkey=" + token + "&packet=" + data);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == 418 ) { // success
        } else {
            ZoneEcho.logger.error("Cannot contact Discord. Response code: " + responseCode);
        }
    }
    public enum preregistred_messages {
        SERVER_OFF("c2VydmVyc3RvcHBlZA=="),
        SERVER_START("c2VydmVyc3N0YXJ0ZWQ="),
        EVENT_START("ZXZlbnRzdGFydGluZw=="),
        SOCKET_CLOSED("YyxTb2NrZXQgYWxlcnQsIGV4dGVybmFsIHNvY2tldCBkaXNjb25lY3RlZC4gU2VydmVyIG5lZWQgdG8gYmUgcmVzdGFydC4sSmF2YSBzaWRlIHNvY2tldCBidWlsdGlu");

        private final String message;
        preregistred_messages(String key) {
            this.message = key;
        }

        public String getMessage() {
            return message;
        }
    }
}
