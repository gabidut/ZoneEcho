package fr.oceanrp.mod.util.command;

import fr.oceanrp.mod.Main;
import fr.oceanrp.mod.util.packets.ApiRequest;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandDev extends CommandBase {


    @Override
    public String getName() {
        return "testcommand";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "testcommand";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Main.network.sendToServer(new ApiRequest("test ApiRequest", null, null));

    }
}