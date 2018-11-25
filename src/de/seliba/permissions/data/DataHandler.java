package de.seliba.permissions.data;

/*
PermissionSystem 2.0 created by Seliba
*/

import java.util.Set;

public interface DataHandler {

    void insertPlayerData(String uuid, String prefix, Set<String> permissions, Set<String> groups);

    void insertGroupData(String groupName, String prefix, Set<String> permissions);

    boolean existsPlayer(String uuid);

    boolean existsGroup(String groupName);

    void addPlayerPermission(String uuid, String permission);

    void addGroupPermission(String groupName, String permission);

    void removePlayerPermission(String uuid, String permission);

    void removeGroupPermission(String groupName, String permission);

    boolean hasPlayerPermission(String uuid, String permission);

    boolean hasGroupPermission(String groupName, String permission);

    void setPlayerPrefix(String uuid, String prefix);

    void setGroupPrefix(String groupName, String prefix);

    String getPlayerPrefix(String uuid);

    String getGroupPrefix(String groupName);

    Set<String> getPlayerPermissions(String uuid);

    Set<String> getGroupPermissions(String groupName);

    Set<String> getGroups();

    Set<String> getPlayerGroups(String uuid);

}
