package com.pietroarmellini.MineIsland.utils;

import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.model.ConfigSerializable;

public class SubRegion implements ConfigSerializable {
	private int x, z;
	private boolean isOwned;
	private boolean isBuyable = false;

	public SubRegion(int x, int z) {
		this.x = x;
		this.z = z;
		this.isOwned = false;
	}

	private SubRegion(int x2, int z2, boolean isOwned2, boolean isBuyable2) {
		this.x = x2;
		this.z = z2;
		this.isOwned = isOwned2;
		this.isBuyable = isBuyable2;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean owned) {
		this.isOwned = owned;
	}

	public boolean isBuyable() {
		return isBuyable;
	}

	public void setBuyable(boolean buyable) {
		this.isBuyable = buyable;
	}

	@Override
	public SerializedMap serialize() {
		SerializedMap map = new SerializedMap();

		map.put("X", this.x);
		map.put("Z", this.z);
		map.put("IsOwned", this.isOwned);
		map.put("IsBuyable", this.isBuyable);

		return map;
	}

	public static SubRegion deserialize(SerializedMap map) {
		int x = map.getInteger("X");
		int z = map.getInteger("Z");
		boolean isOwned = map.getBoolean("IsOwned");
		boolean isBuyable = map.getBoolean("IsBuyable");

		return new SubRegion(x, z, isOwned, isBuyable);
	}
}