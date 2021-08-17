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
* Added config options to regenerate template files  
* Revamped template system to work with the new v2 data structure  
* Removed reload command due to CODEC requiring a full restart  
* Tons of backend changes


**v1.17.1 - 2.3.1**
* Implemented spawnRate below 1 (Example: 0.2, 0.75, etc)  

**v1.17.1 - 2.3.0**  
* Ported to run on 1.17.+  
* Updated to official mappings  
* Updated to Gradle 7.1  
* Updated to ForgeGradle 5.1  
* Requires Bibliotheca 1.7.1+   
* Built inn support for deepslate variants  
* Added copper, deepslate and tuff to default list  
* Updated templates