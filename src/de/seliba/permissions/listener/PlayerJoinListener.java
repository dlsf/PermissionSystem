package de.seliba.permissions.listener;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.PermissionsSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerJoinListener implements Listener {

    private PermissionsSystem plugin;

    public PlayerJoinListener(PermissionsSystem permissionsSystem) {
        this.plugin = permissionsSystem;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //TODO: Remove Debug Code
        if(!plugin.getDataHandler().existsPlayer(event.getPlayer().getUniqueId().toString())) {
            System.out.println("Join Trigger 1");
            List<String> groups = new ArrayList<>();
            groups.add("default");
            plugin.getDataHandler().insertPlayerData(event.getPlayer().getUniqueId().toString(), "", new ArrayList<>(), groups);
        }
        System.out.println("Join Trigger 2");
        plugin.getPermissionsAPI().reload();
        plugin.getPermissionsAPI().addGroupPermission("admin", "worldedit.selection.pos");
        //plugin.getPermissionsAPI().addPlayerGroup(event.getPlayer().getUniqueId().toString(), "admin");
        //plugin.getPermissionsAPI().addPlayerPermission(event.getPlayer().getUniqueId().toString(), "system.menu");
    }

}
