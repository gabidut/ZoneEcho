package fr.zoneecho.mod.server.utils;

import fr.nathanael2611.modularvoicechat.api.VoiceDispatchEvent;
import fr.nathanael2611.modularvoicechat.api.dispatcher.IVoiceDispatcher;

public class MicroDispatcher implements IVoiceDispatcher {
    @Override
    public void dispatch(VoiceDispatchEvent e) {
        e.dispatchToAllExceptSpeaker();
    }
}
