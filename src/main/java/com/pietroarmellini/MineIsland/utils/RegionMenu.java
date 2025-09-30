package com.pietroarmellini.MineIsland.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonMenu;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import com.pietroarmellini.MineIsland.MineIsland;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.runnables.SubRegionBorderRunnable;
import com.pietroarmellini.MineIsland.settings.GeneralSettings;

public class RegionMenu extends Menu {

	@Position(13)
	private final Button subRegionButton;
	private Region region;

	public RegionMenu(Region region) {
		super();
		this.region = region;
		setTitle("Island Menu");
		setSlotNumbersVisible();
		setSize(9 * 3);

		this.subRegionButton = new ButtonMenu(
				new SubRegionMenu(),
				CompMaterial.IRON_ORE,
				"&cAreas",
				"&7Click to open the Areas menu");
	}

	private class SubRegionMenu extends MenuPagged<SubRegion> {
		public SubRegionMenu() {
			super(RegionMenu.this, region.getOwnedSubRegionsList());

			this.setTitle("Island Menu - Areas");
		}

		@Override
		protected ItemStack convertToItemStack(SubRegion subRegion) {
			if (!subRegion.isOwned()) {
				if (subRegion.isBuyable()) {
					return ItemCreator.of(CompMaterial.IRON_ORE, 
															"Buyable Area", 
															"Click to Buy", 
															"Price: §a"+ String.format("%.2f", GeneralSettings.getPriceForNextSubRegion(region.getNumberOfOwnedSubRegions())), 
															"Balance: §a" + String.format("%.2f", EconomyHandler.getBalance(Bukkit.getPlayer(region.getOwnerUUID())))).make();
				} else {
					return ItemCreator.of(CompMaterial.STONE, "Not Owned Area",
							"Area Not Owned", "Not Buyable Yet")
							.make();
				}
			} else {
				return ItemCreator.of(CompMaterial.IRON_BLOCK, "Owned Area").make();
			}

		}

		@Override
		protected void onPageClick(Player player, SubRegion subRegion, ClickType click) {
			if (!subRegion.isOwned() && subRegion.isBuyable()) {
				if (EconomyHandler.chargePlayer(player, GeneralSettings.getPriceForNextSubRegion(region.getNumberOfOwnedSubRegions()))) {
					subRegion.setOwned(true);
					subRegion.setBuyable(false);
					player.closeInventory();
					Common.tell(player, "You have successfully bought this area!");
					MineIsland.getInstance().worldManager.saveRegions();
					player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.0f);
					new SubRegionBorderRunnable(player, region, subRegion, WorldManager.worldName)
							.runTaskTimer(MineIsland.getInstance(), 0L, 10L);
				} else {
					player.closeInventory();
					Common.tell(player, "You can't afford to buy this area!");
				}

			}
		}
	}
}
