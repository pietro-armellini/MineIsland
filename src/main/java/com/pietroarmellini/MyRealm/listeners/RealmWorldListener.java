package com.pietroarmellini.MyRealm.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.pietroarmellini.MyRealm.utils.WorldManager;

public class RealmWorldListener implements Listener {

		WorldManager worldManager;

		public RealmWorldListener(com.pietroarmellini.MyRealm.utils.WorldManager worldManager) {
				this.worldManager = worldManager;
		}
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
      if(event.getTo().getWorld().getName().equals(worldManager.getWorldName())){  
				if(!worldManager.canPlayerMoveHere(event.getPlayer(), event.getTo())){
					event.getPlayer().sendMessage("You cannot leave your island");	
					event.setCancelled(true);
					}
				}
    }

		@EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {
			if(event.getBlock().getWorld().getName().equals(worldManager.getWorldName())){
				if(worldManager.canPlayerBuildHere(event.getPlayer(), event.getBlock().getLocation())){
					event.getPlayer().sendMessage("You cannot build here, this is not your island");
					event.setCancelled(true);
				}
			}
		}

}
