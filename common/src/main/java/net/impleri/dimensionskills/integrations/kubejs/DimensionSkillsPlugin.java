package net.impleri.dimensionskills.integrations.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.impleri.dimensionskills.integrations.kubejs.events.EventsBinding;
import net.impleri.dimensionskills.integrations.kubejs.events.RestrictionsRegistrationEventJS;
import net.minecraft.server.MinecraftServer;

public class DimensionSkillsPlugin extends KubeJSPlugin {
    public static void onStartup(MinecraftServer minecraftServer) {
        new RestrictionsRegistrationEventJS(minecraftServer).post(ScriptType.SERVER, EventsBinding.RESTRICTIONS);
    }
}
