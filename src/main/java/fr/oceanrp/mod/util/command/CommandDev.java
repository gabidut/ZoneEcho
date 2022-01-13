package fr.oceanrp.mod.util.command;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import fr.oceanrp.mod.util.Ref;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandDev extends CommandBase {


    @Override
    public String getName() {
        return "testcommand";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "AdminCommand";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        URL url = null;
        try {
            url = new URL("https://" + Ref.API_IP +  "/user/" + sender.getCommandSenderEntity().getUniqueID().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("user-agent", "Mozilla/5.0 (Compatible; Postman) ");
            con.connect();
            System.out.println(con.getResponseMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}