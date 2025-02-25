package me.Pizza.listener;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.Pizza.FurnitureShop;

public class RegionListener implements Listener {

    private final FurnitureShop plugin;

    public RegionListener(FurnitureShop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!plugin.getZoneConfig().isZoneSetup() || plugin.getZoneConfig().getZoneStatus() == false) return;

        Location to = event.getTo();
        Location from = event.getFrom();

        // Only check if player changed block position
        if (to.getBlockX() == from.getBlockX() 
            && to.getBlockY() == from.getBlockY() 
            && to.getBlockZ() == from.getBlockZ()) {
            return;
        }

        if (isZone(to)) {
            plugin.getZoneManager().enter(event.getPlayer());
        }
    }

    private boolean isZone(Location location) {
        if (location == null) return false;

        String regionId = plugin.getZoneConfig().getRegion();

        Set<ProtectedRegion> regions = WorldGuard.getInstance()
            .getPlatform().getRegionContainer()
            .createQuery().getApplicableRegions(BukkitAdapter.adapt(location))
            .getRegions();

        return regions.stream().anyMatch(region -> region.getId().equals(regionId));
    }
    
}
