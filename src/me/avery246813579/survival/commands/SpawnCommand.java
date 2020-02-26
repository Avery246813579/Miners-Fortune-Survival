package me.avery246813579.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.MinersFortune;
import me.avery246813579.minersfortune.ranks.Ranks;
import me.avery246813579.survival.town.TownHandler;

public class SpawnCommand extends TownClass{
	public SpawnCommand() {
		super("spawn", Ranks.DEFAULT);
	}

	@Override
	public void runCommand(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		int id = MinersFortune.getPlugin().getSqlHandler().getPlayerId(player);

		if(TownHandler.findTown(id) == null){
			sendTownMessage(sender, ChatColor.RED + "You are not part of a town!");
			return;
		}
		
		player.teleport(TownHandler.findTown(id).getSpawn());
		sendTownMessage(sender, ChatColor.YELLOW + "You have teleported to your towns spawn.");
	}
}
