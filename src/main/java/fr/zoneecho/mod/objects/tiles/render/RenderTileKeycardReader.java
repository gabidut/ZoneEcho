package fr.zoneecho.mod.objects.tiles.render;

import fr.zoneecho.mod.init.ItemInit;
import fr.zoneecho.mod.objects.tiles.TileCardReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumHand;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderTileKeycardReader extends TileEntitySpecialRenderer<TileCardReader> {
    @Override
    public void render(TileCardReader te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);

        GL11.glPushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.translate((float)x, (float)y + 0.56F, (float)z);
        if(te.getBlockMetadata() == 2) {
            GL11.glTranslatef(0.75F, 0.0F, 0.78F);
        }

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.005F, 0.005F, 0.005F);
        GlStateManager.glBegin(GL11.GL_QUADS);
        GlStateManager.glVertex3f((float)x,(float)y ,(float)z);
        GlStateManager.glEnd();
        // GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        System.out.println(te.getBlockMetadata());
        if(Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(ItemInit.WRENCH)) {
            TETVSignRender.drawSplitString(getFontRenderer(), "SL:"+ te.getTileData().getInteger("securitylevel") , 0, 0, 100, Color.red.getRGB());
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();

    }
}
