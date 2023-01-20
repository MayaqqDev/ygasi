package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Ninja2 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/ninja2")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("ygasi", "mercenary/ninja2"), "unlocked_ninja2");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/ninja2")) {
            AdvUtils.revokeAllAdvancements(player, "ygasi", "mercenary/ninja2");
        }
    }
}
