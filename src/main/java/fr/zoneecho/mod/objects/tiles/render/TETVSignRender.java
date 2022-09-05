package fr.zoneecho.mod.objects.tiles.render;

import fr.dynamx.client.renders.TESRDynamXBlock;
import fr.dynamx.common.DynamXContext;
import fr.zoneecho.mod.objects.tiles.TETVSign;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

public class TETVSignRender extends TESRDynamXBlock<TETVSign> {
    public void render(TETVSign te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D + (te.getBlockObjectInfo().getTranslation()).x, y + 1.5D + (te.getBlockObjectInfo().getTranslation()).y, z + 0.5D + (te.getBlockObjectInfo().getTranslation()).z);
        GlStateManager.scale((te.getBlockObjectInfo().getScaleModifier()).x, (te.getBlockObjectInfo().getScaleModifier()).y, (te.getBlockObjectInfo().getScaleModifier()).z);
        GlStateManager.rotate(te.getRotation() * 22.5F + 180, 0.0F, -1.0F, 0.0F);
        DynamXContext.getObjModelRegistry().getModel(te.getBlockObjectInfo().getModel()).renderModel((byte) te.getBlockMetadata());
        GlStateManager.popMatrix();

        GL11.glPushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        if(te.getRotation() * 22.5F == 90.0F) {
            GL11.glTranslatef((float) x + 0.8F, (float)y + 1.3F, (float)z + 0.46F);
        } else if(te.getRotation() * 22.5F == 180.0F) {
            GL11.glTranslatef((float) x + 0.54F, (float)y + 1.3F, (float)z + 0.73F);
        } else if(te.getRotation() * 22.5F == 270.0F) {
            GL11.glTranslatef((float) x + 0.25F, (float)y + 1.3F, (float)z + 0.6F);
        } else {
            GL11.glTranslatef((float) x + 0.46F, (float)y + 1.3F, (float)z + 0.11F);
        }
        GL11.glRotatef(te.getRotation() * 22.5F + 270.0F, 0.0F, -1.0F, 0.0F);
        GL11.glRotatef(180F, 0.0F, 0.0F, -1.0F);
        GL11.glScalef(0.01F, 0.01F, 0.01F);
        // GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glPushMatrix();
        drawSplitString(getFontRenderer(), "Test"/*te.getTileData().getString("text")*/ /*String.valueOf(te.getRotation() * 22.5F)*/ , 0, 0, 100, Color.red.getRGB());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        // 270 = east
        // 1170 = south
        // 2070 = west
        // 2970 = north

    }

    public void drawSplitString(FontRenderer renderer, String str, int x, int y, int wrapWidth, int textColor) {
        str = trimStringNewline(str);
        renderSplitStringCentered(renderer, str, x, y, wrapWidth, textColor);
    }
    private void renderSplitStringCentered(FontRenderer renderer, String str, int x, int y, int wrapWidth, int textColor) {
        List<String> lines = renderer.listFormattedStringToWidth(str, 55);
        for (int i = 0; i < lines.size() && i < 4; i++) {
            String line = (String)lines.get(i);
            x = (wrapWidth + -renderer.getStringWidth(line)) / 2;
            renderer.drawString(line, x, y, textColor);
            y += renderer.FONT_HEIGHT;
        }
    }

    private String trimStringNewline(String text) {
        while (text != null && text.endsWith("\n"))
            text = text.substring(0, text.length() - 1);
        return text;
    }
}
