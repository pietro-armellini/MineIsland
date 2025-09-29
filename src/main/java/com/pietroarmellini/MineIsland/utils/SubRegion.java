package com.pietroarmellini.MineIsland.utils;

import java.io.Serializable;

public class SubRegion implements Serializable {
    private static final long serialVersionUID = 1L;
		private final Region parentRegion;
    private int x, z;
    private boolean isOwned;
		private boolean isBuyable = false;

    public SubRegion(int x, int z, Region parentRegion) {
        this.x = x;
        this.z = z;
        this.isOwned = false;
				this.parentRegion = parentRegion;
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
}