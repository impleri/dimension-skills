package net.impleri.dimensionskills.forge;

import net.impleri.dimensionskills.DimensionSkills;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber()
public class TeleportEventHandler {
    @SubscribeEvent
    public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
        var teleported = event.getEntity();
        if (teleported instanceof ServerPlayer player) {
            var changeResult = DimensionSkills.onChangeDimension(player, event.getDimension());

            switch (changeResult.result()) {
                case ALLOW -> event.setCanceled(false);
                case DENY -> event.setCanceled(true);
                case CHANGE -> {
                    event.setCanceled(true);
                    player.changeDimension(Objects.requireNonNull(changeResult.dimension()), player.getLevel().getPortalForcer());
                }
            }
        }
    }
}
