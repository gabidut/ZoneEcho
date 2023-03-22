package fr.zoneecho.mod.common.network;

import fr.nathanael2611.simpledatabasemanager.core.Databases;
import fr.zoneecho.mod.common.utils.Character;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketConfirmPerso implements IMessage {


    String name, surname, date, perso, sex;

    public PacketConfirmPerso() {
    }

    public PacketConfirmPerso(String name, String surname, String date, String perso, String sex) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.perso = perso;
        this.sex = sex;
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
        surname = ByteBufUtils.readUTF8String(buf);
        date = ByteBufUtils.readUTF8String(buf);
        perso = ByteBufUtils.readUTF8String(buf);
        sex = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        ByteBufUtils.writeUTF8String(buf, surname);
        ByteBufUtils.writeUTF8String(buf, date);
        ByteBufUtils.writeUTF8String(buf, perso);
        ByteBufUtils.writeUTF8String(buf, sex);
    }


    public static class Handler implements IMessageHandler<PacketConfirmPerso, IMessage> {
        @Override
        public IMessage onMessage(PacketConfirmPerso message, MessageContext ctx) {

            if(ctx.side.isServer()) {
                ctx.getServerHandler().player.sendMessage(new TextComponentString("Personnage enregistré : " +  message.sex + " (" + Character.Sex.valueOf(message.sex) +") " + message.name + " " + message.surname + " né le " + message.date + " sur le perso : " + message.perso + "."));
                Databases.getPlayerData(ctx.getServerHandler().player).setString("perso_" + message.perso, new Character(message.name, message.surname, message.date, Character.Sex.valueOf(message.sex)).serialize());
            }
            return null;
        }
    }
}
