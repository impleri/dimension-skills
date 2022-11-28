package net.impleri.dimensionskills.integrations.kubejs.events;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.Extra;

public abstract class DimensionEventsBinding {
    public static final EventGroup GROUP = EventGroup.of("DimensionEvents");

    public static final EventHandler TELEPORT = GROUP.server("onTeleport", () -> OnTeleportEventJS.class).extra(Extra.ID).cancelable();
}
