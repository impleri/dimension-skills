package net.impleri.dimensionskills;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.Platform;
import net.impleri.dimensionskills.commands.DimensionSkillsCommands;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DimensionEvents {
    public void registerEventHandlers() {
        LifecycleEvent.SERVER_STARTING.register(this::onStartup);
    }

    public void registerCommands() {
        CommandRegistrationEvent.EVENT.register(DimensionSkillsCommands::register);
    }

    private void onStartup(MinecraftServer minecraftServer) {
        if (Platform.isModLoaded("kubejs")) {
            net.impleri.dimensionskills.integrations.kubejs.DimensionSkillsPlugin.onStartup(minecraftServer);
        }
    }

    public ChangeDimensionResult onChangeDimension(ServerPlayer player, @Nullable ServerLevel serverLevel) {
        if (serverLevel != null) {
            var destination = DimensionHelper.getDestination(player, serverLevel);

            if (destination == null) {
                DimensionSkills.LOGGER.info("Blocking travel to {} for {}", DimensionHelper.getDimensionName(serverLevel), player.getName().getString());

                return new ChangeDimensionResult(DimensionResult.DENY, null);
            }

            if (!DimensionHelper.isSameDimension(destination, serverLevel)) {
                DimensionSkills.LOGGER.info("Rerouting travel to {} for {} to {}", DimensionHelper.getDimensionName(serverLevel), player.getName().getString(), DimensionHelper.getDimensionName(destination));
                return new ChangeDimensionResult(DimensionResult.CHANGE, DimensionHelper.getDimension(destination, Objects.requireNonNull(player.getServer())));
            }
        }

        return new ChangeDimensionResult(DimensionResult.ALLOW, serverLevel);
    }

    public ChangeDimensionResult onChangeDimension(ServerPlayer player, ResourceKey<Level> levelResourceKey) {
        var server = player.getServer();

        if (server == null) {
            return new ChangeDimensionResult(DimensionResult.ALLOW, null);
        }

        var level = DimensionHelper.getDimension(levelResourceKey.location(), player.getServer());

        return onChangeDimension(player, level);

    }
}
