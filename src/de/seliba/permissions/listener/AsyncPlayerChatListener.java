package de.seliba.permissions.listener;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.api.PermissionsAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class AsyncPlayerChatListener implements Listener {

    private PermissionsAPI api;

    public AsyncPlayerChatListener(PermissionsAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        String uuid = event.getPlayer().getUniqueId().toString();
        if(api.getPlayerPrefix(uuid) == null) {
            List<String> playerGroups = api.getPlayerGroups(uuid);
            for(int i = 0; i < playerGroups.size(); i++) {
                String group = playerGroups.get(i);
                if(api.getGroupPrefix(group) != null || !api.getGroupPrefix(group).equals("")) {
                    event.setFormat(ChatColor.translateAlternateColorCodes('&', api.getGroupPrefix(group)) + " §7" + event.getPlayer().getName() + " »§f " + event.getMessage());
                    return;
                }
            }
        } else {
            event.setFormat(ChatColor.translateAlternateColorCodes('&', api.getPlayerPrefix(uuid)) + " §7" + event.getPlayer().getName() + " »§f " + event.getMessage());
        }
    }

}
