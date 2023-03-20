package net.impleri.dimensionskills.restrictions;

import net.impleri.playerskills.restrictions.AbstractRestriction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Restriction extends AbstractRestriction<Level> {
    public final boolean accessible;
    public final List<ResourceLocation> sources;

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @Nullable Boolean accessible,
            @Nullable List<ResourceLocation> sources,
            @NotNull Level replacement
    ) {
        super(target, condition, replacement);
        this.accessible = Boolean.TRUE.equals(accessible);
        this.sources = sources == null ? new ArrayList<>() : sources;
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @Nullable Boolean accessible,
            @NotNull Level replacement
    ) {
        this(target, condition, accessible, null, replacement);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @NotNull Level replacement
    ) {
        this(target, condition, null, replacement);
    }

    public Restriction(
            @NotNull Level target,
            @NotNull Level replacement
    ) {
        this(target, null, replacement);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @Nullable Boolean accessible,
            @Nullable List<ResourceLocation> sources
    ) {
        this(target, condition, accessible, sources, null);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @Nullable List<ResourceLocation> sources
    ) {
        this(target, condition, null, sources);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition,
            @Nullable Boolean accessible
    ) {
        this(target, condition, accessible, null, null);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Boolean accessible,
            @Nullable List<ResourceLocation> sources
    ) {
        this(target, null, accessible, sources, null);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Predicate<Player> condition
    ) {
        this(target, condition, null, null, null);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable Boolean accessible
    ) {
        this(target, null, accessible, null, null);
    }

    public Restriction(
            @NotNull Level target,
            @Nullable List<ResourceLocation> sources
    ) {
        this(target, null, null, sources, null);
    }
}
