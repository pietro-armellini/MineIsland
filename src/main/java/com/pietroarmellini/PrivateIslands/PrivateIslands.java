package com.pietroarmellini.PrivateIslands;

import com.pietroarmellini.PrivateIslands.commands.PrivateIslandCommand;
import com.pietroarmellini.PrivateIslands.listeners.IslandsWorldListener;
import com.pietroarmellini.PrivateIslands.managers.WorldManager;

import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

public class PrivateIslands extends SimplePlugin {

		WorldManager worldManager = new WorldManager();
			
		@Override
		public void onPluginStart() {
				System.out.println("[PrivateIslands] plugin enabled!");

				worldManager.loadIslandsWorld();
				worldManager.loadRegions();
				Bukkit.getPluginManager().registerEvents(new IslandsWorldListener(worldManager), this);
				getCommand("privateisland").setExecutor(new PrivateIslandCommand(worldManager));
			}

			public void onPluginStop() {
				System.out.println("[PrivateIslands] plugin disabled!");
				worldManager.saveRegions();
			}
}
