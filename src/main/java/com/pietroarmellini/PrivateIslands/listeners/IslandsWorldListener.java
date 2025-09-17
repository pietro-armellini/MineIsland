package com.pietroarmellini.PrivateIslands.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.pietroarmellini.PrivateIslands.utils.WorldManager;

public class IslandsWorldListener implements Listener {

		WorldManager worldManager;

		public IslandsWorldListener(WorldManager worldManager) {
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

		public void onPlayerFalls(EntityDamageEvent event) {
			if(event.getEntity().getWorld().getName().equals(worldManager.getWorldName())){
				if(event.getCause() == EntityDamageEvent.DamageCause.WORLD_BORDER){
					event.setCancelled(true);

				}
			}
		}

}
