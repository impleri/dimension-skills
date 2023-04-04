package net.impleri.dimensionskills.restrictions;

import net.impleri.dimensionskills.DimensionHelper;
import net.impleri.dimensionskills.DimensionSkills;
import net.impleri.playerskills.restrictions.RestrictionsApi;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.function.Predicate;

public class Restrictions extends RestrictionsApi<Level, Restriction> {
    private static final Field[] allRestrictionFields = Restriction.class.getDeclaredFields();
    public static Restrictions INSTANCE = new Restrictions(DimensionSkills.RESTRICTIONS, allRestrictionFields);

    public Restrictions(net.impleri.playerskills.restrictions.Registry<Restriction> registry, Field[] fields) {
        super(registry, fields, DimensionSkills.LOGGER);
    }

    @Override
    protected ResourceLocation getTargetName(Level target) {
        return DimensionHelper.getDimensionName(target);
    }

    @Override
    protected Predicate<Level> createPredicateFor(Level dimension) {
        var dimensionName = getTargetName(dimension);

        return (Level target) -> {
            var isSame = DimensionHelper.isSameDimension(target, dimensionName);

            DimensionSkills.LOGGER.info("Testing if restriction target {} matches {}? {}", getTargetName(target), dimensionName, isSame);

            return isSame;
        };
    }

    @Nullable
    public Level getReplacementFor(Player player, Level target, Level source) {
        var inDimension = getTargetName(source);
        var inBiome = source.getBiome(player.getOnPos()).unwrapKey().orElseThrow().location();
        var destination = getReplacementFor(player, target, inDimension, inBiome);
        var allowed = canPlayer(player, destination, inDimension, inBiome, "accessible");

        DimensionSkills.LOGGER.info(
                "{} should be travelling from {} to {} instead of {}? {}",
                player.getName().getString(),
                getTargetName(source),
                getTargetName(destination),
                getTargetName(target),
                allowed
        );

        return allowed ? destination : null;
    }
}
