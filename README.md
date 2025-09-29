
<p align="center">
  <img src="docs/img/mineisland_logo.png" alt="Mine Island Logo" width="200"/>
</p>

# 🌴 Mine Island

A survival-oriented Spigot plugin that provides players with their own personal floating islands, allowing them to build, expand, and create a private base in a safe environment.

## Overview

Mine Island gives each player a dedicated island in a separate `islands` world, complete with structured land management, zone ownership, and upgradeable territory. The plugin integrates seamlessly with popular economy and utility plugins, offering a customizable experience for server owners and players alike.

## Features

- **Personal Islands**: Each player receives their own island to build freely.  
- **Structured Land System**: Islands are divided into **100x100 slots** with a **90x90 buildable area** in the center.  
- **Zone Ownership**: Islands are further divided into **10x10 zones** (9x9 usable). Players start with the central zone and can expand gradually.  
- **Upgradeable Land**: Players can expand their islands by purchasing additional zones via `/mineisland manage`.  
- **Configurable Economy**: Set expansion prices and define multipliers for each purchase.  
- **Plugin Integration**: Supports [Vault](https://www.spigotmc.org/resources/vault.34315/) and [Essentials](https://essentialsx.net/) for full economy and utility functionality.  

## Installation

### Prerequisites
- A Minecraft server running **Spigot**, **Paper**, or a compatible fork.  
- **Vault** plugin installed.  
- **EssentialsX** plugin installed.  
- Java 21 or higher recommended.

### Steps
1. Download the latest `MineIsland.jar` from [SpigotMC](https://www.spigotmc.org/) or the GitHub releases page.  
2. Place the `.jar` file in your server's `plugins` folder.  
3. Restart the server to load the plugin.  
4. Customize settings in `config.yml` as needed.  

## Usage

### Commands
- `/mineisland` – Teleport you to your island
- `/mineisland manage` – Opens the island management menu.  

### Permissions
| Permission                        | Description                                                                 |
|-----------------------------------|-----------------------------------------------------------------------------|
| `mineisland.command.mineisland`   | Allows access to `/mineisland` commands and the management menu.           |
| `mineisland.op`                   | Grants full plugin access and bypasses island movement restrictions.       |

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.  
2. Create a new branch (`git checkout -b feature/your-feature`).  
3. Make your changes and commit (`git commit -m 'Add your feature'`).  
4. Push to your branch (`git push origin feature/your-feature`).  
5. Open a Pull Request.  

## Credits

**Foundation Library** – Developed by **Kangarko (MineAcademy)**



## License

This project is licensed under the [MIT License](LICENSE).  

