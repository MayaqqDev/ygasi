package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Defence2 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/defence2")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("ygasi", "mercenary/defence2"), "unlocked_defence2");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "ygasi", "mercenary/defence2")) {
            AdvUtils.revokeAllAdvancements(player, "ygasi", "mercenary/defence2");
        }
    }
}
