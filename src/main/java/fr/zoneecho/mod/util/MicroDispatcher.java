package fr.zoneecho.mod.util;

import fr.nathanael2611.modularvoicechat.api.VoiceDispatchEvent;
import fr.nathanael2611.modularvoicechat.api.dispatcher.IVoiceDispatcher;

public class MicroDispatcher implements IVoiceDispatcher {
    @Override
    public void dispatch(VoiceDispatchEvent e) {
        e.dispatchToAllExceptSpeaker();
    }
}
