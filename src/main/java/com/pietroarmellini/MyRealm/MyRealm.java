package com.pietroarmellini.MyRealm;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import com.pietroarmellini.MyRealm.utils.WorldManager;
import com.pietroarmellini.MyRealm.commands.RealmCommand;
import com.pietroarmellini.MyRealm.listeners.RealmWorldListener;

public class MyRealm extends JavaPlugin {

		WorldManager worldManager = new WorldManager();
			
		@Override
		public void onEnable() {
				System.out.println("MyRealm plugin has been enabled!");

				worldManager.loadRealmsWorld();
				Bukkit.getPluginManager().registerEvents(new RealmWorldListener(worldManager), this);
				getCommand("realm").setExecutor(new RealmCommand(worldManager));
			}
}
