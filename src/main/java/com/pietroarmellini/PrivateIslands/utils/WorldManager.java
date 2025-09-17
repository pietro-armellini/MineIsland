package com.pietroarmellini.PrivateIslands.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class WorldManager {

    private String worldName = "islands";
    private Map<UUID, Region> playerRegions = new HashMap<>();
		World world;

    private final String saveFile = "plugins/PrivateIslands/regions.dat";

    public void loadIslandsWorld() {
        if (Bukkit.getWorld(worldName) == null) {
            System.out.println("Loading world '" + worldName + "'.");
            WorldCreator creator = new WorldCreator(worldName);
            creator.generator(new VoidChunkGenerator());
            this.world = Bukkit.createWorld(creator);
        }
    }

    public String getWorldName() {
        return worldName;
    }

    public boolean canPlayerMoveHere(Player player, Location loc) {
			Region region = playerRegions.get(player.getUniqueId());
			if(region != null && region.isLocationInRegion(loc)) {
				return true;
			}
			return false;
    }

		public boolean canPlayerBuildHere(Player player, Location loc) {
			Region region = playerRegions.get(player.getUniqueId());
        if(region != null && region.isLocationInRegion(loc)) {
					return true;
				}
				return false;
    }

		public Region getRegion(Player player){
    if(playerRegions.containsKey(player.getUniqueId())){
        return playerRegions.get(player.getUniqueId());
    } else {
        // Assign region and build spawn platform
        int x = (int)(Math.random()*1000)-500;
        int z = (int)(Math.random()*1000)-500;
        Region region = new Region(x, z);
        playerRegions.put(player.getUniqueId(), region);

        // Build 3x3 grass platform under spawn location
        Location spawn = region.getSpawnLocation(world);
        int baseY = spawn.getBlockY() - 1;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Location blockLoc = new Location(world, spawn.getBlockX() + dx, baseY, spawn.getBlockZ() + dz);
                world.getBlockAt(blockLoc).setType(Material.GRASS_BLOCK);
            }
        }

        saveRegions();

        return region;
    }
	}

    public void saveRegions() {
        File file = new File(saveFile);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); // Create missing directories
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(playerRegions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadRegions() {
        File file = new File(saveFile);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                playerRegions = (Map<UUID, Region>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

class VoidChunkGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        // createChunkData è deprecato ma ancora l’API corretta per i plugin
        return createChunkData(world);
    }
}
