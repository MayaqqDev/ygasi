package dev.mayaqq.ygasi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreatePlayerData {
    public static UUID playerId;
    private static File serverDatFile = new File(FabricLoader.getInstance().getConfigDir().toFile() + "/ygasi","serverDat.json");
    public static ServerData SERVERDATA = new ServerData();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        if (!serverDatFile.exists()) {
        try {
            serverDatFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        } else {
            try {
                SERVERDATA = gson.fromJson(new FileReader(serverDatFile), ServerData.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void createPlayerData(UUID player) throws IOException {
        try {
            playerId = player;
            Map<UUID, Map<Integer, Map<String, Integer>>> uuidMap = new HashMap<>();
            Map<Integer, Map<String, Integer>> tree = new HashMap<>();
            Map<String, Integer> skills = new HashMap<>();
            skills.put("0", 0);
            for (int i = 1; i < 4; i++) {

            }
            var Writer = new FileWriter(serverDatFile);
            Writer.write(gson.toJson(SERVERDATA));
            Writer.close();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static class ServerData {
        UUID player = null;
        public ServerData() {}
    }
}
