package com.pietroarmellini.MineIsland.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.PlayerUtil;

import com.pietroarmellini.MineIsland.MineIsland;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.GeneralSettings;

public class IslandsWorldListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (!event.getTo().getWorld().getName().equals(WorldManager.worldName))
			return;
		if (event.getPlayer().hasPermission("mineisland.admin"))
			return;

		// Only check if block coordinates changed
		if (event.getFrom().getBlockX() == event.getTo().getBlockX() &&
				event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
			return;
		}

		if (!WorldManager.canPlayerMoveHere(event.getPlayer(), event.getTo())) {
			Common.tell(event.getPlayer(), "You are not allowed to enter this area");
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent event) {
		if (event.getBlock().getWorld().getName().equals(WorldManager.worldName) == false)
			return;
		{
			if (event.getPlayer().hasPermission("mineisland.admin"))
				return;
			if (!WorldManager.canPlayerBuildHere(event.getPlayer(), event.getBlock().getLocation())) {
				Common.tell(event.getPlayer(), "You are not allowed to build here");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerFalls(EntityDamageEvent event) {
		if (event.getEntity().getWorld().getName().equals(WorldManager.worldName)) {
			if (event.getCause() == EntityDamageEvent.DamageCause.VOID && event.getEntity() instanceof Player) {
				event.setCancelled(true);
				World fallbackWorld = Bukkit.getWorld(GeneralSettings.FALLBACK_WORLD); // Replace "world" with your main world
				Player player = (Player) event.getEntity();
				if (fallbackWorld != null) {
					Location spawnLocation = fallbackWorld.getSpawnLocation();
					player.teleport(spawnLocation);
					Common.tell(player, "You left your island");
				} else {
					player.performCommand("mi tp");
					Common.tell(player, "You left your island but there is no fallback world setted, teleporting you to your island, contact an admin!");
				}
			}
		}
	}

}
