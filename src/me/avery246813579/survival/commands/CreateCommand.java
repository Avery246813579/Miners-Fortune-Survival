package me.avery246813579.survival.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.minersfortune.sql.tables.AccountTable;
import me.avery246813579.survival.town.Plot;
import me.avery246813579.survival.town.Town;
import me.avery246813579.survival.town.TownHandler;

public class CreateCommand extends TownClass {
	public CreateCommand() {
		super("create", Ranks.DEFAULT);
	}

	@Override
	public void runCommand(CommandSender sender, String[] args) {
		if (args.length == 1) {
			sendTownMessage(sender, ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/Town Create (Name)");
			return;
		}

		Player player = (Player) sender;
		int id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);

		for (Town town : TownHandler.getTowns()) {
			town.getMembers().contains(id);
			sendTownMessage(sender, ChatColor.RED + "You are already in a town!");
			return;
		}

		AccountTable accountTable = MinersFortune.getPlugin().getSqlHandler().getAccount(player);
		if (accountTable.getCredits() < 500) {
			sendTownMessage(sender, ChatColor.RED + "You need 500 Credits to buy a town!");
			return;
		}

		Plot plot = new Plot(player.getLocation());
		for (Town town : TownHandler.getTowns()) {
			if (town.getName().equalsIgnoreCase(args[1])) {
				sendTownMessage(sender, ChatColor.RED + "A town with that name already exists!");
				return;
			}
			
			for(Plot plots : town.getPlots()){
				for(Block block : plots.getBlocks()){
					if(plot.getBlocks().contains(block)){
						sendTownMessage(sender, ChatColor.RED + "A town already has land near! Choose a new location!");
						return;
					}
				}
			}
		}

		accountTable.setCredits(accountTable.getCredits() - 500);
		MinersFortune.getPlugin().getSqlHandler().saveAccount(accountTable);

		Town town = new Town(args[1], player.getLocation(), id, new ArrayList<Integer>(Arrays.asList(id)), new ArrayList<Integer>(Arrays.asList(id)), 1, 0, 0, false, false, false);
		town.getPlots().add(plot);
		TownHandler.getTowns().add(town);
		
		sendTownMessage(sender, ChatColor.YELLOW + "You have created a town called " + args[1] + "!");
	}
}
