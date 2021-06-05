# Ore-Tweaker Default Values

Default Values The following is some default settings for vanilla ore generation for each ore:

## Default Values

The following tables contains the default settings used by Ore-Tweaker to mimic vanilla ore generation from vanilla Minecraft.
The values for some ores are interpolated approximations of the math equations behind the vanilla ore generation. 
The values are not perfect, but the world gen produced by these values are as close to vanilla ore generation as possible.

Blocks marked with a `*` at the end like: `minecraft:lapis_ore`* are using interpolated approximations.
This is due to the blocks using custom placement configs and distribution settings witch I did not bother coding in special cases for.

### Stones:
#### These are the values used for all the stones generating naturally underground in the overworld
| ore                        | minY | maxY | vein size | rate | filler            | blacklist | whitelist                                         |
|----------------------------|------|------|-----------|------|-------------------|---------- |---------------------------------------------------|
| `minecraft:dirt`           | 1    | 256  | 32        | 10   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:gravel`         | 1    | 256  |  8        |  8   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:granite`        | 1    |  80  | 32        | 10   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:diorite`        | 1    |  80  | 32        | 10   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:andesite`       | 1    |  80  | 32        | 10   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:infested_stone` | 1    |  64  |  8        |  7   | `minecraft:stone` | :x:       |```mountains wooded_mountains gravelly_mountains```|

### Ores:
#### These are the values used for all the ores generating naturally underground in the overworld
| ore                      | minY | maxY | vein size | rate | filler            | blacklist | whitelist                                         |
|------------------------- |------|------|-----------|------|-------------------|---------- |---------------------------------------------------|
| `minecraft:coal_ore`     | 1    | 128  | 16        | 20   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:iron_ore`     | 1    |  64  |  8        | 20   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:gold_ore`     | 1    |  32  |  8        |  2   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:redstone_ore` | 1    |  16  |  7        |  8   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:lapis_ore`*   | 1    |  30  |  6        |  3   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:diamond_ore`  | 1    |  16  |  7        |  1   | `minecraft:stone` | :x:       | :x:                                               |
| `minecraft:emerald_ore`* | 4    |  32  |  1        |  5   | `minecraft:stone` | :x:       |```mountains wooded_mountains gravelly_mountains```|

### Nether:
#### These are the values used for all the stuff generating naturally in the nether
| ore                            | minY | maxY | vein size | rate | filler                 | blacklist | whitelist |
|--------------------------------|------|------|-----------|------|------------------------|-----------|-----------|
| `minecraft:soul_sand`          |  1   |  32  | 12        | 12   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:gravel`             |  5   |  37  | 32        |  2   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:nether_quartz_ore`* | 10   | 246  | 14        | 10   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:nether_quartz_ore`* | 10   | 246  | 14        | 32   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:nether_gold_ore`*   | 10   | 118  | 10        | 10   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:nether_gold_ore`*   | 10   | 118  | 10        | 20   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:blackstone`         |  5   |  32  | 10        |  2   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:magma_block`*       | 26   |  36  | 10        | 15   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:ancient_debris`*    |  8   | 120  |  1        |  1   | `minecraft:netherrack` | :x:       | :x:       |
| `minecraft:ancient_debris`*    |  8   |  24  |  2        |  1   | `minecraft:netherrack` | :x:       | :x:       |

Don't ask me why minecraft chose to add inn two different ways of spawning in nether quartz, nether gold and ancient debris, but they did.
That's why there are multiple entries with almost the same values. They are **not** duplicates.


## Default File

### Path: `../config/oretweaker/oretweaker.json`

This is the default file generated by the mod on launch if there is not already a file there named `oretweaker.json`. The file **must** be named `oretweaker.json` for the mod to find and read the file on launch and this is the only file that will be utilized by the mod for tweaking ore-generation.
The mod will also generate a backup of this file inside the [template folder](https://github.com/EwyBoy/OreTweaker/wiki/Templates) simply named `default.json`.

This file allows you to tweak all the vanilla world generation features out of the box without having to add any entries yourself. 
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:dirt",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 256,
      "maxVeinSize": 32,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:gravel",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 256,
      "maxVeinSize": 32,
      "spawnRate": 8.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:granite",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 80,
      "maxVeinSize": 32,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:diorite",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 80,
      "maxVeinSize": 32,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:andesite",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 80,
      "maxVeinSize": 32,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:infested_stone",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 64,
      "maxVeinSize": 8,
      "spawnRate": 7.0,
      "biomeBlacklist": [],
      "biomeWhitelist": [
        "mountains",
        "wooded_mountains",
        "gravelly_mountains"
      ]
    },
    {
      "ore": "minecraft:coal_ore",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 128,
      "maxVeinSize": 16,
      "spawnRate": 20.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:iron_ore",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 64,
      "maxVeinSize": 8,
      "spawnRate": 20.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:gold_ore",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 32,
      "maxVeinSize": 8,
      "spawnRate": 2.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:redstone_ore",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 16,
      "maxVeinSize": 7,
      "spawnRate": 8.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:lapis_ore",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 30,
      "maxVeinSize": 6,
      "spawnRate": 3.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:diamond_ore",
      "filler": "minecraft:stone",
      "minY": 1,
      "maxY": 16,
      "maxVeinSize": 7,
      "spawnRate": 1.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:emerald_ore",
      "filler": "minecraft:stone",
      "minY": 4,
      "maxY": 32,
      "maxVeinSize": 1,
      "spawnRate": 30.0,
      "biomeBlacklist": [],
      "biomeWhitelist": [
        "mountains",
        "wooded_mountains",
        "gravelly_mountains"
      ]
    },
    {
      "ore": "minecraft:soul_sand",
      "filler": "minecraft:netherrack",
      "minY": 1,
      "maxY": 32,
      "maxVeinSize": 12,
      "spawnRate": 12.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:gravel",
      "filler": "minecraft:netherrack",
      "minY": 5,
      "maxY": 37,
      "maxVeinSize": 32,
      "spawnRate": 2.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:nether_quartz_ore",
      "filler": "minecraft:netherrack",
      "minY": 10,
      "maxY": 246,
      "maxVeinSize": 14,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:nether_quartz_ore",
      "filler": "minecraft:netherrack",
      "minY": 10,
      "maxY": 246,
      "maxVeinSize": 14,
      "spawnRate": 32.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:nether_gold_ore",
      "filler": "minecraft:netherrack",
      "minY": 10,
      "maxY": 118,
      "maxVeinSize": 10,
      "spawnRate": 10.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:nether_gold_ore",
      "filler": "minecraft:netherrack",
      "minY": 10,
      "maxY": 118,
      "maxVeinSize": 10,
      "spawnRate": 20.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:blackstone",
      "filler": "minecraft:netherrack",
      "minY": 5,
      "maxY": 31,
      "maxVeinSize": 10,
      "spawnRate": 2.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:magma_block",
      "filler": "minecraft:netherrack",
      "minY": 26,
      "maxY": 36,
      "maxVeinSize": 10,
      "spawnRate": 15.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:ancient_debris",
      "filler": "minecraft:netherrack",
      "minY": 8,
      "maxY": 120,
      "maxVeinSize": 1,
      "spawnRate": 1.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    },
    {
      "ore": "minecraft:ancient_debris",
      "filler": "minecraft:netherrack",
      "minY": 8,
      "maxY": 24,
      "maxVeinSize": 2,
      "spawnRate": 1.0,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    }
  ]
}
```