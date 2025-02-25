package me.Pizza.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.Pizza.FurnitureShop;
import me.Pizza.manager.ZoneManager;

public class PlayerListener implements Listener {

    private final ZoneManager zoneManager;

    public PlayerListener(FurnitureShop plugin) {
        zoneManager = plugin.getZoneManager();
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if (zoneManager.isInZone(event.getPlayer())) zoneManager.exit(event.getPlayer());
    }
    
}
