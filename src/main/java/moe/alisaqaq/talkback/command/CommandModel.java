package moe.alisaqaq.talkback.command;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

public class CommandModel {

    private final String Command;
    private final String Permission;
    private final String Target;
    private final String Message;

    public CommandModel(String command, String permission, String target, String message) {
        Command = command;
        Permission = permission;
        Target = target;
        Message = message;
    }

    public String getCommand() {
        return Command;
    }

    public String getPermission() {
        return Permission;
    }

    public String getTarget() {
        return Target;
    }

    public String getMessage() {
        return Message;
    }

    @NotNull
    public static CommandModel read(@NotNull ConfigurationSection section) {
        var command = section.getString("command");
        var permission = section.getString("permission");
        var target = section.getString("target");
        var message = section.getString("message");

        return new CommandModel(command, permission, target, message);
    }
}
