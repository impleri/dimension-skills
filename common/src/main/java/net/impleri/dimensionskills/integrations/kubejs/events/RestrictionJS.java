package net.impleri.dimensionskills.integrations.kubejs.events;

import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.impleri.dimensionskills.DimensionHelper;
import net.impleri.dimensionskills.DimensionSkills;
import net.impleri.dimensionskills.restrictions.Restriction;
import net.impleri.playerskills.restrictions.AbstractRestrictionBuilder;
import net.impleri.playerskills.utils.SkillResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class RestrictionJS extends Restriction {
    private static final ResourceKey<Registry<Restriction>> key = ResourceKey.createRegistryKey(SkillResourceLocation.of("dimension_restriction_builders_registry"));

    public static final RegistryObjectBuilderTypes<Restriction> registry = RegistryObjectBuilderTypes.add(key, Restriction.class);

    public RestrictionJS(Level dimension, Builder builder) {
        super(
                dimension,
                builder.condition,
                builder.accessible,
                builder.sources,
                builder.replacement
        );
    }

    public static class Builder extends AbstractRestrictionBuilder<Restriction> {
        public boolean accessible = true;
        public List<ResourceLocation> sources = new ArrayList<>();
        public Level replacement;

        private final MinecraftServer server;

        @HideFromJS
        public Builder(ResourceLocation id, MinecraftServer server) {
            super(id);
            this.server = server;
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

        public Builder from(String origin) {
            var name = SkillResourceLocation.ofMinecraft(origin);

            this.sources.add(name);

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
