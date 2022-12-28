package dev.mayaqq.ygasi.util;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static dev.mayaqq.ygasi.Ygasi.LOGGER;

public class YgasiUtils {
    public static void grantAdvancementCriterion(@NotNull ServerPlayerEntity serverPlayerEntity, Identifier advancementIdentifier, String criterion) {
        if (serverPlayerEntity.getServer() == null) {
            return;
        }
        ServerAdvancementLoader sal = serverPlayerEntity.getServer().getAdvancementLoader();
        PlayerAdvancementTracker tracker = serverPlayerEntity.getAdvancementTracker();

        Advancement advancement = sal.get(advancementIdentifier);
        if (advancement == null) {
            LOGGER.error("Trying to grant a criterion \"" + criterion + "\" for an advancement that does not exist: " + advancementIdentifier);
        } else {
            if (!tracker.getProgress(advancement).isDone()) {
                tracker.grantCriterion(advancement, criterion);
            }
        }
    }

    public static boolean getAdvancementProgress(ServerPlayerEntity player, String advancement) {
        return player.getAdvancementTracker().getProgress(player.getServer().getAdvancementLoader().get(new Identifier("minecraft", "ygasi/" + advancement))).isDone();
    }
}