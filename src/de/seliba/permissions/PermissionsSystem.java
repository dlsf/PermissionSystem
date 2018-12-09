package de.seliba.permissions;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.api.PermissionsAPI;
import de.seliba.permissions.commands.PermissionsCommand;
import de.seliba.permissions.data.Config;
import de.seliba.permissions.data.DataHandler;
import de.seliba.permissions.data.FileHandler;
import de.seliba.permissions.listener.AsyncPlayerChatListener;
import de.seliba.permissions.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionsSystem extends JavaPlugin {

    private DataHandler dataHandler;
    private Config cfg;
    private PermissionsSystem instance;
    private PermissionsAPI permissionsAPI;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("[PermissionsPlugin] Gestartet!");
        cfg = new Config("permissions.yml", this);
        dataHandler = new FileHandler(cfg);
        permissionsAPI = new PermissionsAPI(this);
        permissionsAPI.createDefaultGroups();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(permissionsAPI), this);
        getCommand("permissions").setExecutor(new PermissionsCommand(permissionsAPI));
    }

    @Override
    public void onDisable() {
        System.out.println("[PermissionsPlugin] Gestoppt!");
        instance = null;
    }

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    public Config getCfg() {
        return cfg;
    }

    public PermissionsAPI getPermissionsAPI() {
        return permissionsAPI;
    }

}
