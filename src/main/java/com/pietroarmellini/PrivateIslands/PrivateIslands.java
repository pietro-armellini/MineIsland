package com.pietroarmellini.PrivateIslands;

import org.bukkit.plugin.java.JavaPlugin;

import com.pietroarmellini.PrivateIslands.commands.PrivateIslandCommand;
import com.pietroarmellini.PrivateIslands.listeners.IslandsWorldListener;
import com.pietroarmellini.PrivateIslands.utils.WorldManager;

import org.bukkit.Bukkit;

public class PrivateIslands extends JavaPlugin {

		WorldManager worldManager = new WorldManager();
			
		@Override
		public void onEnable() {
				System.out.println("PrivateIslands plugin has been enabled!");

				worldManager.loadIslandsWorld();
				Bukkit.getPluginManager().registerEvents(new IslandsWorldListener(worldManager), this);
				getCommand("privateisland").setExecutor(new PrivateIslandCommand(worldManager));
			}
}
