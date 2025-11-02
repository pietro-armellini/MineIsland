package com.pietroarmellini.MineIsland.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class GeneralSettings extends SimpleSettings {

	// Basic price setting
	public static Double BASIC_PRICE = 10.0;

	// Increasing percentage setting
	public static Double INCREASING_PERCENTAGE = 15.0;

	// Fallback world setting
	public static String FALLBACK_WORLD = "world";

	// Enable expand setting
	public static Boolean EXPANDABLE_MODE = true;

	// Enable respawn at bed location setting
	public static Boolean RESPAWN_AT_BED_LOCATION = true;




	private static void init() {
		if (isSetDefault("Basic_Price"))
			BASIC_PRICE = getDouble("Basic_Price");

		if (isSetDefault("Increasing_Percentage"))
			INCREASING_PERCENTAGE = getPercentage("Increasing_Percentage");

		if (isSetDefault("Fallback_World"))
			FALLBACK_WORLD = getString("Fallback_World");

		if (isSetDefault("Expandable_Mode"))
			EXPANDABLE_MODE = getBoolean("Expandable_Mode");

			if (isSetDefault("Respawn_At_Bed_Location"))
			RESPAWN_AT_BED_LOCATION = getBoolean("Respawn_At_Bed_Location");
	}

	public static double getPriceForNextSubRegion(int ownedSubRegions) {
		return BASIC_PRICE * Math.pow(1 + INCREASING_PERCENTAGE, ownedSubRegions - 1);
	}
}