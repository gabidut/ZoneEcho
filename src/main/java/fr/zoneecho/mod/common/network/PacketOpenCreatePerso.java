package fr.zoneecho.mod.common.network;

import fr.zoneecho.mod.common.proxy.ClientProxy;
import fr.zoneecho.mod.common.utils.PlayableJobs;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketOpenCreatePerso implements IMessage {

    private String perso;

    public PacketOpenCreatePerso() {
    }

    public PacketOpenCreatePerso(PlayableJobs.listPerso perso) {
        this.perso = perso.name();
        System.out.println("PacketOpenCreatePerso#Builder");
        System.out.println(perso.getName());
    }




    @Override
    public void fromBytes(ByteBuf buf) {
        System.out.println("PacketOpenCreatePerso#fromBytes");
        System.out.println(this.perso);
        perso = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        System.out.println("PacketOpenCreatePerso#toBytes");
        System.out.println(this.perso);
        ByteBufUtils.writeUTF8String(buf, this.perso);
    }


    public static class Handler implements IMessageHandler<PacketOpenCreatePerso, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketOpenCreatePerso message, MessageContext ctx) {
            ClientProxy.guiToDisplay = 1;
            System.out.println("PacketOpenCreatePerso");
            System.out.println(message.perso);
            ClientProxy.createPersoLastCharName = message.perso;

            return null;
        }
    }
}
