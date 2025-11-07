package com.pietroarmellini.MineIsland.menus;

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
import org.mineacademy.fo.menu.button.ButtonRemove;
import org.mineacademy.fo.menu.button.annotation.Position;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import com.pietroarmellini.MineIsland.MineIsland;
import com.pietroarmellini.MineIsland.api_handlers.EconomyHandler;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.runnables.SubRegionBorderRunnable;
import com.pietroarmellini.MineIsland.settings.GeneralSettings;
import com.pietroarmellini.MineIsland.settings.MyLocalization;
import com.pietroarmellini.MineIsland.utils.Helper;
import com.pietroarmellini.MineIsland.utils.Region;
import com.pietroarmellini.MineIsland.utils.SubRegion;

public class RegionMenu extends Menu {

	@Position(12)
	private final Button subRegionButton;

	@Position(14)
	private final Button removeIslandButton;
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
				"&aAreas",
				"&7Click to open the Areas menu");

		this.removeIslandButton = new RemoveIslandButton(this);
	}

	private class SubRegionMenu extends MenuPagged<SubRegion> {
		public SubRegionMenu() {
			super(RegionMenu.this, region.getOwnedSubRegionsList());

			this.setTitle("Island Menu - Areas");
		}

		@Override
		protected String[] getInfo() {
			return new String[] {
					MyLocalization.IslandMenu.INFORMATION_LORE_LINE1,
					MyLocalization.IslandMenu.INFORMATION_LORE_LINE2,
					MyLocalization.IslandMenu.INFORMATION_LORE_LINE3,
			};
		}

		@Override
		protected ItemStack convertToItemStack(SubRegion subRegion) {
			if (!subRegion.isOwned()) {
				if (subRegion.isBuyable()) {
					return ItemCreator.of(CompMaterial.IRON_ORE,
							"Buyable Area",
							"Click to Buy",
							"Price: §a" + String.format("%.2f",
									GeneralSettings.getPriceForNextSubRegion(region.getNumberOfOwnedSubRegions())),
							"Balance: §a" + String.format("%.2f", EconomyHandler.getBalance(Bukkit.getPlayer(region.getOwnerUUID()))))
							.make();
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
				if (EconomyHandler.chargePlayer(player,
						GeneralSettings.getPriceForNextSubRegion(region.getNumberOfOwnedSubRegions()))) {
					subRegion.setOwned(true);
					subRegion.setBuyable(false);
					player.closeInventory();
					Common.tell(player, MyLocalization.Messages.NEW_AREA);
					WorldManager.saveRegionsAsync();
					player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.0f);
					new SubRegionBorderRunnable(player, region, subRegion, WorldManager.worldName)
							.runTaskTimer(MineIsland.getInstance(), 0L, 10L);
				} else {
					player.closeInventory();
				}

			}
		}
	}

	public class RemoveIslandButton extends ButtonRemove {

		public RemoveIslandButton(RegionMenu parentMenu) {
			super(parentMenu, "Island", "", () -> {
				if (parentMenu.getViewer().getWorld().getName().equals(WorldManager.worldName)) {
					Helper.teleportPlayerToFallbackWorld(parentMenu.getViewer());
				}
				WorldManager.deleteRegion(parentMenu.getViewer());
			});
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.LAVA_BUCKET, "&6Delete Island", "&7Click to delete your island.").make();
		}

		@Override
		public ItemStack getRemoveConfirmItem() {
			return ItemCreator.of(CompMaterial.LAVA_BUCKET, "&6&lConfirm Deletion", "&7Click to delete your Island").make();
		}

		@Override
		public String getMenuTitle() {
			return "Island Menu - Deletation";
		}
	}
}
