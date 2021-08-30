# Disabling Ore Generation using Ore-Tweaker

## Intro

Ore Tweaker has the option to let you disable ores from spawning at all.

### Modded Ores

Ore Tweaker works with some mods that use the standard vanilla Minecraft ore generation like Thermal Expansion. Mods
that use their own custom generation can not be disabled by default.

However, you can create a [Compatibility Request](https://github.com/EwyBoy/OreTweaker/issues/new?assignees=EwyBoy&labels=Compatibility+Request&template=compatibility-request.md&title=%5BCompatibility%5D+MOD-NAME) by clicking [here](https://github.com/EwyBoy/OreTweaker/issues/new?assignees=EwyBoy&labels=Compatibility+Request&template=compatibility-request.md&title=%5BCompatibility%5D+MOD-NAME)
and I will attempt to hardcode a way into supporting to disable the ores from that mod.

Most mods allow you to disable their world-gen in their own config files so if you can do it, it is probably easier to
do it there than using Ore-Tweaker.

### Disabling Ore-Generation

Disabling ore-generation is fairly easy and can be done by setting the values to `-1` in the ores json file you wish to disable.

#### Example:
The example below will completely remove all iron and coal ores from spawning.
```json
{
  "oreConfig": [
    {
      "ore": "minecraft:iron_ore",
      "minY": -1,
      "maxY": -1,
      "maxVeinSize": -1,
      "spawnRate": -1,
      "biomeBlacklist": [],
      "biomeWhitelist": []
    }
  ]
}
```

## Disable everything

if you wish to disable all vanilla ores you can simply delete all the files inside the `../config/oretweaker/data/` folder and move the `remove_everything.json` from the template folder to the data folder.