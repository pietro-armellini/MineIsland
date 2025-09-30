package com.pietroarmellini.MineIsland.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;

import com.pietroarmellini.MineIsland.managers.WorldManager;

public class Region implements ConfigSerializable {
	private final UUID ownerUUID;
	private int x, z;
	private int regionSize = 100;
	private int borderSize = 10;
	private int subRegionSize = 10;
	private SubRegion[][] subRegions;
	private Location spawnLocation;

	public Region(int x, int z, UUID ownerUUID) {
		this.x = x;
		this.z = z;
		this.ownerUUID = ownerUUID;

		// Initialize subregions
		this.subRegions = new SubRegion[regionSize / subRegionSize][regionSize / subRegionSize];
		for (int i = 0; i < regionSize / subRegionSize; i++) {
			for (int j = 0; j < regionSize / subRegionSize; j++) {
				subRegions[i][j] = new SubRegion(i, j);
			}
		}

		Location spawnLocation = new Location(Bukkit.getWorld(WorldManager.worldName), x * regionSize + regionSize / 2 + 5,
				100,
				z * regionSize + regionSize / 2 + 5);
		getSubRegion(spawnLocation).setOwned(true);
		this.spawnLocation = spawnLocation;
	}

	private Region(UUID ownerUUID2, int x2, int z2, int regionSize2, int borderSize2, int subRegionSize2,
			SubRegion[][] subRegions2, Location spawnLocation2) {
		this.ownerUUID = ownerUUID2;
		this.x = x2;
		this.z = z2;
		this.regionSize = regionSize2;
		this.borderSize = borderSize2;
		this.subRegionSize = subRegionSize2;
		this.subRegions = subRegions2;
		this.spawnLocation = spawnLocation2;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public UUID getOwnerUUID() {
		return ownerUUID;
	}

	public Location getSpawnLocation() {
		return spawnLocation;
	}

	public Location setSpawnLocation(Location loc) {
		this.spawnLocation = loc;
		return this.spawnLocation;
	}

	// region minus the border
	public boolean isLocationInRegion(Location loc) {
		// Check if location is within region bounds (minus border)
		if (loc.getX() >= x * regionSize + borderSize && loc.getX() < (x + 1) * regionSize - borderSize &&
				loc.getZ() >= z * regionSize + borderSize && loc.getZ() < (z + 1) * regionSize - borderSize) {

			// Check if location is in an owned subregion
			SubRegion sub = getSubRegion(loc);
			if (sub != null && sub.isOwned()) {
				return true;
			}
		}
		return false;
	}

	public SubRegion getSubRegion(Location loc) {
		// Get local coordinates within the region
		int localX = (int) (loc.getX() - x * regionSize);
		int localZ = (int) (loc.getZ() - z * regionSize);

		// Get subregion indices
		int subX = localX / subRegionSize;
		int subZ = localZ / subRegionSize;

		// Bounds check
		if (subX < 0 || subX >= subRegions.length || subZ < 0 || subZ >= subRegions[0].length) {
			return null;
		}

		return subRegions[subX][subZ];
	}

	public String toString() {
		String rtn = "";
		for (int i = 0; i < regionSize / subRegionSize; i++) {
			for (int j = 0; j < regionSize / subRegionSize; j++) {
				rtn += subRegions[i][j].isOwned() ? "X" : "O";
			}
			rtn += "\n";
		}
		return rtn;
	}

	public int getNumberOfOwnedSubRegions() {
		int count = 0;
		for (int i = 0; i < regionSize / subRegionSize; i++) {
			for (int j = 0; j < regionSize / subRegionSize; j++) {
				if (subRegions[i][j].isOwned()) {
					count++;
				}
			}
		}
		return count;
	}

	public List<SubRegion> getOwnedSubRegionsList() {
		List<SubRegion> ownedList = new ArrayList<>(81); // 9x9 grid

		for (int i = 1; i < 10; i++) { // skip border
			for (int j = 1; j < 10; j++) {
				subRegions[i][j].setBuyable(false); // reset all to false
			}
		}

		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				if (subRegions[i][j].isOwned()) {
					// check all 4 directions
					if (i > 1 && !subRegions[i - 1][j].isOwned()) {
						subRegions[i - 1][j].setBuyable(true);
					}
					if (i < 9 && !subRegions[i + 1][j].isOwned()) {
						subRegions[i + 1][j].setBuyable(true);
					}
					if (j > 1 && !subRegions[i][j - 1].isOwned()) {
						subRegions[i][j - 1].setBuyable(true);
					}
					if (j < 9 && !subRegions[i][j + 1].isOwned()) {
						subRegions[i][j + 1].setBuyable(true);
					}
				}
				ownedList.add(subRegions[i][j]);
			}
		}

		return ownedList;

	}

	@Override
	public SerializedMap serialize() {
		SerializedMap map = new SerializedMap();

		map.put("OwnerUUID", this.ownerUUID);
		map.put("X", this.x);
		map.put("Z", this.z);
		map.put("RegionSize", this.regionSize);
		map.put("BorderSize", this.borderSize);
		map.put("SubRegionSize", this.subRegionSize);
		map.putIfExist("SpawnLocation", this.spawnLocation);
		map.putIfExist("SubRegions", this.subRegions);

		return map;
	}

	public static Region deserialize(SerializedMap map) {
		UUID ownerUUID = map.getUUID("OwnerUUID");
		int x = map.getInteger("X");
		int z = map.getInteger("Z");
		int regionSize = map.getInteger("RegionSize");
		int borderSize = map.getInteger("BorderSize");
		int subRegionSize = map.getInteger("SubRegionSize");
		Location spawnLocation = map.getLocation("SpawnLocation");
		SubRegion[][] subRegions = map.get("SubRegions", SubRegion[][].class);

		return new Region(ownerUUID, x, z, regionSize, borderSize, subRegionSize, subRegions, spawnLocation);
	}

}
