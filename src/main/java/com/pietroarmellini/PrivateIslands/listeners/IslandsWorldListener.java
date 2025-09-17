package com.pietroarmellini.PrivateIslands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
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
        if (!event.getTo().getWorld().getName().equals(worldManager.getWorldName())) return;
        if (event.getPlayer().hasPermission("privateislands.admin")) return;

        // Only check if block coordinates changed
        if (event.getFrom().getBlockX() == event.getTo().getBlockX() &&
            event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
            return;
        }

        if (!worldManager.canPlayerMoveHere(event.getPlayer(), event.getTo())) {
            event.getPlayer().sendMessage("You cannot leave your island");
            event.setCancelled(true);
        }
    }

		@EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {
			if(event.getBlock().getWorld().getName().equals(worldManager.getWorldName())){
				if (event.getPlayer().hasPermission("privateislands.admin")) return;
				if(!worldManager.canPlayerBuildHere(event.getPlayer(), event.getBlock().getLocation())){
					event.getPlayer().sendMessage("You cannot build here, this is not your island");
					event.setCancelled(true);
				}
			}
		}

		@EventHandler
    public void onPlayerFalls(EntityDamageEvent event) {
        if(event.getEntity().getWorld().getName().equals(worldManager.getWorldName())){
            if(event.getCause() == EntityDamageEvent.DamageCause.VOID && event.getEntity() instanceof Player){
                event.setCancelled(true);
                World mainWorld = Bukkit.getWorld("world"); // Replace "world" with your main world name if different
                if (mainWorld != null) {
                    Location spawnLocation = mainWorld.getSpawnLocation();
                    Player player = (Player) event.getEntity();
                    player.teleport(spawnLocation);
                    player.sendMessage("You left your island");
                }
            }
        }
    }

}
