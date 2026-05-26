# CrosshairTarget Filter
English | [简体中文](https://github.com/LitnhJacuzzi/CrosshairTarget-Filter/blob/master/README_CN.md)
## Summary
CrosshairTarget Filter is a Minecraft mod that allows your crosshair to pick an interactive target through **ANY** entity or block specified in the configuration. This mod requires **Cloth Config** to manage the configuration.

> [!WARNING]  
> **THIS MOD MAY BE AGAINST THE RULES OF YOUR SERVER, USE IT AT YOUR OWN RISK!**
## Usage
You can filter the targets in two ways - whitelist and blacklist, they are stored separately in the configuration and only take effects if the corresponding filter type is selected. The rules for whitelist and blacklist are as follows:  
 - Whitelist - **ONLY** elements in the list **CAN** be picked by the crosshair.  
 - Blacklist - Elements in the list **CANNOT** be picked by the crosshair.

For naming conventions of list elements, please read further:
### Entities
Entities can be filtered by **type**, **category** and **name**.  
 - Types are specified in the format `<namespace>:<registryName>`, such as `minecraft:pig`, which can be found in the target entity section of F3 debug overlay.  
 - Categories are specified in the format `[<category>]`, such as `[MONSTER]`.  
 - Names are specified in the format `"<name>"`, such as `"Example"`.
### Blocks
Blocks can only be filtered by type in the format `<namespace>:<registryName>`, such as `minecraft:stone`, which can be found in the target block section of F3 debug overlay.
