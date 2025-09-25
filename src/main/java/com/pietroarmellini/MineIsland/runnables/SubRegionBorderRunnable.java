package com.pietroarmellini.MineIsland.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.pietroarmellini.MineIsland.utils.Region;
import com.pietroarmellini.MineIsland.utils.SubRegion;

public class SubRegionBorderRunnable extends BukkitRunnable {

    private final Player player;
    private final Region region;
    private final SubRegion subRegion;
    private final World world;
    private int ticks = 0;

    public SubRegionBorderRunnable(Player player, Region region, SubRegion subRegion, String worldName) {
        this.player = player;
        this.region = region;
        this.subRegion = subRegion;
        this.world = Bukkit.getWorld(worldName);
    }

    @Override
public void run() {
    if (ticks >= 20 || !player.isOnline()) { // 20 iterations * 0.5s = 10 seconds
        cancel();
        return;
    }

    int regionBaseX = region.getX() * 100;
    int regionBaseZ = region.getZ() * 100;

    int subBaseX = regionBaseX + (subRegion.getX() * 10);
    int subBaseZ = regionBaseZ + (subRegion.getZ() * 10);

    int y = player.getLocation().getBlockY();

    // Simple particle
    for (int x = subBaseX; x <= subBaseX + 10; x++) {
        player.spawnParticle(Particle.HAPPY_VILLAGER, new Location(world, x + 0.5, y, subBaseZ + 0.5), 2);
        player.spawnParticle(Particle.HAPPY_VILLAGER, new Location(world, x + 0.5, y, subBaseZ + 10 + 0.5), 2);
    }
    for (int z = subBaseZ; z <= subBaseZ + 10; z++) {
        player.spawnParticle(Particle.HAPPY_VILLAGER, new Location(world, subBaseX + 0.5, y, z + 0.5), 2);
        player.spawnParticle(Particle.HAPPY_VILLAGER, new Location(world, subBaseX + 10 + 0.5, y, z + 0.5), 2);
    }

    ticks++;
}

}
