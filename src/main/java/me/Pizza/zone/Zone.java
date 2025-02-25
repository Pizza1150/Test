package me.Pizza.zone;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.Pizza.FurnitureShop;

public class Zone {
    
    private final Player player;

    public Zone(Player player) {
        this.player = player;
        enter();
    }

    public void enter() {
        Location location = FurnitureShop.getPlugin().getZoneConfig().getViewerLocation();
        player.teleport(location);
        player.sendMessage("Welcome to furniture shop!");
        preview();
    }

    public void exit() {
        Location location = FurnitureShop.getPlugin().getZoneConfig().getExitLocation();
        player.teleport(location);
        player.sendMessage("Goodbye!");
    }

    public void preview() {

    }

}
