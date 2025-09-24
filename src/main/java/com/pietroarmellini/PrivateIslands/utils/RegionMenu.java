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

public class RegionMenu extends Menu {

	@Position(4)
	private final Button subRegionButton;

	public RegionMenu() {
		super();
		setTitle("Island Menu");
		setSlotNumbersVisible();
		setSize(9*5);

		this.subRegionButton = new ButtonMenu(
													new TestMenu(),
													CompMaterial.GRASS_BLOCK,
													"&cSub Regions",
													"&7Click to open the sub regions menu"
											);

	}
}

class TestMenu extends Menu {

	public TestMenu() {
		super();
		setTitle("dsfsdf Menu");
		setSlotNumbersVisible();

	}
}

class SubRegionMenu extends MenuPagged<Boolean> {

	
	public SubRegionMenu() {
		//super(RegionMenu.this)
		setSlotNumbersVisible();
		setSize(9*5);
	}


	@Override
	protected ItemStack convertToItemStack(Boolean subRegion) {
		return ItemCreator.of(CompMaterial.ACACIA_BOAT, "ciao", "ciao").make();
	}

	@Override
	protected void onPageClick(Player player, Boolean SubRegion, ClickType click) {
		player.sendMessage("You clicked");
	}
}
