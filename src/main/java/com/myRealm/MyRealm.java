package com.myrealm;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class MyRealm extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    System.out.println("MyRealm plugin has been enabled!");

    String worldName = "realms";
    if (Bukkit.getWorld(worldName) == null) {
      WorldCreator creator = new WorldCreator(worldName);
      creator.generator(new VoidChunkGenerator());
      Bukkit.createWorld(creator);
      System.out.println("World '" + worldName + "' created.");
    } else {
      System.out.println("World '" + worldName + "' already exists.");
    }
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
  }
}

// Add this class at the end of the file or in a new file
class VoidChunkGenerator extends ChunkGenerator {
  @Override
  public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
    return createChunkData(world); // Returns an empty chunk (void)
  }
}