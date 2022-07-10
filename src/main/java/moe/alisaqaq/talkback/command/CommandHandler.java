package moe.alisaqaq.talkback.command;

import me.clip.placeholderapi.PlaceholderAPI;
import moe.alisaqaq.talkback.Talkback;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class CommandHandler implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            return false;
        }

        var cmdKey = args[0];
        var cmd = CommandContainer.getCommand(cmdKey);

        if (cmd == null) {
            sender.sendMessage("Could not find command entry: " + cmdKey);
            return true;
        }

        var perm = cmd.getPermission();

        Player p = null;
        if (sender instanceof Player) {
            p = (Player) sender;

            if (!perm.isEmpty()) {
                if (!sender.hasPermission(perm)) {
                    sender.sendMessage("You do not have permission to use this command.");
                }
            }
        }

        var msg = cmd.getMessage();
        var target = cmd.getTarget();

        if (Talkback.hasPlaceholderAPI) {
            msg = PlaceholderAPI.setPlaceholders(p, msg);
        }

        switch (target.toLowerCase()) {
            case "all":
                Talkback.instance.getServer().broadcastMessage(msg);
                break;
            case "self":
                sender.sendMessage(msg);
                break;
            default:
                sender.sendMessage("Unknown target: " + target);
                break;
        }

        return true;
    }
}
