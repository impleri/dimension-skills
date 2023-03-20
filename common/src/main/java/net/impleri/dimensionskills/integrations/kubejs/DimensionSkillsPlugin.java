package net.impleri.dimensionskills.integrations.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import net.impleri.dimensionskills.integrations.kubejs.events.EventsBinding;
import net.impleri.dimensionskills.integrations.kubejs.events.RestrictionsRegistrationEventJS;
import net.minecraft.server.MinecraftServer;

public class DimensionSkillsPlugin extends KubeJSPlugin {
    @Override
    public void registerEvents() {
        EventsBinding.GROUP.register();
    }

    public static void onStartup(MinecraftServer minecraftServer) {
        EventsBinding.RESTRICTIONS.post(new RestrictionsRegistrationEventJS(minecraftServer));
    }
}
