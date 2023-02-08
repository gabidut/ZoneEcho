package fr.zoneecho.mod.client.betterInventory;

import fr.zoneecho.mod.Ref;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class BetterInventoryGui extends GuiContainer {
    public static final ResourceLocation INVENTORY_BACKGROUND = new ResourceLocation(Ref.MODID, "textures/inventory.png");

    public BetterInventoryGui(Container inventorySlotsIn)
    {
        super(inventorySlotsIn);
    }

    public void initGui()
    {
        super.initGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
        drawModalRectWithCustomSizedTexture(this.guiLeft, this.guiTop,0,0, 200, 100, 200, 100);
    }

}
