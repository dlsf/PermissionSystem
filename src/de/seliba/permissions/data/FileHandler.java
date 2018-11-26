package de.seliba.permissions.data;

/*
PermissionSystem 2.0 created by Seliba
*/

import java.util.List;
import java.util.Set;

public class FileHandler implements DataHandler {

    private Config data;

    public FileHandler(Config dataFile) {
        this.data = dataFile;
    }

    @Override
    public boolean existsGroup(String groupName) {
        return data.getStringList("groups.names").contains(groupName);
    }

    @Override
    public boolean existsPlayer(String uuid) {
        return data.getStringList("player." + uuid + ".permissions") == null;
    }

    @Override
    public void insertGroupData(String groupName, String prefix, List<String> permissions) {
        List<String> groupsList = getGroups();
        groupsList.add(groupName);
        data.set("groups.names", groupsList);
        data.set("groups." + groupName + ".prefix", prefix);
        data.set("groups." + groupName + ".permissions", permissions);
        data.save();
    }

    @Override
    public void insertPlayerData(String uuid, String prefix, List<String> permissions, List<String> groups) {
        data.set("player." + uuid + ".prefix", prefix);
        data.set("player." + uuid + ".permissions", permissions);
        data.set("player." + uuid + ".groups", groups);
        data.save();
    }

    @Override
    public void removeGroupData(String groupName, boolean yesIKnowWhatIAmDoing) {
        //TODO
    }

    @Override
    public void addGroupPermission(String groupName, String permission) {
        List<String> groupPermissions = getGroupPermissions(groupName);
        if(!groupPermissions.contains(permission)) groupPermissions.add(permission);
        data.set("groups." + groupName + ".permissions", groupPermissions);
        data.save();
    }

    @Override
    public void addPlayerPermission(String uuid, String permission) {
        List<String> playerPermissions = getPlayerPermissions(uuid);
        if(!playerPermissions.contains(permission)) playerPermissions.add(permission);
        data.set("player." + uuid + ".permissions", playerPermissions);
        data.save();
    }

    @Override
    public void removeGroupPermission(String groupName, String permission) {
        List<String> groupPermissions = getGroupPermissions(groupName);
        if(groupPermissions.contains(permission)) groupPermissions.remove(permission);
        data.set("groups." + groupName + ".permissions", groupPermissions);
        data.save();
    }

    @Override
    public void removePlayerPermission(String uuid, String permission) {
        List<String> playerPermissions = getPlayerPermissions(uuid);
        if(playerPermissions.contains(permission)) playerPermissions.remove(permission);
        data.set("player." + uuid + ".permissions", playerPermissions);
        data.save();
    }

    @Override
    public boolean hasGroupPermission(String groupName, String permission) {
        return data.getStringList("groups." + groupName + ".permissions").contains(permission);
    }

    @Override
    public boolean hasPlayerPermission(String uuid, String permission) {
        return data.getStringList("player." + uuid + ".permissions").contains(permission);
    }

    @Override
    public List<String> getGroupPermissions(String groupName) {
        return data.getStringList("player." + groupName + ".permissions");
    }

    @Override
    public List<String> getGroups() {
        return data.getStringList("groups.names");
    }

    @Override
    public List<String> getPlayerGroups(String uuid) {
        return data.getStringList("player." + uuid + ".groups");
    }

    @Override
    public List<String> getPlayerPermissions(String uuid) {
        return data.getStringList("player." + uuid + ".permissions");
    }

    @Override
    public String getGroupPrefix(String groupName) {
        return data.getString("groups." + groupName + ".prefix");
    }

    @Override
    public String getPlayerPrefix(String uuid) {
        return data.getString("player." + uuid + ".prefix");
    }

    @Override
    public void setGroupPrefix(String groupName, String prefix) {
        data.set("groups." + groupName + ".prefix", prefix);
        data.save();
    }

    @Override
    public void setPlayerPrefix(String uuid, String prefix) {
        data.set("player." + uuid + ".prefix", prefix);
        data.save();
    }
}
