package com.pietroarmellini.MyRealm;

import org.bukkit.plugin.java.JavaPlugin;

import com.pietroarmellini.MyRealm.utils.WorldManager;

public class MyRealm extends JavaPlugin {

	WorldManager worldManager = new WorldManager();
	
  @Override
  public void onEnable() {
    System.out.println("MyRealm plugin has been enabled!");
		worldManager.createVoidWorldIfNotExists();
	}
}
