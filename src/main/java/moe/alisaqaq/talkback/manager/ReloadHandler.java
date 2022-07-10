package moe.alisaqaq.talkback.manager;

import moe.alisaqaq.talkback.Talkback;
import moe.alisaqaq.talkback.command.CommandContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class ReloadHandler implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            return false;
        }

        Player p = null;

        if (sender instanceof Player) {
            p = (Player) sender;
        }

        Talkback.instance.reloadConfig();
        CommandContainer.initialize(p);

        return true;
    }
}
