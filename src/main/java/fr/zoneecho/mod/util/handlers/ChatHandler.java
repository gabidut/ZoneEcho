package fr.zoneecho.mod.util.handlers;

import fr.zoneecho.mod.util.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Ref.MODID)
public class ChatHandler {
    @SubscribeEvent
    public static void OnChat(ServerChatEvent e) {
//
//        if(e.getMessage().startsWith("$")) {
//            String prefix = e.getMessage().split(" ")[0];
//            ChatMode parsedChatMode = ChatMode.getChatMode(prefix);
//
//            if(Objects.isNull(parsedChatMode)) {
//                e.setCanceled(true);
//                e.getPlayer().sendMessage(new TextComponentString(TextFormatting.RED + "Prefix de message non reconnu."));
//            } else {
//                if(parsedChatMode.equals(ChatMode.ADMIN) && isPlayerOpped(e.getPlayer())) {
//
//                } else {
//                    e.setCanceled(true);
//                    ZoneEcho.network.sendTo(new PacketGoBackToSleep("I think u'r trying to send messages ad admin i'm wrong ? gabidut76#6123 :)"), e.getPlayer());
//                }
//            }
//
//
//        } else {
//            e.setCanceled(true);
//            e.getPlayer().sendMessage(new TextComponentString(TextFormatting.RED + "Méthode non autorisée. (voir gabidut76) "));
//        }


        if(Objects.equals(e.getMessage(), "gabidut le best")) {
            e.setCanceled(true);
            e.getPlayer().sendMessage(new TextComponentString(TextFormatting.GREEN + "J'approuve !"));
        }
    }

    public static boolean isPlayerOpped( EntityPlayer player )
    {
        return player.canUseCommand( 4, "op" );
    }

}