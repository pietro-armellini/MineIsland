package com.pietroarmellini.MineIsland.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import com.pietroarmellini.MineIsland.utils.Region;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class WorldManager {

	private String worldName = "islands";
	private Map<UUID, Region> playerRegions = new HashMap<>();
	World world;
	Set<UUID> playersInEditingMode = new HashSet<>();

	private final String saveFile = "plugins/MineIsland/regions.dat";

	public void loadIslandsWorld() {
		if (Bukkit.getWorld(worldName) == null) {
			System.out.println("[MineIsland] Loading world '" + worldName + "'.");
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
		if (region != null && region.isLocationInRegion(loc)) {
			return true;
		}
		return false;
	}

	public boolean canPlayerBuildHere(Player player, Location loc) {
		Region region = playerRegions.get(player.getUniqueId());
		if (region != null && region.isLocationInRegion(loc)) {
			return true;
		}
		return false;
	}

	public Region getRegion(Player player) {
		if (playerRegions.containsKey(player.getUniqueId())) {
			return playerRegions.get(player.getUniqueId());
		} else {
			player.sendMessage("Assigning you a new island...");
			// Assign region and build spawn platform
			int x = (int) (Math.random() * 1000) - 500;
			int z = (int) (Math.random() * 1000) - 500;
			Region region = new Region(x, z, player.getUniqueId());
			playerRegions.put(player.getUniqueId(), region);

			// Build 3x3 grass platform under spawn location
			player.sendMessage("Building your spawn platform...");
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

	public boolean hasRegion(Player player) {
		return playerRegions.containsKey(player.getUniqueId());
	}

	public void setPlayerInEditingMode(Player player, boolean isEditing) {
		if (isEditing) {
			playersInEditingMode.add(player.getUniqueId());
		} else {
			playersInEditingMode.remove(player.getUniqueId());
		}
	}

	public boolean isPlayersInEditingMode(UUID uniqueId) {
		return playersInEditingMode.contains(uniqueId);
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
