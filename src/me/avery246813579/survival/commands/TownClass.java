package me.avery246813579.survival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.avery246813579.minersfortune.ranks.RankManager;
import me.avery246813579.minersfortune.ranks.Ranks;

public abstract class TownClass {
	protected boolean consoleCanUse = false;
	private String command;
	private Ranks canUse;

	public TownClass(String commands, Ranks canUse) {
		this.command = commands;
		this.canUse = canUse;
	}

	public void run(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if (sender instanceof ConsoleCommandSender && !consoleCanUse) {
			if (sender instanceof ConsoleCommandSender && !consoleCanUse) {
				sender.sendMessage(ChatColor.GREEN + "Console >> " + ChatColor.RED + "The console can not use this command!");
				return;
			}
		}

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (RankManager.findPlayerRank(player) != null) {
				if (RankManager.findPlayerRank(player).getRankNumber() < canUse.getRankNumber()) {
					player.sendMessage(ChatColor.RED + "This requires Staff Rank [" + canUse.getChatColor() + canUse.getName() + ChatColor.RED + "]");
					return;
				}
			}
		}

		runCommand(sender, args);
	}

	public abstract void runCommand(CommandSender sender, String[] args);

	public static void sendTownMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.GREEN + "Town >> " + ChatColor.GRAY + message);
	}
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Ranks getPermissions() {
		return canUse;
	}

	public void setPermissions(Ranks canUse) {
		this.canUse = canUse;
	}

	public boolean isConsoleCanUse() {
		return consoleCanUse;
	}

	public void setConsoleCanUse(boolean consoleCanUse) {
		this.consoleCanUse = consoleCanUse;
	}
}
