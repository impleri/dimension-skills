package net.impleri.dimensionskills.integrations.kubejs.events;

import dev.latvian.mods.kubejs.server.ServerEventJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class OnTeleportEventJS extends ServerEventJS {
    private final Player player;
    private final ResourceLocation destination;

    public OnTeleportEventJS(Player player, ResourceLocation destination) {
        super(UtilsJS.staticServer);
        this.player = player;
        this.destination = destination;
    }

    public Player getEntity() {
        return getPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    public ResourceLocation getDestination() {
        return destination;
    }

    public void deny() {
        cancel();
    }
}
