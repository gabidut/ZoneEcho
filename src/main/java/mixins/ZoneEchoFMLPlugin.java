package mixins;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class ZoneEchoFMLPlugin implements IFMLLoadingPlugin {
    public ZoneEchoFMLPlugin() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.zoneecho.json");
        System.out.println("[ZoneEcho] MixinBootstrap.init()");
        try {
            Launch.classLoader.findClass("fr.zoneecho.mod.util.Texture");
            Class.forName("fr.zoneecho.mod.util.Texture", true, Launch.classLoader);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return  new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // load a class
        try {
            Launch.classLoader.findClass("fr.zoneecho.mod.util.Texture");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
