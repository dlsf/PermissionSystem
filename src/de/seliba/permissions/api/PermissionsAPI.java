package de.seliba.permissions.api;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.PermissionsSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermissionsAPI {

    private PermissionsSystem plugin;

    public PermissionsAPI(PermissionsSystem plugin) {
        this.plugin = plugin;
    }

    public boolean existsPlayer(String uuid) {
        return plugin.getDataHandler().existsPlayer(uuid);
    }

    public boolean existsGroup(String groupName) {
        return plugin.getDataHandler().existsGroup(groupName);
    }

    public void addPlayerGroup(String uuid, String groupName) {
        if(!getPlayerGroups(uuid).contains(groupName)) {
            plugin.getDataHandler().addPlayerGroup(uuid, groupName);
            reload();
        }
    }

    public void removePlayerGroup(String uuid, String groupName) {
        if(getPlayerGroups(uuid).contains(groupName)) {
            plugin.getDataHandler().removePlayerGroup(uuid, groupName);
            reload();
        }
    }

    public void addPlayerPermission(String uuid, String permission) {
        plugin.getDataHandler().addPlayerPermission(uuid, permission);
        addPermission(uuid, permission);
    }

    public void addGroupPermission(String groupName, String permission) {
        plugin.getDataHandler().addGroupPermission(groupName, permission);
        reload();
    }

    public void removePlayerPermission(String uuid, String permission) {
        plugin.getDataHandler().removePlayerPermission(uuid, permission);
        reload();
    }

    public void removeGroupPermission(String groupName, String permission) {
        plugin.getDataHandler().removeGroupPermission(groupName, permission);
        reload();
    }

    public boolean hasPlayerPermission(String uuid, String permission) {
        return plugin.getDataHandler().hasPlayerPermission(uuid, permission);
    }

    public boolean hasGroupPermission(String groupName, String permission) {
        return plugin.getDataHandler().hasGroupPermission(groupName, permission);
    }

    public void setPlayerPrefix(String uuid, String prefix) {
        //TODO
        plugin.getDataHandler().setPlayerPrefix(uuid, prefix);
    }

    public void setGroupPrefix(String groupName, String prefix) {
        //TODO
        plugin.getDataHandler().setGroupPrefix(groupName, prefix);
    }

    public String getPlayerPrefix(String uuid) {
        return plugin.getDataHandler().getPlayerPrefix(uuid);
    }

    public String getGroupPrefix(String groupName) {
        return plugin.getDataHandler().getGroupPrefix(groupName);
    }

    public List<String> getPlayerPermissions(String uuid) {
        return plugin.getDataHandler().getPlayerPermissions(uuid);
    }

    public List<String> getGroupPermissions(String groupName) {
        return plugin.getDataHandler().getGroupPermissions(groupName);
    }

    public List<String> getGroups() {
        return plugin.getDataHandler().getGroups();
    }

    public List<String> getPlayerGroups(String uuid) {
        return plugin.getDataHandler().getPlayerGroups(uuid);
    }

    public void createDefaultGroups() {
        if(!existsGroup("default")) {
            plugin.getDataHandler().insertGroupData("default", "", new ArrayList<>());
            plugin.getDataHandler().insertGroupData("admin", "", new ArrayList<>());
        }
    }

    public void reload() {
        //TODO: Remove debug messages
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            System.out.println("Player gefunden:" + player.getName());
            plugin.getDataHandler().getPlayerPermissions(player.getUniqueId().toString()).forEach(permission -> {
                System.out.println("Permission gefunden:" + permission);
                addPermission(player.getUniqueId().toString(), permission);
            });
            plugin.getDataHandler().getPlayerGroups(player.getUniqueId().toString()).forEach(group ->{
                System.out.println("Gruppe gefunden:" + group);
                plugin.getDataHandler().getGroupPermissions(group).forEach(permission -> {
                    System.out.println("Grouppermission gefunden:" + permission);
                    addPermission(player.getUniqueId().toString(), permission);
                });
            });
        });
    }

    public void removeGroupData(String groupName, boolean yesIKnowWhatIAmDoing) {
        plugin.getDataHandler().removeGroupData(groupName, yesIKnowWhatIAmDoing);
    }

    public void addPermission(String uuid, String permission) {
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        PermissionAttachment attachment = player.addAttachment(plugin);
        attachment.setPermission(permission, true);
    }

}
