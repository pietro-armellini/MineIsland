package com.pietroarmellini.MyRealm.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class WorldManager {

	private String worldName = "realms";

	public void createVoidWorldIfNotExists() {
			if (Bukkit.getWorld(worldName) == null) {
					System.out.println("Loading world '"+worldName+"'.");
					WorldCreator creator = new WorldCreator(worldName);
					creator.generator(new VoidChunkGenerator());
					Bukkit.createWorld(creator);
			}
	}

	public String getWorldName() {
			return worldName;
	}
}

class VoidChunkGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        // createChunkData è deprecato ma ancora l’API corretta per i plugin
        return createChunkData(world);
    }
}
