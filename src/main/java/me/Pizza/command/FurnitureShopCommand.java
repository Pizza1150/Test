package me.Pizza.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import me.Pizza.FurnitureShop;

public class FurnitureShopCommand implements TabExecutor {

    private final FurnitureShop plugin;

    public FurnitureShopCommand(FurnitureShop plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) return Arrays.asList("editzone", "reload");

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("editzone")) return Arrays.asList(
                "enable",
                "disable",
                "region", 
                "viewer-location", 
                "furniture-location", 
                "exit-location");
        }
        
        return Arrays.asList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /" + label + " <editzone|reload>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> {
                plugin.reloadConfig();
                sender.sendMessage("§aFurnitureShop got reloaded!");
                return true;
            }

            case "editzone" -> {
                switch (args[1].toLowerCase()) {
                    case "enable" -> {
                        plugin.getZoneConfig().setZoneStatus(sender, true);
                        return true;
                    }
                    
                    case "disable" -> {
                        plugin.getZoneConfig().setZoneStatus(sender, false);
                        return true;
                    }

                    case "region" -> {
                        if (!(sender instanceof Player player)) {
                            sender.sendMessage("§cCan not use this command via console!");
                            return true;
                        }

                        if (args.length < 3) {
                            sender.sendMessage("§cSomething wrong");
                            return true;
                        }

                        plugin.getZoneConfig().setRegion(player, args[2]);
                        return true;
                    }

                    case "viewer-location" -> {
                        if (!(sender instanceof Player player)) {
                            sender.sendMessage("§cCan not use this command via console!");
                            return true;
                        }

                        plugin.getZoneConfig().setViewerLocation(player, player.getLocation());
                        return true;
                    }

                    case "furniture-location" -> {
                        if (!(sender instanceof Player player)) {
                            sender.sendMessage("§cCan not use this command via console!");
                            return true;
                        }

                        plugin.getZoneConfig().setFurnitureLocation(player, player.getLocation());
                        return true;
                    }

                    case "exit-location" -> {
                        if (!(sender instanceof Player player)) {
                            sender.sendMessage("§cCan not use this command via console!");
                            return true;
                        }

                        plugin.getZoneConfig().setExitLocation(player, player.getLocation());
                        return true;
                    }
                }
            }
        }

        return true;
    }
    
}
