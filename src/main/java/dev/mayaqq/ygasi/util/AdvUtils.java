package dev.mayaqq.ygasi.util;

import de.dafuqs.revelationary.api.advancements.AdvancementUtils;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static dev.mayaqq.ygasi.Ygasi.LOGGER;

public class AdvUtils {
    public static void grantAdvancementCriterion(ServerPlayerEntity player, Identifier advancementIdentifier, String criterion) {
        if (player.getServer() == null) {
            return;
        }
        ServerAdvancementLoader sal = player.getServer().getAdvancementLoader();
        PlayerAdvancementTracker tracker = player.getAdvancementTracker();

        Advancement advancement = sal.get(advancementIdentifier);
        if (advancement == null) {
            LOGGER.error("Trying to grant a criterion \"" + criterion + "\" for an advancement that does not exist: " + advancementIdentifier);
        } else {
            if (!tracker.getProgress(advancement).isDone()) {
                tracker.grantCriterion(advancement, criterion);
            }
        }
    }

    public static void revokeAllAdvancements(ServerPlayerEntity player, String namespace, String path) {
        if (player.getServer() == null) {
            return;
        }
        ServerAdvancementLoader sal = player.getServer().getAdvancementLoader();
        PlayerAdvancementTracker tracker = player.getAdvancementTracker();

        for (Advancement advancement : sal.getAdvancements()) {
            if (advancement.getId().getNamespace().equals(namespace) && advancement.getId().getPath().startsWith(path)) {
                for (String criterion : advancement.getCriteria().keySet()) {
                    tracker.revokeCriterion(advancement, criterion);
                }
            }
        }
    }

    public static boolean getAdvancementProgress(ServerPlayerEntity player, String namespace, String path) {
        if (player.getServer() == null) {
            return false;
        }
        return player.getAdvancementTracker().getProgress(player.getServer().getAdvancementLoader().get(new Identifier(namespace, path))).isDone();
    }

    public static boolean hasBeforeAdvancements(ServerPlayerEntity player, String namespace, String path) {
        int level = Integer.parseInt(path.substring(path.length() - 1));
        if (player.getServer() == null) {
            return false;
        }
        for (int i = 1; i < level; i++) {
            try {
                if (!getAdvancementProgress(player, namespace, path.substring(0, path.length() - 1) + i)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}