package com.ewyboy.oretweaker.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGen {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        // generator.addProvider(event.includeServer(), new);

    }

}
