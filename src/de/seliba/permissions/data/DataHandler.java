package de.seliba.permissions.data;

/*
PermissionSystem 2.0 created by Seliba
*/

import java.util.List;
import java.util.Set;

public interface DataHandler {

    void insertPlayerData(String uuid, String prefix, List<String> permissions, List<String> groups);

    void insertGroupData(String groupName, String prefix, List<String> permissions);

    void removeGroupData(String groupName, boolean yesIKnowWhatIAmDoing);

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

    List<String> getPlayerPermissions(String uuid);

    List<String> getGroupPermissions(String groupName);

    List<String> getGroups();

    List<String> getPlayerGroups(String uuid);

}
