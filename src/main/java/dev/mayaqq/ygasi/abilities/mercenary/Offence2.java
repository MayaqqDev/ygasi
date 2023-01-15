package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Offence2 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence2")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/offence2"), "unlocked_offence2");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence2")) {
            AdvUtils.revokeAllAdvancements(player, "minecraft", "ygasi/offence2");
        }
    }
}
