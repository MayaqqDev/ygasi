package dev.mayaqq.ygasi.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class GetAdvancementProgress {
    public static boolean get(ServerPlayerEntity player, String advancement) {
        return player.getAdvancementTracker().getProgress(player.getServer().getAdvancementLoader().get(new Identifier("minecraft", "ygasi/" + advancement))).isDone();
    }
}
