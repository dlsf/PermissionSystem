package de.seliba.permissions.listener;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.PermissionsSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private PermissionsSystem plugin;

    public PlayerQuitListener(PermissionsSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getPermissionsAPI().clearPermissions(event.getPlayer().getUniqueId().toString());
    }

}
