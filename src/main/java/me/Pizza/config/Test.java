/*
 * CUSTOM CONFIG EXAMPLE
 */
package me.Pizza.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import lombok.Getter;
import me.Pizza.FurnitureShop;

public class Test {
    
    private final FurnitureShop plugin;    

    private File file;

    @Getter
    private FileConfiguration config;

    public Test(FurnitureShop plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    private void loadConfig() {
        file = new File(plugin.getDataFolder(), "zone.yml");

        if (!file.exists()) {
            plugin.saveResource("zone.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }
 
//    public void setExitLocation(Location location) {
//        plugin.getZoneConfig().getConfig().set("exit-location", location);
//    }
//
//    public Location getExitLocation() {
//        return plugin.getZoneConfig().getConfig().getLocation("exit-location");
//    }

}
