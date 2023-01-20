package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Defence3 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/defence3")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("ygasi", "mercenary/defence3"), "unlocked_defence3");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/defence3")) {
            AdvUtils.revokeAllAdvancements(player, "ygasi", "mercenary/defence3");
        }
    }
}
