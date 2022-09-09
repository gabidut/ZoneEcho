package fr.zoneecho.mod.init;

import fr.zoneecho.mod.util.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;


@Mod.EventBusSubscriber(modid = Ref.MODID)
public class SoundInit {

    public static ArrayList<SoundEvent> SoundEvents = new ArrayList<>();
    public static final SoundEvent PING_SPEAKER = createSoundEvent("master.ping");
    public static final SoundEvent KEYCARD_NO = createSoundEvent("master.keycardno");
    public static final SoundEvent KEYCARD_OK = createSoundEvent("master.keycardok");
    public static final SoundEvent ALARM_ANY = createSoundEvent("master.alarm");

    public static final SoundEvent OTHERBECAUSEITSFUNNY = createSoundEvent("prout");
    public static final SoundEvent CLICK_BUTTON_SOUND = createSoundEvent("clickbuttonsound");
    public static final SoundEvent SOUND_GENERATOR = createSoundEvent("master.generator");
    public static final SoundEvent SOUND_GAZ = createSoundEvent("master.gaz");
    public static final SoundEvent SOUND_FIRE = createSoundEvent("master.fire");

    public static final SoundEvent SOUND_PROBLEM = createSoundEvent("master.problem");

    private static SoundEvent createSoundEvent(String soundName) {
        ResourceLocation soundID = new ResourceLocation("zoneecho", soundName);
        SoundEvents.add(new SoundEvent(soundID).setRegistryName(soundID));
        return (SoundEvent)(new SoundEvent(soundID)).setRegistryName(soundID);
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        System.out.println("Registring sounds...");
        SoundEvents.forEach(soundEvent -> event.getRegistry().register(soundEvent));
        // event.getRegistry().registerAll(PING_SPEAKER, OTHERBECAUSEITSFUNNY, KEYCARD_NO, KEYCARD_OK, ALARM_ANY, CLICK_BUTTON_SOUND);
    }
}
