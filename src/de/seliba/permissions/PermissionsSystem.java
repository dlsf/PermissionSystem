package de.seliba.permissions;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.api.PermissionsAPI;
import de.seliba.permissions.data.Config;
import de.seliba.permissions.data.DataHandler;
import de.seliba.permissions.data.FileHandler;
import de.seliba.permissions.listener.PlayerJoinListener;
import de.seliba.permissions.listener.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PermissionsSystem extends JavaPlugin {

    private DataHandler dataHandler;
    private Config cfg;
    private HashMap<String,PermissionAttachment> attachments;
    private PermissionsSystem instance;
    private PermissionsAPI permissionsAPI;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("[PermissionsPlugin] Gestartet!");
        cfg = new Config("permissions.yml", this);
        dataHandler = new FileHandler(cfg);
        permissionsAPI = new PermissionsAPI(this);
        attachments = new HashMap<>();
        permissionsAPI.createDefaultGroups();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        //Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
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

    public HashMap<String, PermissionAttachment> getAttachments() {
        return attachments;
    }

    public PermissionsAPI getPermissionsAPI() {
        return permissionsAPI;
    }

}
