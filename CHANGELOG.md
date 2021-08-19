**v1.16.5 - 2.4.0**

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
* Added new config options to regenerate default data / templates  
* Revamped template system to work with the new v2 data structure  
* Removed reload command due to CODEC requiring a full restart  
* Fixed bug where duplicated names for features - Thanks to lynnpye  
* Tons of backend changes  
  

**v1.16.5 - 2.3.1**  
* Implemented spawnRate below 1 (Example: 0.2, 0.75, etc.)  
  
**v1.16.5 - 2.3.0**  
* Ported to run on 1.17.+  
* Updated to official mappings  
* Updated to Gradle 7.1  
* Updated to ForgeGradle 5.1  
* Requires Bibliotheca 1.7.1+  
* Built inn support for deepslate variants  
* Added copper, deepslate and tuff to default list  
* Updated templates  

**v1.16.5 - 2.2.1**  
* CurseGradle Test build  
* Minor changes  

**v1.16.5 - 2.2.0**  
* Added automated security scan of jar file and build scripts  
* Added Ore Tweaker Wiki : https://github.com/EwyBoy/OreTweaker/wiki  
* Updated GitHub page to my new standard  
* Fixed [#20](https://github.com/EwyBoy/OreTweaker/issues/20) Emerald ores don't spawn.  
* Fixed wrong emerald values in templates  
* Bumped to the latest forge version  
* Bumped to gradle 6.9  
* Builds are now pushed using [CurseGradle](https://github.com/matthewprenger/CurseGradle)  
* Minor changes under the hood  
* Started working on a new system to do more advanced tweaks and make the mod less hacky  

**v1.16.5 - 2.1.0**  
* There was a bug in the code causing tons of world gen features to be deleted from the world.  
* This is a huge fix for Ore Tweaker and will fix the ore generation incompatibility with both vanilla Minecraft and other mods world gen  
* Properly Fixes [#9](https://github.com/EwyBoy/OreTweaker/issues/9) Create Ore Generation Incompatibility - by The-Math-Fish  
* Properly Fixes [#10](https://github.com/EwyBoy/OreTweaker/issues/10) No sand, gravel, and clay in river - by frank89722  
* Properly Fixes [#11](https://github.com/EwyBoy/OreTweaker/issues/11) Conflict with zycraft's zychorium ore-gen - by frank89722  

**v1.16.5 - 2.0.2**  
* Added commands  
* /oretweaker reload - reloads JSON file

**v1.16.5 - 2.0.1**  
* Fixes [#10](https://github.com/EwyBoy/OreTweaker/issues/10) No sand, gravel, and clay in river - by frank89722

**v1.16.5 - 2.0.0**  
* Initial release and port
