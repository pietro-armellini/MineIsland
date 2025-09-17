package com.pietroarmellini.PrivateIslands.utils;

import org.bukkit.Location;
import org.bukkit.World;

public class Region {
	private int x, z;
	private int regionSize = 100;
	private int borderSize = 10;

	public Region(int x, int z) {
			this.x = x;
			this.z = z;	
	}

	public int getX() {
			return x;
	}

	public int getZ() {
			return z;
	}

	//region minus the border
	public boolean isLocationInRegion(Location loc){
		if(loc.getX() >= x*regionSize + borderSize && loc.getX() < (x+1)*regionSize - borderSize &&
				loc.getZ() >= z*regionSize + borderSize && loc.getZ() < (z+1)*regionSize - borderSize){
			return true;
		}
		return false;
	}

	public Location getSpawnLocation(World world){
		System.out.println("Spawn location in " + world.getName() + " for region (" + x + "," + z + ") is (" + (x*regionSize + regionSize/2) + ",100," + (z*regionSize + regionSize/2) + ")");
		return new Location(world, x*regionSize + regionSize/2, 100, z*regionSize + regionSize/2);
	}

}
