package me.avery246813579.survival.town;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Plot {
	private int owner;
	private String name;
	private List<Block> blocks = new ArrayList<Block>();
	private Location midPoint;

	public Plot(Location midPoint, int owner) {
		this.name = "Plot";
		this.midPoint = midPoint;
		this.owner = owner;
	}

	public Plot(Location midPoint) {
		this.name = "Plot";
		this.midPoint = midPoint;
	}
	
	public void loadBlocks(Location firstLocation, Location secondLocation) {
		int topBlockX = (firstLocation.getBlockX() < secondLocation.getBlockX() ? secondLocation.getBlockX() : firstLocation.getBlockX());
		int bottomBlockX = (firstLocation.getBlockX() > secondLocation.getBlockX() ? secondLocation.getBlockX() : firstLocation.getBlockX());

		int topBlockZ = (firstLocation.getBlockZ() < secondLocation.getBlockZ() ? secondLocation.getBlockZ() : firstLocation.getBlockZ());
		int bottomBlockZ = (firstLocation.getBlockZ() > secondLocation.getBlockZ() ? secondLocation.getBlockZ() : firstLocation.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++) {
			for (int z = bottomBlockZ; z <= topBlockZ; z++) {
				for (int y = 0; y <= 255; y++) {
					Block block = firstLocation.getWorld().getBlockAt(x, y, z);

					blocks.add(block);
				}
			}
		}
	}

	public boolean isIn(Location loc) {
		if (loc.getBlockX() < midPoint.getBlockX() - 7)
			return false;
		if (loc.getBlockX() > midPoint.getBlockX() + 7)
			return false;
		if (loc.getBlockZ() < midPoint.getBlockZ() - 7)
			return false;
		if (loc.getBlockZ() > midPoint.getBlockZ() + 7)
			return false;
		return true;
	}

	public List<Plot> getAdjacentPlots() {
		List<Plot> plots = new ArrayList<Plot>();
		plots.add(new Plot(midPoint.clone().add(15D, 0, 0)));
		plots.add(new Plot(midPoint.clone().add(-15D, 0, 0)));
		plots.add(new Plot(midPoint.clone().add(0, 0, 15D)));
		plots.add(new Plot(midPoint.clone().add(0, 0, -15D)));
		plots.add(new Plot(midPoint.clone().add(15D, 0, 15D)));
		plots.add(new Plot(midPoint.clone().add(15D, 0, -15D)));
		plots.add(new Plot(midPoint.clone().add(-15D, 0, 15D)));
		plots.add(new Plot(midPoint.clone().add(-15D, 0, -15D)));
		return plots;
	}

	public boolean isInsidePlot(Block block) {
		if (blocks.contains(block)) {
			return true;
		}

		return false;
	}

	public Location getMidPoint() {
		return midPoint;
	}

	public void setMidPoint(Location midPoint) {
		this.midPoint = midPoint;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
}
