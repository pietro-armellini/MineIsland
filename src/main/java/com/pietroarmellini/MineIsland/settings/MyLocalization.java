package com.pietroarmellini.MineIsland.settings;

import org.mineacademy.fo.settings.SimpleLocalization;

public class MyLocalization extends SimpleLocalization {

	public static final class Messages {
		public static String ASSIGNING_NEW_ISLAND;
		public static String BUILDING_SPAWN_PLATFORM;
		public static String NEW_AREA;
		public static String CANNOT_AFFORD_AREA;
		public static String CANNOT_ENTER_AREA;
		public static String CANNOT_BUILD_HERE;
		public static String ISLAND_LEFT;
		public static String NO_FALLBACK_WORLD_SET;
		public static String NO_PERMISSION;
		public static String NOT_ISLAND_OWNER;
		public static String TELEPORTED_TO_ISLAND;
		public static String ALREADY_OWN_ISLAND;
		public static String CANNOT_SET_SPAWN_HERE;
		public static String SPAWN_SET;
		public static String UNKNOWN_COMMAND;

		private static void init() {
			setPathPrefix("Messages");

			if (isSetDefault("Building_Spawn_Platform"))
				BUILDING_SPAWN_PLATFORM = getString("Building_Spawn_Platform");

			if (isSetDefault("New_Area"))
				NEW_AREA = getString("New_Area");

			if (isSetDefault("Assigning_New_Island"))
				ASSIGNING_NEW_ISLAND = getString("Assigning_New_Island");

			if (isSetDefault("Cannot_Afford_Area"))
				CANNOT_AFFORD_AREA = getString("Cannot_Afford_Area");

			if (isSetDefault("Cannot_Enter_Area"))
				CANNOT_ENTER_AREA = getString("Cannot_Enter_Area");

			if (isSetDefault("Cannot_Build_Here"))
				CANNOT_BUILD_HERE = getString("Cannot_Build_Here");

			if (isSetDefault("Island_Left"))
				ISLAND_LEFT = getString("Island_Left");

			if (isSetDefault("No_Fallback_World_Set"))
				NO_FALLBACK_WORLD_SET = getString("No_Fallback_World_Set");

			if (isSetDefault("No_Permission"))
				NO_PERMISSION = getString("No_Permission");

			if (isSetDefault("Not_Island_Owner"))
				NOT_ISLAND_OWNER = getString("Not_Island_Owner");

			if (isSetDefault("Teleported_To_Island"))
				TELEPORTED_TO_ISLAND = getString("Teleported_To_Island");

			if (isSetDefault("Already_Own_Island"))
				ALREADY_OWN_ISLAND = getString("Already_Own_Island");

			if (isSetDefault("Cannot_Set_Spawn_Here"))
				CANNOT_SET_SPAWN_HERE = getString("Cannot_Set_Spawn_Here");

			if (isSetDefault("Spawn_Set"))
				SPAWN_SET = getString("Spawn_Set");

			if (isSetDefault("Unknown_Command"))
				UNKNOWN_COMMAND = getString("Unknown_Command");

		}
	}

}
