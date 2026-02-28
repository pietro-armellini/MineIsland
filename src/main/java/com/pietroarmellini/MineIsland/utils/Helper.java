package com.pietroarmellini.MineIsland.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

import com.pietroarmellini.MineIsland.settings.GeneralSettings;
import com.pietroarmellini.MineIsland.settings.MyLocalization;

public final class Helper {

	public static void teleportPlayerToFallbackLocation(Player player) {
		World fallbackWorld = GeneralSettings.FALLBACK_LOCATION != null ? GeneralSettings.FALLBACK_LOCATION.getWorld() : null;
		if (fallbackWorld != null) {
			Location target = GeneralSettings.FALLBACK_LOCATION;
			if (player.getBedSpawnLocation() != null && GeneralSettings.RESPAWN_AT_BED_LOCATION) {
				target = player.getBedSpawnLocation();
			} 
			player.teleport(target);
			Common.tell(player, MyLocalization.Messages.ISLAND_LEFT);
		} else {
			Common.tell(player, MyLocalization.Messages.NO_FALLBACK_WORLD_SET);
		}
	}
}
