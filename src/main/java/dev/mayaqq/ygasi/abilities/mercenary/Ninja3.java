package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Ninja3 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/ninja3")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("ygasi", "mercenary/ninja3"), "unlocked_ninja3");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/ninja3")) {
            AdvUtils.revokeAllAdvancements(player, "ygasi", "mercenary/ninja3");
        }
    }
}
