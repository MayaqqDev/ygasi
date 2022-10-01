package dev.mayaqq.ygasi.mixin;

import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.mayaqq.ygasi.StatRegistry.SKILL_POINTS;
import static dev.mayaqq.ygasi.ygasi.LOGGER;

@Mixin(AdvancementRewards.class)
public class AdvancementRewardsMixin {
    @Shadow @Final private Identifier[] recipes;

    @Inject(method = "apply",at = @At("HEAD"))
    private void inject(ServerPlayerEntity player, CallbackInfo ci) {
        if (recipes.length == 0) {
            LOGGER.info("Skill point unlocked");
            player.increaseStat(SKILL_POINTS, 1);
        }
    }
}
