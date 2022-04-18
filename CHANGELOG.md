**v1.17.1 - 2.6.1**  
* Ore Tweaker now only read files in the data folder ending with `.json`  
  
**v1.17.1 - 2.6.0**  
* Bump, patch and fixes  
  
**v1.17.1 - 2.5.1**  
* Changed template to use new BiomeDictionary Type `MOUNTAIN` for Emerald Ore and Infested Stone.  
* Started working on enhancing performance when generating new chunks and biomes.  
* Ore reconstruction from JSON now cashes a lot of stuff to reduce stress on biome loading.  
* More performance enhancements coming soon.  

**v1.17.1 - 2.5.0**  
* Added support for the Forge BiomeDictionary to be used in (black/white)lists.  

You can now do:  
```
"biomeWhitelist": ["MOUNTAIN", "WET", "HOT", "OCEAN", "minecraft:plains"]
```
this will now whitelist any biome from vanilla or modded that is tagged with these biome types.  

You can read more about this at: https://github.com/EwyBoy/OreTweaker/wiki/Biome-Filtering  

* Cleaned up parts of the backend  
* Cleaned up console spam  

**v1.17.1 - 2.4.1**  
* Fixed a bug where the white/black lists where not working properly

**v1.17.1 - 2.4.0**
###### Important information
Ore Tweaker 2.4.x and forward now uses the new data structure (**v2**) for ore tweaking. 
The new data structure allows multiple ore tweaking files and will make it easier to edit, download, share and use templates.
The mod will now read **ALL** `.json` files in the new `oretweaker/data` folder.  
I have written an automated updater that will update and convert your old `OreTweaker.json` from the old (**v1**) format to the new (**v2**) format and move the files inside the new `oretweaker/data` folder for you.
All you have to do is to update the mod, and the next time you launch OreTweaker will change its internal file structure to fit the new standard.
After the update the old `OreTweaker.json` will be automatically **deleted**. I have tested a lot of edge cases with the automated updater, but I can never guarantee it will work 100% if you are doing some wicked stuff.
Backup your OreTweaker `config` folder just in case.

**Changes**:

* Moved over to new data structure (v2) for ore tweaking  
* Improved console outputs and debugging
* Added config options to auto generate deepslate variants  
* Added new config options to regenerate default data / templates  
* Revamped template system to work with the new v2 data structure  
* Removed reload command due to CODEC requiring a full restart  
* Fixed bug where duplicated names for features - Thanks to lynnpye  
* Tons of backend changes  
  

**v1.17.1 - 2.3.1**
* Implemented spawnRate below 1 (Example: 0.2, 0.75, etc.)  

**v1.17.1 - 2.3.0**  
* Ported to run on 1.17.+  
* Updated to official mappings  
* Updated to Gradle 7.1  
* Updated to ForgeGradle 5.1  
* Requires Bibliotheca 1.7.1+   
* Built inn support for deepslate variants  
* Added copper, deepslate and tuff to default list  
* Updated templates