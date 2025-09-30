package com.pietroarmellini.MineIsland.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlConfig;

import com.pietroarmellini.MineIsland.MineIsland;
import com.pietroarmellini.MineIsland.utils.Region;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class WorldManager {

	public static String worldName = "islands";
	private Map<UUID, Region> playerRegions = new HashMap<>();
	World world;
	private final String saveFile = "plugins/MineIsland" + "/data/player-regions.yml";


	public void loadIslandsWorld() {
		if (Bukkit.getWorld(worldName) == null) {
			System.out.println("[MineIsland] Loading world '" + worldName + "'.");
			WorldCreator creator = new WorldCreator(worldName);
			creator.generator(new VoidChunkGenerator());
			this.world = Bukkit.createWorld(creator);
		}
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

	public Region createRegion(Player player) {
		Common.tell(player, "Assigning you a new island...");
		player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);

		boolean overlaps = true;
		int x = 0, z = 0;
		// Ensure the new region does not overlap with existing ones
		while (overlaps) {
			overlaps = false;
			// Assign region and build spawn platform
			x = (int) (Math.random() * 1000) - 500;
			z = (int) (Math.random() * 1000) - 500;

			for (Region existingRegion : playerRegions.values()) {
				if (existingRegion.getX() == x && existingRegion.getZ() == z) {
					overlaps = true;
					break;
				}
			}
		}
		Region region = new Region(x, z, player.getUniqueId());
		playerRegions.put(player.getUniqueId(), region);

		// Build 3x3 grass platform under spawn location
		Common.tell(player, "Building your spawn platform...");
		Location spawn = region.getSpawnLocation();
		int baseY = spawn.getBlockY() - 1;
		for (int dx = -1; dx <= 1; dx++) {
			for (int dz = -1; dz <= 1; dz++) {
				Location blockLoc = new Location(world, spawn.getBlockX() + dx, baseY, spawn.getBlockZ() + dz);
				world.getBlockAt(blockLoc).setType(Material.GRASS_BLOCK);
			}
		}

		saveRegionsAsync();
		return region;
	}

	public Region getRegion(Player player) {
		if (playerRegions.containsKey(player.getUniqueId())) {
			return playerRegions.get(player.getUniqueId());
		} else {
			return null;
		}
	}

	public boolean hasRegion(Player player) {
		return playerRegions.containsKey(player.getUniqueId());
	}

	public void loadRegions() {
		File file = new File(saveFile);
		if (!file.exists())
			return;

		YamlConfig config = YamlConfig.fromFile(file);
		SerializedMap regionsMap = config.getMap("playerRegions");

		playerRegions.clear();
		for (Map.Entry<String, Object> entry : regionsMap.entrySet()) {
			UUID playerId = UUID.fromString(entry.getKey());
			Region region = Region.deserialize(SerializedMap.of(entry.getValue()));
			playerRegions.put(playerId, region);
		}
	}

	public void saveRegionsAsync() {
		Common.runAsync(() -> {
			saveRegions();
		});
	}

	public void saveRegions() {
		// Your existing synchronous save code
		File file = new File(saveFile);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		YamlConfig config = YamlConfig.fromFile(file);

		SerializedMap regionsMap = new SerializedMap();
		for (Map.Entry<UUID, Region> entry : playerRegions.entrySet()) {
			regionsMap.put(entry.getKey().toString(), entry.getValue().serialize());
		}

		config.set("playerRegions", regionsMap);
		config.save();
	}

}

class VoidChunkGenerator extends ChunkGenerator {
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		// createChunkData è deprecato ma ancora l’API corretta per i plugin
		return createChunkData(world);
	}
}
