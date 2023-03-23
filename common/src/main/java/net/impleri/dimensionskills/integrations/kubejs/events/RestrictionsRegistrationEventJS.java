package net.impleri.dimensionskills.integrations.kubejs.events;

import dev.latvian.mods.kubejs.server.ServerEventJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.impleri.dimensionskills.DimensionHelper;
import net.impleri.dimensionskills.DimensionSkills;
import net.impleri.playerskills.utils.RegistrationType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RestrictionsRegistrationEventJS extends ServerEventJS {
    protected final MinecraftServer server;

    public RestrictionsRegistrationEventJS(MinecraftServer server) {
        this.server = server;
    }

    public void restrict(String dimensionName, @NotNull Consumer<RestrictionJS.Builder> consumer) {
        RegistrationType<Level> registrationType = new RegistrationType<>(dimensionName, Registry.DIMENSION_REGISTRY);

        registrationType.ifNamespace(namespace -> restrictNamespace(namespace, consumer));
        registrationType.ifName(name -> restrictDimension(name, consumer));
        registrationType.ifTag(tag -> restrictTag());
    }

    private void restrictTag() {
        ConsoleJS.SERVER.error("Cannot identify dimensions by tags");
    }

    private void restrictDimension(ResourceLocation name, @NotNull Consumer<RestrictionJS.Builder> consumer) {
        if (!DimensionHelper.hasDimension(name, server)) {
            ConsoleJS.SERVER.warn("Could not find any dimension named " + name);
            return;
        }

        var builder = new RestrictionJS.Builder(name, server);
        consumer.accept(builder);

        var dimension = DimensionHelper.getDimension(name, server);
        var restriction = builder.createObject(dimension);

        ConsoleJS.SERVER.info("Created dimension restriction for " + name);
        DimensionSkills.RESTRICTIONS.add(name, restriction);
    }

    private void restrictNamespace(String namespace, Consumer<RestrictionJS.Builder> consumer) {
        server.levelKeys().stream()
                .map(ResourceKey::location)
                .filter(location -> location.getNamespace().equals(namespace))
                .forEach(location -> restrictDimension(location, consumer));
    }
}
