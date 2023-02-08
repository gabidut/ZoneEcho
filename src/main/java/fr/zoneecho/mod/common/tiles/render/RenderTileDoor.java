package fr.zoneecho.mod.common.tiles.render;

import fr.dynamx.client.renders.TESRDynamXBlock;
import fr.dynamx.common.DynamXContext;
import fr.zoneecho.mod.common.tiles.TileDoor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;

public class RenderTileDoor extends TESRDynamXBlock<TileDoor> {

    @Override
    public void render(TileDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        GL11.glPushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        if(te.getRotation() * 22.5F == 90.0F) {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + (float) te.getTileData().getInteger("step2") / -100 + 0.5F);
        } else if(te.getRotation() * 22.5F == 180.0F) {
            GL11.glTranslatef((float)x + (float) te.getTileData().getInteger("step2") / -100 + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        } else if(te.getRotation() * 22.5F == 270.0F) {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + (float) te.getTileData().getInteger("step2") / 100 + 0.5F);
        } else {
            GL11.glTranslatef((float)x + (float) te.getTileData().getInteger("step2") / 100 + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        }
        GL11.glRotatef(te.getRotation() * 22.5F, 0F, 1F, 0F);
        GL11.glPushMatrix();
        DynamXContext.getObjModelRegistry().getModel(te.getBlockObjectInfo().getModel()).renderGroups("partl", (byte) te.getBlockMetadata());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();

        GL11.glPushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        if(te.getRotation() * 22.5F == 90.0F) {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + (float) te.getTileData().getInteger("step") / -100 + 0.5F);
        } else if(te.getRotation() * 22.5F == 180.0F) {
            GL11.glTranslatef((float)x + (float) te.getTileData().getInteger("step") / -100 + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        } else if(te.getRotation() * 22.5F == 270.0F) {
            GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + (float) te.getTileData().getInteger("step") / 100 + 0.5F);
        } else {
            GL11.glTranslatef((float)x + (float) te.getTileData().getInteger("step") / 100 + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        }

        GL11.glRotatef(te.getRotation() * 22.5F, 0F, 1F, 0F);
        GL11.glPushMatrix();
        DynamXContext.getObjModelRegistry().getModel(te.getBlockObjectInfo().getModel()).renderGroups("partr", (byte) te.getBlockMetadata());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();


        GL11.glPushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GL11.glRotatef(te.getRotation() * 22.5F, 0F, 1F, 0F);
        GL11.glPushMatrix();
        DynamXContext.getObjModelRegistry().getModel(te.getBlockObjectInfo().getModel()).renderGroups("tour", (byte) te.getBlockMetadata());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();

    }
}
