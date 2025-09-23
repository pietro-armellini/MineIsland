package com.pietroarmellini.PrivateIslands.utils;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;

public class RegionMenu extends MenuPagged<EntityType> {

	
	public RegionMenu() {
		setTitle("Island Menu");
		setSlotNumbersVisible();
		setSize(9*6);
	}


	@Override
	protected ItemStack convertToItemStack(EntityType item) {
		return ItemCreator.ofEgg(item, "ciao").make();
	}

	@Override
	protected void onPageClick(Player player, EntityType item, ClickType click) {
		player.sendMessage("You clicked on " + item.name());
	}
}
