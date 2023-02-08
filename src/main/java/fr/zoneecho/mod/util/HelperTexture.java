package fr.zoneecho.mod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.asm.FMLSanityChecker;
import org.lwjgl.util.glu.GLU;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.glGetError;

public class HelperTexture {
    private static final IResourcePack mcPack = Minecraft.getMinecraft().mcDefaultResourcePack;
    private static final IResourcePack fmlPack = createResourcePack(FMLSanityChecker.fmlLocation);

    static InputStream open(ResourceLocation loc, @Nullable ResourceLocation fallback, boolean allowResourcePack) throws IOException
    {
        if (!allowResourcePack)
            return mcPack.getInputStream(loc);

        if(fmlPack.resourceExists(loc))
        {
            return fmlPack.getInputStream(loc);
        }
        else if(!mcPack.resourceExists(loc) && fallback != null)
        {
            return open(fallback, null, true);
        }
        return mcPack.getInputStream(loc);
    }

    private static IResourcePack createResourcePack(File file)
    {
        if(file.isDirectory())
        {
            return new FolderResourcePack(file);
        }
        else
        {
            return new FileResourcePack(file);
        }
    }


    public static void checkGLError(String where)
    {
        int err = glGetError();
        if (err != 0)
        {
            throw new IllegalStateException(where + ": " + GLU.gluErrorString(err));
        }
    }

}
