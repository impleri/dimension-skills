package net.impleri.dimensionskills.mixins;

import net.impleri.dimensionskills.DimensionSkills;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Inject(method = "changeDimension", cancellable = true, at = @At(value = "HEAD"))
    private void onChangeDimension(ServerLevel serverLevel, CallbackInfoReturnable<Entity> cir) {
        var player = (ServerPlayer) (Object) this;
        var cancelled = DimensionSkills.shouldDeny(player, serverLevel.dimension().location());
        DimensionSkills.LOGGER.info("Should travel to {} be canceled for {}? {}", serverLevel.dimension().location(), player.getName().getString(), cancelled);
        if (cancelled) {
            cir.setReturnValue(player);
        }
    }
}
