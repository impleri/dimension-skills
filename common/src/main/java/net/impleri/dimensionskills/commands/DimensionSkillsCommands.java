package net.impleri.dimensionskills.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.impleri.dimensionskills.DimensionSkills;
import net.impleri.playerskills.commands.PlayerSkillsCommands;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class DimensionSkillsCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection selection) {
        PlayerSkillsCommands.registerDebug(dispatcher, "dimensions", PlayerSkillsCommands.toggleDebug("Dimension Skills", DimensionSkills::toggleDebug));
    }
}
