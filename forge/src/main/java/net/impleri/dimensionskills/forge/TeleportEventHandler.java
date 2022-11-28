package net.impleri.dimensionskills.forge;

import net.impleri.dimensionskills.DimensionSkills;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class TeleportEventHandler {
    @SubscribeEvent
    public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
        var teleported = event.getEntity();
        if (teleported instanceof Player player) {
            var cancelled = DimensionSkills.shouldDeny(player, event.getDimension().location());
            DimensionSkills.LOGGER.info("Should travel to {} be canceled for {}? {}", event.getDimension().location(), player.getName().getString(), cancelled);
            event.setCanceled(cancelled);
        }
    }
}
