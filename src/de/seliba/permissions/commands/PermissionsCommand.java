package de.seliba.permissions.commands;

/*
PermissionSystem 2.0 created by Seliba
*/

import de.seliba.permissions.api.PermissionsAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionsCommand implements CommandExecutor {

    private PermissionsAPI api;

    public PermissionsCommand(PermissionsAPI api) {
        this.api = api;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("*")) {
            sender.sendMessage("§cDazu hast du keine Rechte!");
            return true;
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("deletegroup")) {
                String group = args[1];
                if(!api.existsGroup(group)) {
                    System.out.println("§cDiese Gruppe existiert nicht!");
                    return true;
                }
                api.removeGroupData(group, true);
                sender.sendMessage("§aDu hast erfolgreich die Gruppe gelöscht!");
                return true;
            }
            sendHelpMessage(sender);
            return true;
        }
        if(args.length != 3) {
            sendHelpMessage(sender);
            return true;
        }
        if(args[0].equalsIgnoreCase("addgroup")) {
            Player target = Bukkit.getPlayer(args[1]);
            String group = args[2];
            if(target == null) {
                System.out.println("§cDieser Spieler ist nicht online!");
                return true;
            }
            if(!api.existsGroup(group)) {
                System.out.println("§cDiese Gruppe existiert nicht!");
                return true;
            }
            api.addPlayerGroup(target.getUniqueId().toString(), group);
            sender.sendMessage("§aDu hast erfolgreich §6" + target.getName() + " §adie Gruppe gegeben!");
            return true;
        } else if(args[0].equalsIgnoreCase("removegroup")) {
            Player target = Bukkit.getPlayer(args[1]);
            String group = args[2];
            if(target == null) {
                System.out.println("§cDieser Spieler ist nicht online!");
                return true;
            }
            if(!api.getPlayerGroups(target.getUniqueId().toString()).contains(group)) {
                System.out.println("§cDer Spieler §6" + target.getName() + " §cbesitzt diese Gruppe nicht!");
                return true;
            }
            api.removePlayerGroup(target.getUniqueId().toString(), group);
            sender.sendMessage("§aDu hast erfolgreich §6" + target.getName() + " §adie Gruppe entzogen!");
            return true;
        } else if(args[0].equalsIgnoreCase("setprefix")) {
            Player target = Bukkit.getPlayer(args[1]);
            String group = args[1];
            String prefix = args[2];
            if(target == null && !api.existsGroup(group)) {
                System.out.println("§6Benutzung§c: §6/§cpermissions setprefix <§6Player§c / §6Group§c> <§6Prefix§c>");
                return true;
            }
            if(target == null) {
                api.setGroupPrefix(group, prefix);
                sender.sendMessage("§aDu hast erfolgreich der Gruppe den Prefix gegeben!");
            } else {
                api.setPlayerPrefix(target.getUniqueId().toString(), prefix);
                sender.sendMessage("§aDu hast erfolgreich §6" + target.getName() + " §aden Prefix gegeben!");
            }
            return true;
        } else if(args[0].equalsIgnoreCase("addpermission")) {
            Player target = Bukkit.getPlayer(args[1]);
            String group = args[1];
            String permission = args[2];
            if(target == null && !api.existsGroup(group)) {
                System.out.println("§6Benutzung§c: §6/§cpermissions addpermission <§6Player§c / §6Group§c> <§6Permission§c>");
                return true;
            }
            if(target == null) {
                api.addGroupPermission(group, permission);
                sender.sendMessage("§aDu hast erfolgreich der Gruppe die Permission gegeben!");
            } else {
                api.addPlayerPermission(target.getUniqueId().toString(), permission);
                sender.sendMessage("§aDu hast erfolgreich §6" + target.getName() + " §adie Permission gegeben!");
            }
            return true;
        } else if(args[0].equalsIgnoreCase("removepermission")) {
            Player target = Bukkit.getPlayer(args[1]);
            String group = args[1];
            String permission = args[2];
            if(target == null && !api.existsGroup(group)) {
                System.out.println("§6Benutzung§c: §6/§cpermissions removepermission <§6Player§c / §6Group§c> <§6Permission§c>");
                return true;
            }
            if(target == null) {
                api.removeGroupPermission(group, permission);
                sender.sendMessage("§aDu hast erfolgreich der Gruppe die Permission entzogen!");
            } else {
                api.removePlayerPermission(target.getUniqueId().toString(), permission);
                sender.sendMessage("§aDu hast erfolgreich §6" + target.getName() + " §adie Permission entzogen!");
            }
            return true;
        } else if(args[0].equalsIgnoreCase("creategroup")) {
            String group = args[1];
            String prefix = args[2];
            if(api.existsGroup(group)) {
                System.out.println("§cDiese Gruppe existiert bereits!");
                return true;
            }
            api.createGroup(group, prefix);
            sender.sendMessage("§aDu hast erfolgreich die Gruppe §6" + group + " §aerstellt!");
            return true;
        }
        sendHelpMessage(sender);
        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage("§7-----------------------------------------------------");
        sender.sendMessage("§6Benutzung:");
        sender.sendMessage("§6/§cpermissions addgroup <§6Player§c> <§6Group§c>");
        sender.sendMessage("§6/§cpermissions removegroup <§6Player§c> <§6Group§c>");
        sender.sendMessage("§6/§cpermissions setprefix <§6Player§c / §6Group§c> <§6Prefix§c>");
        sender.sendMessage("§6/§cpermissions addpermission <§6Player§c / §6Group§c> <§6Permission§c>");
        sender.sendMessage("§6/§cpermissions removepermission <§6Player§c / §6Group§c> <§6Permission§c>");
        sender.sendMessage("§6/§cpermissions creategroup <§6Name§c> <§6Prefix§c>");
        sender.sendMessage("§6/§cpermissions deletegroup <§6Name§c>");
        sender.sendMessage("§7-----------------------------------------------------");
    }
}
