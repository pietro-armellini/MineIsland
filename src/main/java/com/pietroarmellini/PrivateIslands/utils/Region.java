package com.pietroarmellini.PrivateIslands.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	private int x, z;
	private int regionSize = 100;
	private int borderSize = 10;
	private int subRegionSize = 10;
	private SubRegion[][] subRegions;

	public Region(int x, int z) {
		this.x = x;
		this.z = z;

		// Initialize subregions
		this.subRegions = new SubRegion[regionSize / subRegionSize][regionSize / subRegionSize];
		for (int i = 0; i < regionSize / subRegionSize; i++) {
			for (int j = 0; j < regionSize / subRegionSize; j++) {
				subRegions[i][j] = new SubRegion(i, j);
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
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

	public Location getSpawnLocation(World world) {
		Location spawnLocation = new Location(world, x * regionSize + regionSize / 2 + 5, 100,
				z * regionSize + regionSize / 2 + 5);
		getSubRegion(spawnLocation).setOwned(true);
		return spawnLocation;
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

public List<SubRegion> getOwnedSubRegionsList() {
    List<SubRegion> ownedList = new ArrayList<>(81); // 9x9 grid

    for (int i = 1; i < 10; i++) {   // skip border
        for (int j = 1; j < 10; j++) {       
            subRegions[i][j].setBuyable(false); // reset all to false
        }
    }

    for (int i = 1; i < 10; i++) {
        for (int j = 1; j < 10; j++) {
            if (subRegions[i][j].isOwned()) {
                // check all 4 directions
                if (i > 1 && !subRegions[i-1][j].isOwned()) {
                    subRegions[i-1][j].setBuyable(true);
                }
                if (i < 9 && !subRegions[i+1][j].isOwned()) {
                    subRegions[i+1][j].setBuyable(true);
                }
                if (j > 1 && !subRegions[i][j-1].isOwned()) {
                    subRegions[i][j-1].setBuyable(true);
                }
                if (j < 9 && !subRegions[i][j+1].isOwned()) {
                    subRegions[i][j+1].setBuyable(true);
                }
            }
            ownedList.add(subRegions[i][j]);
        }
    }

    return ownedList;

}


}
