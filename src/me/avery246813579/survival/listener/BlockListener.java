package me.avery246813579.survival.listener;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.survival.town.Plot;
import me.avery246813579.survival.town.Town;
import me.avery246813579.survival.town.TownHandler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getBlock() == null) {
			return;
		}

		Player player = event.getPlayer();
		Plot plot = TownHandler.findPlot(event.getBlock().getLocation());
		if (plot == null) {
			return;
		}

		Town town = TownHandler.findTown(plot);
		int id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);
		if(!town.getMembers().contains(id)){
			player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't place this block!");
			event.setCancelled(true);
			return;
		}
		
		if (plot.getOwner() == 0) {
			if (town.isCanBuildOffPlot()) {
				return;
			}
		}

		if (plot.getOwner() == 0) {
			if (town.getCreatorId() != id) {
				player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't place this block!");
				event.setCancelled(true);
				return;
			}else{
				return;
			}
		}

		if (plot.getOwner() != id) {
			player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't place this block!");
			event.setCancelled(true);
			return;
		}

	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlock() == null) {
			return;
		}

		Player player = event.getPlayer();
		Plot plot = TownHandler.findPlot(event.getBlock().getLocation());
		if (plot == null) {
			return;
		}

		Town town = TownHandler.findTown(plot);
		int id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);
		if(!town.getMembers().contains(id)){
			player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't place this block!");
			event.setCancelled(true);
			return;
		}
		
		if (plot.getOwner() == 0) {
			if (town.isCanBuildOffPlot()) {
				return;
			}
		}

		if (plot.getOwner() == 0) {
			if (town.getCreatorId() != id) {
				player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't place this block!");
				event.setCancelled(true);
				return;
			}else{
				return;
			}
		}

		if (plot.getOwner() != id) {
			player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't place this block!");
			event.setCancelled(true);
			return;
		}
	}
}
