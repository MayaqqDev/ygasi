package dev.mayaqq.ygasi.mixin;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.mayaqq.ygasi.registry.StatRegistry.SKILL_POINTS;


@Mixin(AdvancementRewards.class)
public class AdvancementRewardsMixin {

    @Shadow @Final private Identifier[] recipes;
    @Shadow @Final private int experience;

    @Inject(method = "apply", at = @At("HEAD"))
    private void apply(ServerPlayerEntity player, CallbackInfo ci) {
        if (recipes.length == 0 && experience != 1) {
            player.increaseStat(SKILL_POINTS, ConfigRegistry.CONFIG.pointsRewarded);
        } else if (experience == 1) {
            player.addExperience(-1);
        }
    }
}
