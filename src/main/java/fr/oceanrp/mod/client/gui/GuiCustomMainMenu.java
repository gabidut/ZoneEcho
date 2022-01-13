package fr.oceanrp.mod.client.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import fr.oceanrp.mod.Main;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.ServerData;
import org.lwjgl.opengl.GL11;

import fr.oceanrp.mod.util.Ref;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class GuiCustomMainMenu extends GuiScreen
{

    private static final ResourceLocation[] ALL = new ResourceLocation[]{getDiapo("bg1"), getDiapo("bg2"), getDiapo("bg3"), getDiapo("bg4"), getDiapo("bg5")};
    private ResourceLocation actual = ALL[0];
    private long lastUpdated = -1;

    private ResourceLocation playLoc = new ResourceLocation(Ref.MODID, "textures/gui/mainmenu/play.png");
    private ResourceLocation solo = new ResourceLocation(Ref.MODID, "textures/gui/mainmenu/solo.png");
    private ResourceLocation quitLoc = new ResourceLocation(Ref.MODID, "textures/gui/mainmenu/quitter.png");
    private ResourceLocation paramLoc = new ResourceLocation(Ref.MODID, "textures/gui/mainmenu/param.png");



    @Override
    public void initGui()
    {
        super.initGui();

        FMLClientHandler.instance().setupServerList();

        int y = height / 2 - 80;
        this.addButton(new Button(0, 40, y, 70, 25, playLoc));
        this.addButton(new Button(1, 30, y + 50, 100, 25, paramLoc));
        this.addButton(new Button(3, 35, y + 100, 89, 25, quitLoc));
        this.addButton(new Button(2, 35, y + 15, 89, 25, solo));




    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {

        if(this.actual == null || this.lastUpdated == -1 || System.currentTimeMillis() - this.lastUpdated > 25000)
        {
            defineActualImage();
        }

        drawImage(0, 0, this.actual, this.width, this.height);

        GlStateManager.pushMatrix();


        GlStateManager.translate(0, -120, -120);
        GlStateManager.rotate(25, 0, 0, 1);
        drawRect(0, 0, 250, 400, new Color(0, 0, 0, 100).getRGB());

        GlStateManager.popMatrix();


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void defineActualImage()
    {
        ResourceLocation n = this.actual;
        while (n.getResourcePath().equalsIgnoreCase(this.actual.getResourcePath()));
        {
            this.actual = ALL[new Random().nextInt(ALL.length)];
        }
        this.lastUpdated = System.currentTimeMillis();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id == 0)
        {
            FMLClientHandler.instance().connectToServer(this, new ServerData("ocrp", Ref.SERVER_IP, false));
            // Main.JoinEvent(Minecraft.getMinecraft().player);
            // Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this));

        }
        else if(button.id == 1)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiOptions(this, Minecraft.getMinecraft().gameSettings));
        }
        else if(button.id == 2)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiWorldSelection(this));
        }
        else if(button.id == 3)
        {
            System.exit(0);
        }
        super.actionPerformed(button);
    }

    private static ResourceLocation getDiapo(String name)
    {
        return new ResourceLocation(Ref.MODID, "textures/gui/mainmenu/" + name + ".png");
    }

    public static void drawImage(double x, double y, ResourceLocation image, double width, double height)
    {
        drawImage(x, y, image, width, height, 1.0f);
    }
    public static void drawImage(double x, double y, ResourceLocation image, double width, double height, float alpha)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.enableAlpha();
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0).tex(0.0, 1.0).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0).tex(1, 1).endVertex();
        bufferbuilder.pos(x + width, y, 0.0).tex(1, 0).endVertex();
        bufferbuilder.pos(x, y, 0.0).tex(0, 0).endVertex();
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDisable(2832);
    }

    public class Button extends GuiButton
    {

        private ResourceLocation loc;

        public Button(int buttonId, int x, int y, int width, int height, ResourceLocation loc)
        {
            super(buttonId, x, y, width, height, "");
            this.loc = loc;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
        {
            GlStateManager.pushMatrix();
            drawImage(x, y, this.loc, this.width, this.height);
            GlStateManager.popMatrix();
        }
    }
}
