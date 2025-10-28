package com.pietroarmellini.MineIsland.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.mineacademy.fo.Common;

import com.pietroarmellini.MineIsland.settings.MyLocalization;

public class EconomyHandler {

	private static Economy economy = null;

	// Call this in onEnable()
	public static boolean setupEconomy() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		economy = rsp.getProvider();
		return economy != null;
	}

	public static boolean chargePlayer(Player player, double amount) {
		if (economy == null) {
			Bukkit.getLogger().warning("Economy is not set up!");
			return false;
		}

		if (economy.getBalance(player) >= amount) {
			economy.withdrawPlayer(player, amount);
			return true;
		} else {
			Common.tell(player, MyLocalization.Messages.CANNOT_AFFORD_AREA);
			return false;
		}
	}

	public static double getBalance(Player player) {
		if (economy == null) {
			Bukkit.getLogger().warning("Economy is not set up!");
			return 0.0;
		}
		return economy.getBalance(player);
	}
}
