package com.pietroarmellini.PrivateIslands.utils;

import java.io.Serializable;

public class SubRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x, z;
    private boolean isOwned;

    public SubRegion(int x, int z) {
        this.x = x;
        this.z = z;
        this.isOwned = false;
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
}