package me.Pizza.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;

import me.Pizza.FurnitureShop;

import java.io.File;

public class ZoneConfig {

	private final FurnitureShop plugin;

	public ZoneConfig(FurnitureShop plugin) {
		this.plugin = plugin;

		reload();
	}

	public void reload() {
		File config = new File(plugin.getDataFolder(), "config.yml");

		if (!config.exists()) {
			plugin.saveDefaultConfig();
		} else {
			plugin.reloadConfig();
			plugin.saveConfig();
		}
	}

	public void setZoneStatus(CommandSender sender, boolean status) {
		if (status && !isZoneSetup()) {
			sender.sendMessage("§cPlease set up the zone first!");
			return;
		}

		plugin.getConfig().set("zone.enable", status);
		plugin.saveConfig();

		sender.sendMessage("§aOkie!");
	}

	public boolean getZoneStatus() {
		return plugin.getConfig().getBoolean("zone.enable");
	}

	public boolean isZoneSetup() {
		return (getRegion() != null && getViewerLocation() != null && getFurnitureLocation() != null && getExitLocation() != null);
	}

	public void setRegion(Player player, String name) {
		if (!WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(player.getWorld())).hasRegion(name)) {
			player.sendMessage("§cRegion named " + name + " does not exist!");
			return;
		}
		
		plugin.getConfig().set("zone.region", name);
		plugin.saveConfig();

		player.sendMessage("§aOkie!");
	}

	public String getRegion() {
		String region = plugin.getConfig().getString("zone.region");

		if (region == null || region.isEmpty()) return null;
		else return region;
	}

	public void setViewerLocation(Player player, Location location) {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("zone.viewer-location");

		if (section == null) {
			player.sendMessage("§cSomething wrong");
			return;
		}

		section.set("x", location.getX());
		section.set("y", location.getY());
		section.set("z", location.getZ());
		section.set("pitch", location.getPitch());
		section.set("yaw", location.getYaw());
		section.set("world", location.getWorld().getName());
		
		plugin.saveConfig();

		player.sendMessage("§aOkie!");
	}

	public Location getViewerLocation() {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("zone.viewer-location");

		if (section == null) return null;

		if (!section.contains("x") || !section.contains("y") || !section.contains("z") || 
			!section.contains("pitch") || !section.contains("yaw") || !section.contains("world")) {
			return null;
		}

		double x = section.getDouble("x");
		double y = section.getDouble("y");
		double z = section.getDouble("z");
		float pitch = (float) section.getDouble("pitch");
		float yaw = (float) section.getDouble("yaw");
		World world = Bukkit.getWorld(section.getString("world"));

		return new Location(world, x, y, z, yaw, pitch);
	}

	public void setFurnitureLocation(Player player, Location location) {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("zone.furniture-location");

		if (section == null) {
			player.sendMessage("§cSomething wrong");
			return;
		}

		section.set("x", location.getX());
		section.set("y", location.getY());
		section.set("z", location.getZ());
		section.set("pitch", location.getPitch());
		section.set("yaw", location.getYaw());
		section.set("world", location.getWorld().getName());

		plugin.saveConfig();
		
		player.sendMessage("§aOkie!");
	}

	public Location getFurnitureLocation() {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("zone.furniture-location");
		
		if (section == null) return null;

		if (!section.contains("x") || !section.contains("y") || !section.contains("z") || 
			!section.contains("pitch") || !section.contains("yaw") || !section.contains("world")) {
			return null;
		}

		double x = section.getDouble("x");
		double y = section.getDouble("y");
		double z = section.getDouble("z");
		float pitch = (float) section.getDouble("pitch");
		float yaw = (float) section.getDouble("yaw");
		World world = Bukkit.getWorld(section.getString("world"));

		return new Location(world, x, y, z, yaw, pitch);
	}

	public void setExitLocation(Player player, Location location) {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("zone.exit-location");

		if (section == null) {
			player.sendMessage("§cSomething wrong");
			return;
		}

		section.set("x", location.getX());
		section.set("y", location.getY());
		section.set("z", location.getZ());
		section.set("pitch", location.getPitch());
		section.set("yaw", location.getYaw());
		section.set("world", location.getWorld().getName());

		plugin.saveConfig();

		player.sendMessage("§aOkie!");
	}

	public Location getExitLocation() {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("zone.exit-location");
		
		if (section == null) return null;

		if (!section.contains("x") || !section.contains("y") || !section.contains("z") || 
			!section.contains("pitch") || !section.contains("yaw") || !section.contains("world")) {
			return null;
		}

		double x = section.getDouble("x");
		double y = section.getDouble("y");
		double z = section.getDouble("z");
		float pitch = (float) section.getDouble("pitch");
		float yaw = (float) section.getDouble("yaw");
		World world = Bukkit.getWorld(section.getString("world"));

		return new Location(world, x, y, z, yaw, pitch);
	}
	
}