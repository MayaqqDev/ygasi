package dev.mayaqq.ygasi.mixin;

import dev.mayaqq.ygasi.util.AdvUtils;
import dev.mayaqq.ygasi.util.Multithreading;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.TimeUnit;

import static dev.mayaqq.ygasi.Ygasi.LOGGER;
import static dev.mayaqq.ygasi.abilities.mercenary.Offence1.attackList;

@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {

    @Shadow public abstract Text getName();

    @Inject(method = "onRemoved", at = @At("HEAD"))
    public void onUpdateStatusEffect(LivingEntity entity, AttributeContainer attributes, int amplifier, CallbackInfo ci) {
        LOGGER.info("onRemovedTick");
        if (entity.isPlayer()) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            if (this.getName() != null && this.getName().equals(StatusEffects.STRENGTH.getName()) && AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
                Multithreading.schedule(() -> {
                    attackList.entrySet().removeIf(entry -> entry.getKey().startsWith(player.getUuidAsString()));
                    },4, TimeUnit.SECONDS);
            }
        }
    }
}
