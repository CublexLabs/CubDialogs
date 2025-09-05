package io.github.devbd1.CubDialogs;

import io.github.devbd1.CubDialogs.commands.CmdRegistrar;
import io.github.devbd1.CubDialogs.listeners.ExpConfigEventListener;
import io.github.devbd1.CubDialogs.dialog.DialogConfigManager;
import io.github.devbd1.CubDialogs.serverLinks.ServerLinksManager;
import io.github.devbd1.CubDialogs.utilities.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    
    private static Main instance;

    private ServerLinksManager serverLinksManager;
    
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        
        // Dependency check: ensure CublexCore is present and enabled
//        if (!isCublexCorePresent()) {
//            getLogger().severe("CublexCore dependency is missing or not enabled. Disabling CubDialogs.");
//            getServer().getPluginManager().disablePlugin(this);
//            return;
//        }

        try {
            // Initialize configuration managers
            ConfigManager.init(this);
            DialogConfigManager.init(this);
            // --- commands
            CmdRegistrar.register(this);

            // Register event listeners
            getServer().getPluginManager().registerEvents(new ExpConfigEventListener(), this);

            // Initialize ServerLinksManager (this sets the static instance)
            serverLinksManager = new ServerLinksManager(this);
            serverLinksManager.setupServerLinks();
        
            getLogger().info("Plugin enabled successfully");
        } catch (Exception e) {
            getLogger().severe("Failed to enable plugin: " + e.getMessage());
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        // Plugin shutdown logic
    }

    private boolean isCublexCorePresent() {
        var pm = getServer().getPluginManager();
        var plugin = pm.getPlugin("Cub");
        return plugin != null && plugin.isEnabled();
    }
}
