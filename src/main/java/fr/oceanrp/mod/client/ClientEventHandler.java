package fr.oceanrp.mod.client;

import fr.oceanrp.mod.client.gui.GuiCustomMainMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ClientEventHandler
{

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event)
    {
        if(event.getGui() instanceof GuiMainMenu)
        {
            event.setGui(new GuiCustomMainMenu());
        }
    }

    @SubscribeEvent
    public void onGuiOpenEvent(GuiScreenEvent event)
    {
        if(event.getGui() instanceof GuiMainMenu)
        {
            int logoLoc = 0;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, logoLoc);
            event.getGui().drawTexturedModalRect(5, 5, 0, 0, 5, 102);
        }
    }

}