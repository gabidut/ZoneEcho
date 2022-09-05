package fr.zoneecho.mod.util.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.util.PlayableJobs;
import fr.zoneecho.mod.util.css.GuiAboutMe;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketGuiAboutMe implements IMessage {

    public PacketGuiAboutMe() {

    }



    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PacketGuiAboutMe, IMessage> {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketGuiAboutMe message, MessageContext ctx) {
            String job = null;
            if (Databases.getPlayerData(Minecraft.getMinecraft().player).isString("job")) {
                job = PlayableJobs.getJobFromName(Databases.getPlayerData(Minecraft.getMinecraft().player).getString("job")).getName();
            } else {
                job = "Aucun métier";
            }
            System.out.println(job);
            String finalJob = job;
            ACsGuiApi.asyncLoadThenShowGui("me", () -> new GuiAboutMe(finalJob));
            return null;
        }
    }
}
