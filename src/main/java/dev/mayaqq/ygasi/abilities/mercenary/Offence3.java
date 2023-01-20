package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Offence3 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/offence3")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("ygasi", "mercenary/offence3"), "unlocked_offence3");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/offence3")) {
            AdvUtils.revokeAllAdvancements(player, "ygasi", "mercenary/offence3");
        }
    }
}
