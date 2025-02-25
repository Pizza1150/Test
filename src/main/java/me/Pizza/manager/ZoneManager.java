package me.Pizza.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.Pizza.zone.Zone;

public class ZoneManager {
 
    private final Map<UUID, Zone> zones = new HashMap<>();

    public boolean isInZone(Player player) {
        return zones.containsKey(player.getUniqueId());
    }

    public void enter(Player player) {
        if (isInZone(player)) return;

        Zone zone = new Zone(player);
        zones.put(player.getUniqueId(), zone);
    }

    public void exit(Player player) {
        if (!isInZone(player)) return;

        zones.get(player.getUniqueId()).exit();
        zones.remove(player.getUniqueId());
    }

    public void clear() {
        zones.values().forEach(zone -> zone.exit());
        zones.clear();
    }

}
