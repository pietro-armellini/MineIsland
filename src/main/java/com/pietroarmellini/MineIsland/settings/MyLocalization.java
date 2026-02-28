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
		public static String NOT_IN_ISLAND_WORLD;
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

			if (isSetDefault("Not_In_Island_World"))
				NOT_IN_ISLAND_WORLD = getString("Not_In_Island_World");

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

	public static final class IslandMenu {
		public static String INFORMATION_LORE_LINE1;
		public static String INFORMATION_LORE_LINE2;
		public static String INFORMATION_LORE_LINE3;

		private static void init() {
			setPathPrefix("IslandMenu");

			if (isSetDefault("Information_Lore_Line1"))
				INFORMATION_LORE_LINE1 = getString("Information_Lore_Line1");

			if (isSetDefault("Information_Lore_Line2"))
				INFORMATION_LORE_LINE2 = getString("Information_Lore_Line2");

			if (isSetDefault("Information_Lore_Line3"))
				INFORMATION_LORE_LINE3 = getString("Information_Lore_Line3");
		}
	}

	public static final class HelpMessage {
		public static String HELPMESSAGE_HEADER_LINE1;
		public static String HELPMESSAGE_HEADER_LINE2;


		private static void init() {
			setPathPrefix("HelpMessage");

			if (isSetDefault("Helpmessage_Header_Line1"))
				HELPMESSAGE_HEADER_LINE1 = getString("Helpmessage_Header_Line1");

			if (isSetDefault("Helpmessage_Header_Line2"))
				HELPMESSAGE_HEADER_LINE2 = getString("Helpmessage_Header_Line2");
		}
	}

	public static final class CommandsDescription {
		public static String NEW_COMMAND;
		public static String MENU_COMMAND;
		public static String SETSPAWN_COMMAND;
		public static String RELOAD_COMMAND;
		public static String BACK_COMMAND;
		public static String TP_COMMAND;


		private static void init() {
			setPathPrefix("CommandsDescription");

			if (isSetDefault("New_Command"))
				NEW_COMMAND = getString("New_Command");

			if (isSetDefault("Menu_Command"))
				MENU_COMMAND = getString("Menu_Command");

			if (isSetDefault("SetSpawn_Command"))
				SETSPAWN_COMMAND = getString("SetSpawn_Command");

			if (isSetDefault("Reload_Command"))
				RELOAD_COMMAND = getString("Reload_Command");

			if (isSetDefault("Back_Command"))
				BACK_COMMAND = getString("Back_Command");

			if (isSetDefault("Tp_Command"))
				TP_COMMAND = getString("Tp_Command");
		}
	}

}
