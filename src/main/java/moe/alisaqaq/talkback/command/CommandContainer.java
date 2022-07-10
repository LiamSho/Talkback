package moe.alisaqaq.talkback.command;

import moe.alisaqaq.talkback.Talkback;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandContainer {

    private static Map<String, CommandModel> commands;

    private static final List<String> targets = List.of("self", "all");

    public static void initialize(@Nullable Player player) {

        commands = new HashMap<>();

        var config = Talkback.instance.getConfig();

        for (var cmdName : config.getKeys(false)) {
            var section = config.getConfigurationSection(cmdName);

            assert section != null;
            var cmd = CommandModel.read(section);

            if (targets.contains(cmd.getTarget().toLowerCase())) {
                commands.put(cmd.getCommand(), cmd);

                if (player != null) {
                    player.sendMessage("[Talkback] Command entry registered: §2" + cmd.getCommand());
                }

                Talkback.instance.getLogger().info("Command entry registered: " + cmd.getCommand());

                continue;
            }

            if (player != null) {
                player.sendMessage("[Talkback] §cInvalid target for command " + cmd.getCommand() + ": " + cmd.getTarget());
            }
            Talkback.instance.getLogger().warning("Invalid target for command " + cmd.getCommand() + ": " + cmd.getTarget());
        }
    }

    public static @Nullable CommandModel getCommand(String key) {
        if (commands.containsKey(key)) {
            return commands.get(key);
        }

        return null;
    }

    public static List<String> getCommandDescribes() {
        var list = new ArrayList<String>();

        for (var cmd : commands.values()) {
            var perm = cmd.getPermission();
            if (perm.isEmpty()) {
                perm = "§o§nNONE";
            }
            list.add("[Talkback] §a" + cmd.getCommand() + "§r: §3" + perm);
        }

        return list;
    }
}
