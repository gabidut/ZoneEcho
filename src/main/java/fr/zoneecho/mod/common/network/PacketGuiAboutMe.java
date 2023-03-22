package fr.zoneecho.mod.common.network;

import fr.aym.acsguis.api.ACsGuiApi;
import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.client.css.GuiAboutMe;
import fr.zoneecho.mod.common.utils.Character;
import fr.zoneecho.mod.common.utils.DataAboutMe;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;


public class PacketGuiAboutMe implements IMessage {

    private String c;
    private String job;
    private String speciality;

    public PacketGuiAboutMe() {
    }

    public PacketGuiAboutMe(EntityPlayerMP player) {
        System.out.println(Arrays.toString(Databases.getPlayerData(player).getAllEntryNames()));
        this.c = Databases.getPlayerData(player).getString("perso_main");
        this.job = "InDev";
        this.speciality = "InDev";
    }



    @Override
    public void fromBytes(ByteBuf buf) {
        c = ByteBufUtils.readUTF8String(buf);
        job = ByteBufUtils.readUTF8String(buf);
        speciality = ByteBufUtils.readUTF8String(buf);

    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, c);
        ByteBufUtils.writeUTF8String(buf, job);
        ByteBufUtils.writeUTF8String(buf, speciality);
    }

    public static class Handler implements IMessageHandler<PacketGuiAboutMe, IMessage> {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketGuiAboutMe message, MessageContext ctx) {
            DataAboutMe data = new DataAboutMe(
                    Character.deserialize(message.c),
                    message.job,
                    message.speciality
            );
            ACsGuiApi.asyncLoadThenShowGui("me", () -> new GuiAboutMe(data));
            return null;
        }
    }
}
