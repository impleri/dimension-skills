package net.impleri.dimensionskills;

import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record ChangeDimensionResult(@NotNull DimensionResult result, @Nullable ServerLevel dimension) {
}
