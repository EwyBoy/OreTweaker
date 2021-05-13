package com.ewyboy.oretweaker.commands;

import com.ewyboy.oretweaker.OreTweaker;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;

public class CommandCenter {

    public CommandCenter(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSource> literal(OreTweaker.MOD_ID)
                        .then(CommandReloadJSON.register())
                        .executes(ctx -> 0)
        );
    }
}
