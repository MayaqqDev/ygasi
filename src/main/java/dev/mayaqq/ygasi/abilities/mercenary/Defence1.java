package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Defence1 {
    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/defence1")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/defence1"), "unlocked_defence1");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/defence1")) {
            AdvUtils.revokeAllAdvancements(player, "minecraft", "ygasi/defence1");
        }
    }
}
