# Tweaking Ore Generation using Ore-Tweaker

## Intro

Ore Tweaker has the option to let you "tweak" how ores spawn in the world. This is achieved by disabling the original ore-generation and replacing it with our own custom ore generation settings.

### Tweaking Modded Ores

Ore Tweaker works with some mods that use the standard vanilla Minecraft ore generation like Thermal Expansion. Mods
that use their own custom generation can not be tweaked by default.

However, you can create a [Compatibility Request](https://github.com/EwyBoy/OreTweaker/issues/new?assignees=EwyBoy&labels=Compatibility+Request&template=compatibility-request.md&title=%5BCompatibility%5D+MOD-NAME) by clicking [here](https://github.com/EwyBoy/OreTweaker/issues/new?assignees=EwyBoy&labels=Compatibility+Request&template=compatibility-request.md&title=%5BCompatibility%5D+MOD-NAME)
and I will attempt to hardcode a way into supporting to tweaking the ores from that mod.

Most mods allow you to disable their world-gen in their own config files so if you can do it you can then add your own [Custom Ore-Generation](https://github.com/EwyBoy/OreTweaker/wiki/Custom-Ore-Generation) by following this guide [here](https://github.com/EwyBoy/OreTweaker/wiki/Custom-Ore-Generation).

### Tweaking Ore-Generation

Tweaking ore-generation is fairly easy and can be done by adding entries in the `oretweaker.json` file.

#### Example:
The example below will first disable both coal and iron ores normal world-generation and then replace it with our own custom generation as defined below.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:coal_ore",
      "filler": "minecraft:stone",
      "minY": 32,
      "maxY": 96,
      "maxVeinSize": 22,
      "spawnRate": 16,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:iron_ore",
      "filler": "minecraft:stone",
      "minY": 52,
      "maxY": 74,
      "maxVeinSize": 12,
      "spawnRate": 14,
      "biomeBlacklist": [
        "swamp",
        "jungle"
      ],
      "biomeWhitelist": []
    }
  ]
}
```
* Coal ores will now only spawn between y-32 and y-96 in veins up to 22 block large. The world-generator will attempt to spawn these veins 16 times per chunk.
* Iron ores will now only spawn between y-52 and y-74 in veins up to 12 block large. The world-generator will attempt to spawn these veins 14 times per chunk. Iron will no longer generate in Swamps or Jungles.


## Multiple spawns for same ore
You can add multiple spawns for the same ore. In this case iron will spawn in huge veins in mountains, small veins inside desert sandstone and fairly normally in the rest of the world.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:iron_ore",
      "filler": "minecraft:stone",
      "minY": 90,
      "maxY": 120,
      "maxVeinSize": 32,
      "spawnRate": 5.0,
      "biomeBlacklist": [],
      "biomeWhitelist": [
        "mountains",
        "wooded_mountains",
        "gravelly_mountains"
      ]
    },
    {
      "ore": "minecraft:iron_ore",
      "filler": "minecraft:stone",
      "minY": 32,
      "maxY": 64,
      "maxVeinSize": 8,
      "spawnRate": 20.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:iron_ore",
      "filler": "minecraft:sandstone",
      "minY": 58,
      "maxY": 64,
      "maxVeinSize": 4,
      "spawnRate": 30.0,
      "biomeBlacklist": [],
      "biomeWhitelist": ["desert"]
    }
  ]
}
```