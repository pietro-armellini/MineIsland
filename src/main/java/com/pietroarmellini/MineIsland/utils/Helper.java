package com.pietroarmellini.MineIsland.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

import com.pietroarmellini.MineIsland.settings.GeneralSettings;
import com.pietroarmellini.MineIsland.settings.MyLocalization;

public final class Helper {

	public static void teleportPlayerToFallbackWorld(Player player) {
		World fallbackWorld = Bukkit.getWorld(GeneralSettings.FALLBACK_WORLD); // Replace "world" with your main world
		if (fallbackWorld != null) {
			Location target = null;
			if (player.getBedSpawnLocation() != null && GeneralSettings.RESPAWN_AT_BED_LOCATION) {
				target = player.getBedSpawnLocation();
			} else {
				target = fallbackWorld.getSpawnLocation();
			}
			player.teleport(target);
			Common.tell(player, MyLocalization.Messages.ISLAND_LEFT);
		} else {
			Common.tell(player, MyLocalization.Messages.NO_FALLBACK_WORLD_SET);
		}
	}
}
