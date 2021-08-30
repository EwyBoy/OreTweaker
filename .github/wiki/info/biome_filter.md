# Biome Filtering

## When specifying the entries in the biome blacklist & whitelist you got two options:  

You can either specify the biome registry name you wish to blacklist / whitelist, or you can use any of the tags listed below provided by the Forge Biome Dictionary.  

### Biome Registry Name:
Examples of biome registry names

| Registry Names               |
|------------------------------|
| `minecraft:plains`           |
| `minecraft:badlands`         |
| `biomesoplenty:burnt_forest` |
| `modid:biome_registry_name`  |

#### Easy ways to find the biome registry names:

Go to the biome you want the registry name from and press F3. On the left side of the screen it will now display the registry name of your current biome.
`Biome: minecraft:beach` for example.

Another methode is to open chat and type `/locatebiome ..` it will then list all biome registry names in the chat. You can start typing here to search using auto-completion to find the biome you want.

### Forge Biome Dictionary:

The forge biome dictionary uses tags that organizes multiple biomes together under different groups.
By whitelisting the `HOT` tag that ore will only be able to spawn in biomes that are tagged with the hot label.  

It also makes it easier to generate ores in specific biomes.
Rather than having to list every single biome for a generic group like this:
```
whitelist [
    "minecraft:mountains",
    "minecraft:wooded_mountains",
    "minecraft:gravelly_mountains",
]
```

You can simply do: 
```
whitelist ["MOUNTAIN"]
```

Below you will be able to find a list of all default tags provided by Forge.
Remember that it is the responsibility for each modder that creates custom biomes to tag their biomes under these tables.

#### Temperature-based tags.
* HOT
* COLD
------------------------------------------------------------------------------------
#### Tags specifying the amount of vegetation a biome has.
* SPARSE
* DENSE
------------------------------------------------------------------------------------
#### Tags specifying how moist a biome is. 
* WET
* DRY
------------------------------------------------------------------------------------
#### Tree-based tags:

* SAVANNA       _(SAVANNA refers to dry, desert-like trees (Such as Acacia))_
* CONIFEROUS    _(CONIFEROUS refers to snowy trees (Such as Spruce))_
* JUNGLE        _(JUNGLE refers to jungle trees)_
------------------------------------------------------------------------------------
#### Tags specifying the nature of a biome
* SPOOKY
* DEAD
* LUSH
* MUSHROOM
* MAGICAL
* RARE
* PLATEAU
* MODIFIED
  

* OCEAN
* RIVER
------------------------------------------------------------------------------------
#### A general tag for all water-based biomes.

* WATER

------------------------------------------------------------------------------------

#### Generic types which a biome can be

* MESA
* FOREST
* PLAINS
* MOUNTAIN
* HILLS
* SWAMP
* SANDY
* SNOWY
* WASTELAND
* BEACH
* VOID
------------------------------------------------------------------------------------
#### Tags specifying the dimension a biome generates in.
* OVERWORLD
* NETHER
* END
