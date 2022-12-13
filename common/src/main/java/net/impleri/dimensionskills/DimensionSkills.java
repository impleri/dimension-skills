package net.impleri.dimensionskills;

import net.impleri.dimensionskills.integrations.kubejs.DimensionSkillsPlugin;
import net.impleri.playerskills.utils.PlayerSkillsLogger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class DimensionSkills {
    public static final String MOD_ID = "dimensionskills";
    public static final PlayerSkillsLogger LOGGER = PlayerSkillsLogger.create(MOD_ID, "PS-DIM");

    public static void init() {
        LOGGER.info("Loaded Dimension Skills");
    }

    public static boolean shouldDeny(Player player, ResourceLocation destination) {
        return DimensionSkillsPlugin.handleTeleport(player, destination);
    }
}
