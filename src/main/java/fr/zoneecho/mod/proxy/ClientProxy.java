package fr.zoneecho.mod.proxy;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.zoneecho.mod.objects.blocks.BlockCardReader;
import fr.zoneecho.mod.objects.tiles.TETVSign;
import fr.zoneecho.mod.objects.tiles.TileDoor;
import fr.zoneecho.mod.objects.tiles.TileDoorO;
import fr.zoneecho.mod.objects.tiles.render.RenderTileDoor;
import fr.zoneecho.mod.objects.tiles.render.RenderTileDoorO;
import fr.zoneecho.mod.objects.tiles.render.TETVSignRender;
import fr.zoneecho.mod.util.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

public class ClientProxy extends CommonProxy
{

    public static Boolean isBleeding = false;

    @Override
    public void registerItemRenderer(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        super.registerVariantRenderer(item, meta, filename, id);
    }

    @Override
    public void registerEntityRenderers()
    {
        super.registerEntityRenderers();	
    }

    @Override
    public void preInit()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TETVSign.class, new TETVSignRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileDoor.class, new RenderTileDoor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileDoorO.class, new RenderTileDoorO());
        try {
            Display.setIcon(new ByteBuffer[] { inputToByteBuffer(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Ref.MODID, "textures/logo.png")).getInputStream()) });
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        super.preInit();
        OBJLoader.INSTANCE.addDomain(Ref.MODID);
    }

    public static ByteBuffer inputToByteBuffer(InputStream paramInputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(paramInputStream);
        int[] arrayOfInt = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 * arrayOfInt.length);
        for (int j : arrayOfInt)
            byteBuffer.putInt(j << 8 | j >> 24 & 0xFF);
        byteBuffer.flip();
        return byteBuffer;
    }
    @SubscribeEvent
    public void usernameRender(RenderLivingEvent.Specials.Pre e){
        if(!(Minecraft.getMinecraft().player.isCreative() || Minecraft.getMinecraft().player.isSpectator())){
            e.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void showTextOnTopOfCardReader(RenderHandEvent e) {
        // check if player is in a world
        // get all blocks arround the player and extract the blockpos
        World world = Minecraft.getMinecraft().world;
        EntityPlayer player = Minecraft.getMinecraft().player;

        for (int i = -1; i < 3; i++) {
            for (int j = -1; j < 3; j++) {
                for (int k = -1; k < 3; k++) {
                    BlockPos blockPos = new BlockPos(player.posX + i, player.posY + j, player.posZ + k);

                    if (world.getBlockState(blockPos).getBlock() instanceof BlockCardReader) {
                        NBTTagCompound tileDoor = Objects.requireNonNull(world.getTileEntity(blockPos)).getTileData();
                        GL11.glPushMatrix();
                        GL11.glPopMatrix();
                    }
                }
            }
        }
        // for each blockpos, get the tileentity and render the text

    }

    @SubscribeEvent
    public void GuieventHandler(GuiOpenEvent e) {
        if (e.getGui() instanceof GuiMainMenu) {
                ACsGuiApi.asyncLoadThenShowGui("mainmenu", fr.zoneecho.mod.util.css.GuiMainMenu::new);
        }
    }
}
