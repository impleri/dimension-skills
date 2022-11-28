package net.impleri.dimensionskills.integrations.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import net.impleri.dimensionskills.integrations.kubejs.events.DimensionEventsBinding;
import net.impleri.dimensionskills.integrations.kubejs.events.OnTeleportEventJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class DimensionSkillsPlugin extends KubeJSPlugin {
    @Override
    public void registerEvents() {
        DimensionEventsBinding.GROUP.register();
    }

    public static boolean handleTeleport(Player player, ResourceLocation destination) {
        return DimensionEventsBinding.TELEPORT.post(destination, new OnTeleportEventJS(player, destination));
    }
}
