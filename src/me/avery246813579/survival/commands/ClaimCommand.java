package me.avery246813579.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.survival.town.Plot;
import me.avery246813579.survival.town.Town;
import me.avery246813579.survival.town.TownHandler;

public class ClaimCommand extends TownClass {
	public ClaimCommand() {
		super("claim", Ranks.DEFAULT);
	}

	@Override
	public void runCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		int id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);

		Town town = null;
		for (Town t : TownHandler.getTowns()) {
			if (t.getAssistants().contains(id)) {
				town = t;
			}
		}

		if (town == null) {
			sendTownMessage(sender, ChatColor.RED + "You are not part of a town you can claim plots in!");
			return;
		}

		if (town.getAllowedPlots() + 1 < town.getPlots().size()) {
			sendTownMessage(sender, ChatColor.RED + "Your town does not have enough plots to claim this!");
			return;
		}

		Plot claimingPlot = null;
		for (Plot plots : town.getPlots()) {
			for(Plot ap : plots.getAdjacentPlots()){
				if(ap.isIn(player.getLocation())){
					claimingPlot = ap;
				}
			}
		}
		
		if(claimingPlot == null){
			sendTownMessage(sender, ChatColor.RED + "This plot is not Adjacent to your plot!");
			return;
		}

		for (Town towns : TownHandler.getTowns()) {
			for (Plot plots : towns.getPlots()) {
				if (plots.isIn(player.getLocation())) {
					sendTownMessage(sender, ChatColor.RED + "A town already has land near! Choose a new location!");
					return;
				}
			}
		}

		town.getPlots().add(claimingPlot);
		sendTownMessage(sender, ChatColor.YELLOW + "You have claimed a new plot!");
	}
}
