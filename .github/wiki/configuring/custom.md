# Custom Ore Generation using Ore-Tweaker

## Intro

Ore Tweaker has the option to let you add your own custom ores spawn in the world. This allows you to make any block generate in veins underground.

### Tweaking Ore-Generation

Tweaking ore-generation is fairly easy and can be done by adding entries in the `oretweaker.json` file.

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
      "biomeWhitelist": ["desert"]
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
      "biomeWhitelist": ["the_end"]
    },
    {
      "ore": "minecraft:coal_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 32,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["the_end"]
    },
    {
      "ore": "minecraft:gold_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 24,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["the_end"]
    },
    {
      "ore": "minecraft:diamond_ore",
      "filler": "minecraft:air",
      "minY": 10,
      "maxY": 70,
      "maxVeinSize": 16,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["the_end"]
    }
  ]
}
```
![img](https://i.imgur.com/MLNLUK8.png)
