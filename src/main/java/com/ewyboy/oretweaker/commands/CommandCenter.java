package com.ewyboy.oretweaker.commands;

import com.ewyboy.oretweaker.OreTweaker;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;

public class CommandCenter {

    public CommandCenter(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSourceStack> literal(OreTweaker.MOD_ID)
                        .then(CommandReloadJSON.register())
                        .executes(ctx -> 0)
        );
    }
}
