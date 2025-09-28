package com.pietroarmellini.MineIsland.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

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

            // ✅ Perform your action here
            player.sendMessage("You paid " + amount + " coins!");
            // e.g., give an item:
            // player.getInventory().addItem(new ItemStack(Material.DIAMOND));

            return true;
        } else {
            player.sendMessage("You don't have enough money!");
            return false;
        }
    }
}
