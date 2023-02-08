package fr.zoneecho.mod.server.command;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.GameType;

public class CommandMakeMeBug extends CommandBase {


    @Override
    public String getName() {
        return "makemebug";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "makemebug";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        AbstractClientPlayer player = (AbstractClientPlayer) sender.getCommandSenderEntity();
        assert player != null;
        player.setNoGravity(true);
        player.setAir(1);
        player.setGameType(GameType.NOT_SET);
        player.setInvisible(true);
        player.setSneaking(true);
        player.setSprinting(true);
        player.setVelocity(5, 0, 0);
        player.setPrimaryHand(EnumHandSide.LEFT);
        player.setAbsorptionAmount(10000.0F);
    }
}