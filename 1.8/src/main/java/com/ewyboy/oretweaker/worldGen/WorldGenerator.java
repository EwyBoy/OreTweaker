package com.ewyboy.oretweaker.worldGen;

import com.ewyboy.oretweaker.utility.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import static com.ewyboy.oretweaker.loaders.ConfigLoader.*;

public class WorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
            case 0 : generateOres(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private Block[] blocks = {Blocks.coal_ore, Blocks.iron_ore, Blocks.gold_ore, Blocks.diamond_ore, Blocks.redstone_ore, Blocks.emerald_ore, Blocks.dirt, Blocks.gravel, Blocks.stone.getStateFromMeta(1).getBlock(), Blocks.stone.getStateFromMeta(3).getBlock(), Blocks.stone.getStateFromMeta(5).getBlock()};

    private void generateOres(World world, Random random, int x, int z) {
        for (int ore = 0; ore < Reference.OreNames.OreNames.length; ore++) {
            if (disableOres[ore] && enableCustomOreGeneration[ore]) {
                for (int i = 0; i < spawnRates[ore]; i++) {
                    int veinYLevel = (int) (Math.random() * (maxVeinLevels[ore] - minVeinLevels[ore])) + minVeinLevels[ore];
                    int veinSize = (int) (Math.random() * (maxVeinSizes[ore] - minVeinSizes[ore]) + minVeinSizes[i]);

                    int xCoord = x + random.nextInt(16);
                    int zCoord = z + random.nextInt(16);

                    new WorldGenMinable(blocks[ore].getDefaultState(), veinSize).generate(world, random, new BlockPos(xCoord, veinYLevel, zCoord));
                }
            }
        }
    }
}
