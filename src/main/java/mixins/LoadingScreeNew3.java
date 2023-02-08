package mixins;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;


@Mixin(targets = "net/minecraftforge/fml/client/SplashProgress$2", priority = 999)
public abstract class LoadingScreeNew3 {

    int i = 0;


    @Inject(
            remap = false,
            method = "drawMemoryBar",
            at = @At(
                    value = "HEAD",
                    target = "Lnet/minecraftforge/fml/client/SplashProgress$SplashFontRenderer;drawString(Ljava/lang/String;III)I",
                    args = "log=true"
            )

    )
    private void start(CallbackInfo info) {

        glPushMatrix();
        // Change background color
        glClearColor((float)((0x1F2830 >> 16) & 0xFF) / 0xFF, (float)((0x1F2830 >> 8) & 0xFF) / 0xFF, (float)(0x1F2830 & 0xFF) / 0xFF, 1);

        glPopMatrix();

        TrueTypeFont font = null;
        i++;
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("bauhaus.ttf");

            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(30f); // set font size
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        assert font != null;
        glPushMatrix();
        glColor3f(1, 1, 1);
        glTranslated(0, 10, 0);

        font.drawString(1, 50, "Zone-Echo Roleplay", org.newdawn.slick.Color.white); //x, y, string to draw, color
        if(i <= 10 && i>0) {
            font.drawString(1, 75, "", org.newdawn.slick.Color.white); //x, y, string to draw, color
        }

        if(i <= 20 && i>10) {
            font.drawString(1, 75, ".", org.newdawn.slick.Color.white); //x, y, string to draw, color
        }
        if(i <= 30 && i>20) {
            font.drawString(1, 75, "..", org.newdawn.slick.Color.white); //x, y, string to draw, color
        }

        if(i <= 40 && i>30) {
            font.drawString(1, 75, "...", org.newdawn.slick.Color.white); //x, y, string to draw, color
            i=0;
        }
        glPopMatrix();

    }


    @Inject(
            remap = false,
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;glBegin(I)V",
                    args = "log=true"
            )

    )
    private void start3(CallbackInfo info) {

        glEnd();
        glDisable(GL_TEXTURE_2D);

    }

    @Inject(
            remap = false,
            method = "run",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fml/client/SplashProgress$Texture;texCoord(IFF)V",
                    args = "log=true"
            )

    )
    private void start2(CallbackInfo info) {

        glEnd();
        glDisable(GL_TEXTURE_2D);
    }


    /**
     * @author ZoneEcho
     * @reason because
     */
    @Overwrite(
            remap = false
    )
    private void setColor(int color)
    {

        if(color == 0)
        {
            glColor3ub((byte)((0x1F2830 >> 16) & 0xFF), (byte)((0x1F2830 >> 8) & 0xFF), (byte)(0x1F2830 & 0xFF));
            System.out.println("Color");
        }
        else
        {
            glColor3d((float) ((color >> 16) & 0xFF) / 0xFF, (float) ((color >> 8) & 0xFF) / 0xFF, (float) (color & 0xFF) / 0xFF);
        }
    }


//    /**
//     * @author ZoneEcho
//     * @reason because
//     */
//    @Inject(
//            remap = false,
//            method = "run()V",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraftforge/fml/client/SplashProgress$2;glDisable(I)V"
//            )
//    )
//    private static void start() throws ClassNotFoundException, LWJGLException {
//        System.out.println("Splash");
//
//
//        if (Display.isCreated() && Display.isCurrent()) {
//
//
//            glClearColor((float) ((0x1F2830 >> 16) & 0xFF) / 0xFF, (float) ((0x1F2830 >> 8) & 0xFF) / 0xFF, (float) (0x1F2830 & 0xFF) / 0xFF, 1);
//
//            glColor3d(1, 1, 1);
//
//            glBegin (GL_LINES);//static field
//            glVertex3f(0.50f,-0.50f,0);
//            glVertex3f(-0.50f,0.50f,0);
//            glEnd();
//
//            Display.update();
//        } else {
//            System.out.println("No display");
//            Display.create();
//        }
//
//        Display.sync(100);
//    }


//    /**
//     * @author ZoneEcho
//     * @reason because
//     */
//    @Overwrite
//    public static void finish()
//    {
//        System.out.println("Splash finish");
//        done = true;
//    }


    private static int bytesToMb(long bytes)
    {
        return (int) (bytes / 1024L / 1024L);
    }

}





