package me.avery246813579.survival.town;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.survival.Survival;

import org.bukkit.Location;

public class Town {
	private String name, creator;
	private List<Plot> plots = new ArrayList<Plot>();
	private List<Integer> assistants = new ArrayList<Integer>();
	private List<Integer> members = new ArrayList<Integer>();
	private Location spawn;
	private int level, creatorId, bankAmount, taxAmount;
	private boolean canBuildOffPlot, canSwitchOffPlot, canOpenPublicItems;
	
	public Town(String name, Location spawn, int creatorId, List<Integer> assistants, List<Integer> members, int level, int bankAmount, int taxAmount, boolean canBuildOffPlot, boolean canSwitchOffPlot, boolean canOpenPublicItems){
		this.name = name;
		this.creator = Survival.getMinersFortune().getSqlHandler().getAccount(creatorId).getName();
		this.creatorId = creatorId;
		this.level = level;
		this.assistants = assistants;
		this.members = members;
		this.spawn = spawn;
		this.bankAmount = bankAmount;
		this.canOpenPublicItems = canOpenPublicItems;
		this.taxAmount = taxAmount;
	}
	
	public Plot findPlot(Location location){
		for(Plot plot : plots){
			if(plot.isInsidePlot(location.getBlock())){
				return plot;
			}
		}
		
		return null;
	}
	
	public int getAllowedPlots(){
		return level * 5; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public List<Plot> getPlots() {
		return plots;
	}

	public void setPlots(List<Plot> plots) {
		this.plots = plots;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public List<Integer> getAssistants() {
		return assistants;
	}

	public void setAssistants(List<Integer> assistants) {
		this.assistants = assistants;
	}

	public List<Integer> getMembers() {
		return members;
	}

	public void setMembers(List<Integer> members) {
		this.members = members;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	public int getBankAmount() {
		return bankAmount;
	}

	public void setBankAmount(int bankAmount) {
		this.bankAmount = bankAmount;
	}

	public boolean isCanBuildOffPlot() {
		return canBuildOffPlot;
	}

	public void setCanBuildOffPlot(boolean canBuildOffPlot) {
		this.canBuildOffPlot = canBuildOffPlot;
	}

	public boolean isCanSwitchOffPlot() {
		return canSwitchOffPlot;
	}

	public void setCanSwitchOffPlot(boolean canSwitchOffPlot) {
		this.canSwitchOffPlot = canSwitchOffPlot;
	}

	public boolean isCanOpenPublicItems() {
		return canOpenPublicItems;
	}

	public void setCanOpenPublicItems(boolean canOpenPublicItems) {
		this.canOpenPublicItems = canOpenPublicItems;
	}

	public int getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}
}
