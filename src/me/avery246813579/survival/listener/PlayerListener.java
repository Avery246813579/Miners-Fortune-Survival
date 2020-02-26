package me.avery246813579.survival.listener;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.survival.town.Plot;
import me.avery246813579.survival.town.Town;
import me.avery246813579.survival.town.TownHandler;
import me.avery246813579.survival.town.TownPlayer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		TownHandler.getPlayers().add(new TownPlayer(player));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		TownHandler.getPlayers().remove(TownHandler.findTownPlayer(player));
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		Plot plotFrom = TownHandler.findPlot(event.getFrom());
		Plot plotTo = TownHandler.findPlot(event.getTo());
		if (plotFrom != null && plotTo == null) {
			player.sendMessage(ChatColor.GREEN + "Location >> " + ChatColor.YELLOW + "The Wild");
		} else if ((plotFrom == null || plotFrom.getName() != plotTo.getName()) && plotTo != null) {
			player.sendMessage(ChatColor.GREEN + "Location >> " + ChatColor.YELLOW + plotTo.getName());
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getClickedBlock() == null) {
			return;
		}
		
		if (event.getClickedBlock().getType() != Material.CHEST && event.getClickedBlock().getType() != Material.DISPENSER) {
			return;
		}

		Player player = event.getPlayer();
		Plot plot = TownHandler.findPlot(event.getClickedBlock().getLocation());
		if (plot == null) {
			return;
		}

		Town town = TownHandler.findTown(plot);
		int id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);
		if(!town.getMembers().contains(id)){
			player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't interact with this block!");
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
				player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't interact with this block!");
				event.setCancelled(true);
				return;
			}else{
				return;
			}
		}

		if (plot.getOwner() != id) {
			player.sendMessage(ChatColor.GREEN + "Plot Protection >> " + ChatColor.RED + "You can't interact with this block!");
			event.setCancelled(true);
			return;
		}
	}
}
