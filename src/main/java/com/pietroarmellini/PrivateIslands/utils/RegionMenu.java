package com.pietroarmellini.PrivateIslands.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonMenu;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import com.pietroarmellini.PrivateIslands.PrivateIslands;
import com.pietroarmellini.PrivateIslands.runnables.SubRegionBorderRunnable;

public class RegionMenu extends Menu {

	@Position(13)
	private final Button subRegionButton;
	private Region region;

	public RegionMenu(Region region) {
		super();
		this.region = region;
		setTitle("Private Island Menu");
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

			this.setTitle("Private Island Menu - Areas");
		}

		@Override
		protected ItemStack convertToItemStack(SubRegion subRegion) {
			if (!subRegion.isOwned()) {
				if (subRegion.isBuyable()) {
					return ItemCreator.of(CompMaterial.IRON_ORE, "Buyable Area", "Click to Buy").make();
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
				subRegion.setOwned(true);
				subRegion.setBuyable(false);
				player.closeInventory();
				player.sendMessage("You have successfully bought this area!");
				PrivateIslands.getInstance().worldManager.saveRegions();
				new SubRegionBorderRunnable(player, region, subRegion, PrivateIslands.getInstance().worldManager.getWorldName())
    									.runTaskTimer(PrivateIslands.getInstance(), 0L, 10L);

			}
		}
	}
}
