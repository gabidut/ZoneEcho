package fr.zoneecho.mod.init;

import fr.zoneecho.mod.util.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = Ref.MODID)
public class SoundInit {
    public static final SoundEvent PING_SPEAKER = createSoundEvent("master.ping");
    public static final SoundEvent KEYCARD_NO = createSoundEvent("master.keycardno");
    public static final SoundEvent KEYCARD_OK = createSoundEvent("master.keycardok");
    public static final SoundEvent ALARM_ANY = createSoundEvent("master.alarm");
    public static final SoundEvent OTHERBECAUSEITSFUNNY = createSoundEvent("prout");
    public static final SoundEvent CLICK_BUTTON_SOUND = createSoundEvent("clickbuttonsound");

    private static SoundEvent createSoundEvent(String soundName) {
        ResourceLocation soundID = new ResourceLocation("zoneecho", soundName);
        return (SoundEvent)(new SoundEvent(soundID)).setRegistryName(soundID);
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        System.out.println("Registring sounds...");
        event.getRegistry().registerAll(PING_SPEAKER, OTHERBECAUSEITSFUNNY, KEYCARD_NO, KEYCARD_OK, ALARM_ANY, CLICK_BUTTON_SOUND);
    }
}
