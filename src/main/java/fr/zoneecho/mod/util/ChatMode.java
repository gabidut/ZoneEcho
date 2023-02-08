package fr.zoneecho.mod.util;

public enum ChatMode {
    RP("$rp"),
    HRP("$hrp"),
    ADMIN("$admin"), // NOTE, This 2 are checked by server :)
    BROADCAST("$broadcast");

    private final String value;
    ChatMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ChatMode getChatMode(String message) {
        if(message.startsWith("$rp")) {
            return RP;
        } else if(message.startsWith("$hrp")) {
            return HRP;
        } else if(message.startsWith("$admin")) {
            return ADMIN;
        } else if(message.startsWith("$broadcast")) {
            return BROADCAST;
        } else {
            return null;
        }
    }

}
