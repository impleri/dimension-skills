package net.impleri.dimensionskills.restrictions;

import net.impleri.playerskills.restrictions.AbstractRestriction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Restriction extends AbstractRestriction<Level> {
    public final boolean accessible;

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @Nullable Boolean accessible,
            @Nullable List<ResourceLocation> includeDimensions,
            @Nullable List<ResourceLocation> excludeDimensions,
            @Nullable List<ResourceLocation> includeBiomes,
            @Nullable List<ResourceLocation> excludeBiomes,
            @Nullable Level replacement
    ) {
        super(target, condition, includeDimensions, excludeDimensions, includeBiomes, excludeBiomes, replacement);

        this.accessible = Boolean.TRUE.equals(accessible);
    }
}
