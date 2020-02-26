package me.avery246813579.survival;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.survival.commands.TownCommand;
import me.avery246813579.survival.listener.BlockListener;
import me.avery246813579.survival.listener.PlayerListener;
import me.avery246813579.survival.town.TownHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Survival extends JavaPlugin{
	private static MinersFortune minersFortune;
	private static Survival plugin;
	
	public void onEnable() {
		minersFortune = (MinersFortune) Bukkit.getPluginManager().getPlugin("MinersFortune");
		plugin = this;
		
		TownCommand townCommand = new TownCommand();
		getCommand("town").setExecutor(townCommand);
		getCommand("t").setExecutor(townCommand);
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockListener(), this);

		new TownHandler();
	}
	
	public void onDisable() {

	}

	public static Survival getPlugin() {
		return plugin;
	}

	public static void setPlugin(Survival plugin) {
		Survival.plugin = plugin;
	}

	public static MinersFortune getMinersFortune() {
		return minersFortune;
	}

	public static void setMinersFortune(MinersFortune minersFortune) {
		Survival.minersFortune = minersFortune;
	}
}
