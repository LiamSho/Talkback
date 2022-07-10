package moe.alisaqaq.talkback;

import moe.alisaqaq.talkback.command.CommandContainer;
import moe.alisaqaq.talkback.command.CommandHandler;
import moe.alisaqaq.talkback.manager.ListHandler;
import moe.alisaqaq.talkback.manager.ReloadHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Objects;

public final class Talkback extends JavaPlugin {

    public static JavaPlugin instance;
    public static File dataDirectory;
    public static boolean hasPlaceholderAPI;

    private static final List<String> commands = List.of("talkback", "talkbacklist", "talkbackreload");

    @Override
    public void onEnable() {

        // Check if PlaceholderAPI is available or not
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().info("PlaceholderAPI is not available. Placeholder support will be disabled.");
            hasPlaceholderAPI = false;
        } else {
            getLogger().info("PlaceholderAPI found.");
            hasPlaceholderAPI = true;
        }

        // Set static
        instance = this;
        dataDirectory = this.getDataFolder();

        // Initialize data directory
        saveDefaultConfig();

        // Initialize commands
        CommandContainer.initialize(null);

        // Register command
        for (var cmd : commands) {
            if (Bukkit.getPluginCommand(cmd) == null) {
                getLogger().warning(String.format("Could not register command: %s", cmd));
                this.failedToLoad();
                return;
            }
        }

        Objects.requireNonNull(Bukkit.getPluginCommand("talkback")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("talkbacklist")).setExecutor(new ListHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("talkbackreload")).setExecutor(new ReloadHandler());
    }

    private void failedToLoad() {
        getLogger().warning("Talkback could not be loaded.");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
