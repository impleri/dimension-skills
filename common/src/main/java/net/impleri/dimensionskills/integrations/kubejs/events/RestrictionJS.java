package net.impleri.dimensionskills.integrations.kubejs.events;

import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.impleri.dimensionskills.DimensionHelper;
import net.impleri.dimensionskills.DimensionSkills;
import net.impleri.dimensionskills.restrictions.Restriction;
import net.impleri.playerskills.integration.kubejs.api.AbstractRestrictionBuilder;
import net.impleri.playerskills.utils.SkillResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;

public class RestrictionJS extends Restriction {
    private static final ResourceKey<Registry<Restriction>> key = ResourceKey.createRegistryKey(SkillResourceLocation.of("dimension_restriction_builders_registry"));

    public static final RegistryObjectBuilderTypes<Restriction> registry = RegistryObjectBuilderTypes.add(key, Restriction.class);

    public RestrictionJS(Level dimension, Builder builder) {
        super(
                dimension,
                builder.condition,
                builder.accessible,
                builder.includeDimensions,
                builder.excludeDimensions,
                builder.includeBiomes,
                builder.excludeBiomes,
                builder.replacement
        );
    }

    public static class Builder extends AbstractRestrictionBuilder<Restriction> {
        public boolean accessible = true;
        public Level replacement;

        @HideFromJS
        public Builder(ResourceLocation id, MinecraftServer server) {
            super(id, server);
        }

        public Builder replaceWith(String replacement) {
            var name = SkillResourceLocation.ofMinecraft(replacement);
            var target = DimensionHelper.getDimension(name, server);

            if (target == null) {
                DimensionSkills.LOGGER.warn("Could not find any dimension named %s", name);
                return this;
            }

            this.replacement = target;

            return this;
        }

        public Builder accessible() {
            this.accessible = true;

            return this;
        }

        public Builder inaccessible() {
            this.accessible = false;

            return this;
        }

        public Builder nothing() {
            accessible = true;

            return this;
        }

        public Builder everything() {
            accessible = false;

            return this;
        }

        @HideFromJS
        @Override
        public RegistryObjectBuilderTypes<Restriction> getRegistryType() {
            return registry;
        }

        @HideFromJS
        @Override
        public Restriction createObject() {
            return null;
        }

        @HideFromJS
        public Restriction createObject(Level state) {
            return new RestrictionJS(state, this);
        }
    }
}
