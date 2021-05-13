package com.ewyboy.oretweaker.commands;

import com.ewyboy.oretweaker.json.JSONHandler;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class CommandReloadJSON {

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("reload").requires((commandSource) -> commandSource.hasPermission(2))
                .executes((commandSource) -> reload(
                        commandSource.getSource()
                        )
                );
    }

    private static int reload(CommandSource source) {
        try {
            JSONHandler.reload();
            source.sendSuccess(new StringTextComponent(TextFormatting.GREEN + "SUCCESS: " + TextFormatting.WHITE + "Config reloaded"), true);
        } catch (Exception e) {
            e.printStackTrace();
            source.sendSuccess(new StringTextComponent(TextFormatting.RED + "ERROR: " + TextFormatting.WHITE + "Config failed to reload"), true);
        }
        return 0;
    }

}
