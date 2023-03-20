package net.impleri.dimensionskills.fabric.mixins;

import net.impleri.dimensionskills.DimensionSkills;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Shadow
    @Nullable
    public abstract Entity changeDimension(ServerLevel serverLevel);

    @Inject(method = "changeDimension", cancellable = true, at = @At(value = "HEAD"))
    private void onChangeDimension(ServerLevel serverLevel, CallbackInfoReturnable<Entity> cir) {
        var player = (ServerPlayer) (Object) this;
        var changeResult = DimensionSkills.onChangeDimension(player, serverLevel);
        var result = changeResult.result();

        switch (result) {
            case DENY -> cir.cancel();
            case CHANGE -> cir.setReturnValue(changeDimension(changeResult.dimension()));
            // case ALLOW -> DO NOTHING
        }
    }
}
