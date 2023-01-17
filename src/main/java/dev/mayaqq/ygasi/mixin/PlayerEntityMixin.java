package dev.mayaqq.ygasi.mixin;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.mayaqq.ygasi.abilities.mercenary.Offence1.attackList;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow public abstract boolean isPlayer();

    @Inject(method = "attack", at = @At("HEAD"))
    public void onAttack(Entity target, CallbackInfo ci) {
        if (this.isPlayer()) {
            ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
            if (!attackList.containsKey(player.getUuidAsString() + target.getUuidAsString()) && AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
                attackList.put(player.getUuidAsString() + target.getUuidAsString(), true);
                StatusEffectInstance currentEffect = player.getStatusEffect(StatusEffects.STRENGTH);
                int currentLevel = currentEffect != null ? currentEffect.getAmplifier() : 0;
                int currentTime = currentEffect != null ? currentEffect.getDuration() : 0;
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, currentTime + 2 * 20, currentLevel, false, false, true));
            }
        }
    }
}