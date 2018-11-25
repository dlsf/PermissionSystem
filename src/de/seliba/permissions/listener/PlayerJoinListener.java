package de.seliba.permissions.listener;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.PermissionsSystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;

public class PlayerJoinListener implements Listener {

    private PermissionsSystem plugin;

    public PlayerJoinListener(PermissionsSystem permissionsSystem) {
        this.plugin = permissionsSystem;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!plugin.getDataHandler().existsPlayer(event.getPlayer().getUniqueId().toString())) {
            Set<String> groups = new HashSet<>();
            groups.add("default");
            plugin.getDataHandler().insertPlayerData(event.getPlayer().getUniqueId().toString(), "", new HashSet<>(), groups);
        }
        //TODO: Load permissions
    }

}
