package dev.mayaqq.ygasi.abilities.mercenary;

import dev.mayaqq.ygasi.util.AdvUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class Offence1 {

    public static Map<String, Boolean> attackList = new HashMap<>();

    public static void give(ServerPlayerEntity player) {
        if (!AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
            AdvUtils.grantAdvancementCriterion(player, new Identifier("minecraft", "ygasi/offence1"), "unlocked_offence1");
        }
    }
    public static void revoke(ServerPlayerEntity player) {
        if (AdvUtils.getAdvancementProgress(player, "minecraft", "ygasi/offence1")) {
            AdvUtils.revokeAllAdvancements(player, "minecraft", "ygasi/offence1");
        }
    }
}