package me.avery246813579.survival.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TownCommand implements CommandExecutor {
	List<TownClass> townClasses = new ArrayList<TownClass>();

	public TownCommand() {
		townClasses.add(new CreateCommand());
		townClasses.add(new ClaimCommand());
		townClasses.add(new SpawnCommand());
	}

	/** Protected **/
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if (CommandLabel.equalsIgnoreCase("town") || CommandLabel.equalsIgnoreCase("t")) {
			if (args.length == 0) {
				sendCommandMessage(sender, ChatColor.RED + "/Town Create (Name) " + ChatColor.YELLOW + "- Creates a town.");
				sendCommandMessage(sender, ChatColor.RED + "/Town Join (Name) " + ChatColor.YELLOW + "- Joins a town.");
				sendCommandMessage(sender, ChatColor.RED + "/Town Invite (Player) " + ChatColor.YELLOW + "- Invites a player to town.");
				sendCommandMessage(sender, ChatColor.RED + "/Town Claim " + ChatColor.YELLOW + "- Claims a plot of land.");
				sendCommandMessage(sender, ChatColor.RED + "/Town List " + ChatColor.YELLOW + "- Shows all the towns on the server.");
				sendCommandMessage(sender, ChatColor.RED + "/Town Info " + ChatColor.YELLOW + "- Info on current town.");
				sendCommandMessage(sender, ChatColor.RED + "/Town Spawn " + ChatColor.YELLOW + "- Teleports a player back to their spawn.");
				return true;
			}

			for (TownClass townClass : townClasses) {
				if (townClass.getCommand().equalsIgnoreCase(args[0])) {
					townClass.run(sender, cmd, CommandLabel, args);
				}
			}
		}

		return false;
	}
	
	public static void sendCommandMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.GREEN + "Command >> " + ChatColor.GRAY + message);
	}
}
