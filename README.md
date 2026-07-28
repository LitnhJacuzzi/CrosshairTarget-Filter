# CrosshairTarget Filter
English | [简体中文](https://github.com/LitnhJacuzzi/CrosshairTarget-Filter/blob/master/README_CN.md)
## Summary
CrosshairTarget Filter is a Minecraft mod that allows your crosshair to pass through **ANY** entity or block specified in the configuration and pick the interactive target behind it. This mod requires **Cloth Config** to manage the configuration.

> [!WARNING]  
> **THIS MOD MAY BE AGAINST THE RULES OF YOUR SERVER, USE IT AT YOUR OWN RISK!**

## Usage
This mod features two **keybindings** that conveniently toggle the filters on/off, initially unassigned. You can filter the targets in two ways - whitelist and blacklist, they are stored separately in the configuration and only take effects if the corresponding filter type is selected. The rules for whitelist and blacklist are as follows:  
 - Whitelist - **ONLY** elements in the list **CAN** be picked by the crosshair.  
 - Blacklist - Elements in the list **CANNOT** be picked by the crosshair.

For naming conventions of list elements, please read further:

### Entities
Entities can be filtered by **type**, **tag**, **category** or **name**.  
 - Types are specified in the format `<namespace>:<registryName>`, such as `minecraft:pig`, which can be found in the target entity section of F3 debug overlay.  
 - Tags are specified in the format `#<namespace>:<registryName>`, such as `#minecraft:undead`, which can be found in [Minecraft Wiki](https://minecraft.wiki/w/Entity_type_tag_(Java_Edition)) or below the target entity section of F3 debug overlay since 26.1.
 - Categories are specified in the format `[<category>]`, such as `[MONSTER]`.  
   As of 26.2, there are 8 categories in total:
   ```
   MONSTER (hostile mobs)
   CREATURE (passive mobs)
   AMBIENT (bat)
   AXOLOTLS (axolotl)
   UNDERGROUND_WATER_CREATURE (glow squid)
   WATER_CREATURE (squid, dolphin, nautilus)
   WATER_AMBIENT (cod, salmon, tropical fish, pufferfish)
   MISC (player, villager, golems and non-living entities)
   ```
   Note: Wandering trader belongs to `CREATURE` category.
 - Names are specified in the format `"<name>"`, such as `"Example"`.
### Blocks
Blocks can be filtered by **type** or **tag**.
 - Types are specified in the format `<namespace>:<registryName>`, such as `minecraft:stone`, which can be found in the target block section of F3 debug overlay.  
 - Tags are specified in the format `#<namespace>:<registryName>`, such as `#minecraft:logs`, which can be found below the target block section of F3 debug overlay.
