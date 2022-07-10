package moe.alisaqaq.talkback.manager;

import moe.alisaqaq.talkback.command.CommandContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.ParametersAreNonnullByDefault;

public class ListHandler implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            return false;
        }

        var commands = CommandContainer.getCommandDescribes();

        sender.sendMessage("[Talkback] Command Entries:");

        for (var cmd : commands) {
            sender.sendMessage("    " + cmd);
        }

        return true;
    }
}
