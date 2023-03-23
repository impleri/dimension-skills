package net.impleri.dimensionskills;

import net.impleri.dimensionskills.restrictions.Restriction;
import net.impleri.playerskills.restrictions.Registry;
import net.impleri.playerskills.utils.PlayerSkillsLogger;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class DimensionSkills {
    public static final String MOD_ID = "dimensionskills";
    public static final PlayerSkillsLogger LOGGER = PlayerSkillsLogger.create(MOD_ID, "DIMS");

    public static Registry<Restriction> RESTRICTIONS = new Registry<>(MOD_ID);

    private static final DimensionEvents INSTANCE = new DimensionEvents();

    public static void init() {
        INSTANCE.registerEventHandlers();
        INSTANCE.registerCommands();
        LOGGER.info("Loaded Dimension Skills");
    }

    public static boolean toggleDebug() {
        return LOGGER.toggleDebug();
    }

    public static ChangeDimensionResult onChangeDimension(ServerPlayer player, ServerLevel level) {
        return INSTANCE.onChangeDimension(player, level);
    }

    public static ChangeDimensionResult onChangeDimension(ServerPlayer player, ResourceKey<Level> level) {
        return INSTANCE.onChangeDimension(player, level);
    }
}
