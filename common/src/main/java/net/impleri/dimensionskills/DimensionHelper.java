package net.impleri.dimensionskills;

import net.impleri.dimensionskills.restrictions.Restrictions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DimensionHelper {
    public static ResourceLocation getDimensionName(Level level) {
        return level.dimension().location();
    }

    public static ServerLevel getDimension(ResourceLocation resource, MinecraftServer server) {
        return server.getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, resource));
    }

    public static ServerLevel getDimension(Level level, MinecraftServer server) {
        return server.getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, getDimensionName(level)));
    }

    public static boolean hasDimension(ResourceLocation resource, MinecraftServer server) {
        return getDimension(resource, server) != null;
    }

    @Nullable
    public static Level getReplacementFor(Player player, Level destination) {
        return Restrictions.INSTANCE.getReplacementFor(player, destination, player.getLevel());
    }

    @Nullable
    public static Level getDestination(ServerPlayer player, ServerLevel level) {
        return getReplacementFor(player, level);
    }

    public static boolean isSameDimension(Level a, Level b) {
        return a != null && b != null && getDimensionName(a).equals(getDimensionName(b));
    }

    public static boolean isSameDimension(Level a, ResourceLocation b) {
        return getDimensionName(a).equals(b);
    }
}
