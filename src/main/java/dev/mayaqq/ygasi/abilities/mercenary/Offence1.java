package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.registry.ConfigRegistry;
import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Offence1 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getBaseValue() + ConfigRegistry.CONFIG.offence1DefIncrease);
            AdvUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/offence1"), "unlocked_offence1");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
            player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getBaseValue() - ConfigRegistry.CONFIG.offence1DefIncrease);
            AdvUtils.revokeAllAdvancements(player, "minecraft", "ygasi/offence1");
        }
    }
}