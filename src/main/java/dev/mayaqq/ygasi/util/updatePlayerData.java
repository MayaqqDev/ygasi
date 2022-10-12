package dev.mayaqq.ygasi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Map;
import java.util.UUID;

import static dev.mayaqq.ygasi.ygasi.LOGGER;

public class updatePlayerData {
    public static void createPlayerData(UUID player) {
        LOGGER.info(player.toString());
    }
}
