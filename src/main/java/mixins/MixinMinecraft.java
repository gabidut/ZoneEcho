package mixins;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;


@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow
    private static Logger LOGGER;

    @Shadow
    private boolean fullscreen;

    /**
     * @author ZoneEcho
     * @reason because
     */
    @Overwrite
    private void createDisplay() throws LWJGLException {
        Display.setResizable(true);
        System.out.println("[ZoneEcho] MixinMinecraft.createDisplay()");
        // Change Display custom background
        Display.setInitialBackground(0.12f, 0.15f, 0.18f);
        Display.setTitle("ZoneEcho - " + Minecraft.getMinecraft().getVersion() + " - " + Minecraft.getMinecraft().getSession().getUsername());


        try {
            Display.create((new PixelFormat()).withDepthBits(24));
        } catch (LWJGLException lwjglexception) {
            LOGGER.error("Couldn't set pixel format", lwjglexception);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            if (this.fullscreen) {
                this.updateDisplayMode();
            }

            Display.create();


            Rectangle a = Display.getParent().getBounds();
            glPushMatrix();

            glEnable(GL_TEXTURE_2D);
            glDisable(GL_DEPTH_TEST);

            glColor3b((byte) 0, (byte) 0, (byte) 0);

            glOrtho(0, a.getWidth(), a.getHeight(), 0, -1, 1);
            glRectd(50, 50, a.getWidth(), a.getHeight());

            glDisable(GL_TEXTURE_2D);
            glEnable(GL_DEPTH_TEST);
            Display.update();

            glPopMatrix();
        }
    }

    @Shadow
    private void updateDisplayMode() {
    }
}