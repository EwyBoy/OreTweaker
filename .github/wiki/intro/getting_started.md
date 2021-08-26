## Configuration:

### Check out these chapters for information on specific topics
* [Disabling Ore Generation](https://github.com/EwyBoy/OreTweaker/wiki/Disabling-Ore-Generation)
* [Tweaking Ore Generation](https://github.com/EwyBoy/OreTweaker/wiki/Tweaking-Ore-Generation)
* [Custom Ore Generation](https://github.com/EwyBoy/OreTweaker/wiki/Custom-Ore-Generation)

### Understanding the json

#### Values:

|      Value       |      Explanation                                                                                                       | 
| ---------------- | ---------------------------------------------------------------------------------------------------------------------- | 
| `ore`            | The registry name of the block to generate. Can be found by pressing F3 + H and hover over in inventory                |
| `filler`         | What block to replace with the "ore", usually stone or nether rack                                                     |
| `minY`           | Minimum Y-Level ore can spawn above                                                                                    |
| `maxY`           | Maximum Y-Level ore can spawn below                                                                                    |
| `maxVeinSize`    | Maximum size of ore vein                                                                                               |
| `spawnRate`      | How many attempts at generating the ore per chunk                                                                      |
| `biomeBlacklist` | List of forbidden biome names where the ore cannot generate **[Choose either blacklist or whitelist or none of them]** |
| `biomeWhitelist` | List of allowed biome names where the ore can generate **[Prioritized above blacklist]**                               |


### Example of how the `coal_ore.json` file can look like.
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
    }
  ]
}
```