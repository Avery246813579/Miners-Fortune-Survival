package me.avery246813579.survival.town;

import org.bukkit.entity.Player;

public class TownPlayer {
	private Player player;
	private Plot lastPlot;
	
	public TownPlayer(Player player){
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Plot getLastPlot() {
		return lastPlot;
	}

	public void setLastPlot(Plot lastPlot) {
		this.lastPlot = lastPlot;
	}
}
