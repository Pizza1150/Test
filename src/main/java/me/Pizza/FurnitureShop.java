package me.Pizza;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.Pizza.command.FurnitureShopCommand;
import me.Pizza.config.ZoneConfig;
import me.Pizza.listener.PlayerListener;
import me.Pizza.listener.RegionListener;
import me.Pizza.manager.ZoneManager;

public final class FurnitureShop extends JavaPlugin {

	@Getter
	private static FurnitureShop plugin;
	
	@Getter
	private ZoneManager zoneManager;

	@Getter
	private ZoneConfig zoneConfig;

	@Override
	public void onEnable() {
		plugin = this;

		zoneManager = new ZoneManager();
		zoneConfig = new ZoneConfig(this);

		getCommand("furnitureshop").setExecutor(new FurnitureShopCommand(this));

		getServer().getPluginManager().registerEvents(new RegionListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	}

	@Override
	public void onDisable() {
		zoneManager.clear();
	}

}
