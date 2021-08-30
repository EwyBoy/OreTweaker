**# Custom Ore Generation using Ore-Tweaker

## Intro

Ore Tweaker has the option to let you add your own custom ores spawn in the world. This allows you to make any block generate in veins underground.

### Customizing Ore-Generation
Customizing ore-generation is fairly easy and can be done by adding new files to the `../config/oretweaker/data/` folder.  
These files can be named pretty much anything as long as they have the `.json` name extension.

#### Example:

The example below will add very rare bone block veins spawning close to bedrock.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:bone_block",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 12,
      "maxVeinSize": 8,
      "spawnRate": 2.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    }
  ]
}
```
![img](https://i.imgur.com/6zvfE2u.png)

The example below will add huge lava pools to the surface of desert biomes.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:lava",
      "filler": "minecraft:sand",
      "minY": 56,
      "maxY": 64,
      "maxVeinSize": 64,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["DESERT"]
    }
  ]
}
```
![img](https://i.imgur.com/3MMO0Wm.png)

The example below will add glass blocks spawning in oak trees.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:glass",
      "filler": "minecraft:oak_leaves",
      "minY": 60,
      "maxY": 70,
      "maxVeinSize": 24,
      "spawnRate": 50.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    }
  ]
}
```
![img](https://i.imgur.com/NGSn9sP.png)

The example below will add emerald blocks generating in **The End**. Since there is only `end_stone` spawning naturally in **The End** there is no need for a biome filter.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:emerald_block",
      "filler": "minecraft:end_stone",
      "minY": 0,
      "maxY": 128,
      "maxVeinSize": 16,
      "spawnRate": 50.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    }
  ]
}
```
![img](https://i.imgur.com/gSVrmpi.png)


The example below will add multiple ore blocks generating around the **The End** platform. Uses a biome filter cause air blocks are found everywhere.  

_You can have several different types of ores in the same file, so you don't need to create new file per ore you want to tweak. You can organize it however you like_ 
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:iron_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 64,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["END"]
    },
    {
      "ore": "minecraft:coal_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 32,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["END"]
    },
    {
      "ore": "minecraft:gold_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 24,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["END"]
    },
    {
      "ore": "minecraft:diamond_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 16,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["END"]
    }
  ]
}
```
![img](https://i.imgur.com/MLNLUK8.png)**
