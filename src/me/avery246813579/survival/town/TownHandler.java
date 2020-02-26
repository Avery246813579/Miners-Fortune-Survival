package me.avery246813579.survival.town;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TownHandler {
	private static List<Town> towns = new ArrayList<Town>();
	private static List<TownPlayer> players = new ArrayList<TownPlayer>();
	
	public TownHandler(){
		
	}
	
	public static Town findTown(int id){
		for(Town town : towns){
			if(town.getMembers().contains(id)){
				return town;
			}
		}
		
		return null;
	}
	
	public static Plot findPlot(Location location){
		for(Town town : towns){
			for(Plot plot : town.getPlots()){
				if(plot.isIn(location)){
					return plot;
				}
			}
		}
		
		return null;
	}
	
	public static Town findTown(Plot plot){
		for(Town town : towns){
			if(town.getPlots().contains(plot)){
				return town;
			}
		}
		
		return null;
	}
	
	public static TownPlayer findTownPlayer(Player player){
		for(TownPlayer townPlayer : players){
			if(townPlayer.getPlayer() == player){
				return townPlayer;
			}
		}
		
		return null;
	}

	public static List<Town> getTowns() {
		return towns;
	}

	public static void setTowns(List<Town> towns) {
		TownHandler.towns = towns;
	}

	public static List<TownPlayer> getPlayers() {
		return players;
	}

	public static void setPlayers(List<TownPlayer> players) {
		TownHandler.players = players;
	}
}
