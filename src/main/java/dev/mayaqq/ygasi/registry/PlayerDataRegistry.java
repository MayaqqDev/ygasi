package dev.mayaqq.ygasi.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.*;

public class PlayerDataRegistry {
    public static PlayerData PLAYERDATA = new PlayerData();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void load(UUID uuid) {
        File playerDatFile = new File(FabricLoader.getInstance().getConfigDir().toFile() + "/ygasi/players/" + uuid + ".json");
        if (!playerDatFile.exists()) {
            try {
                playerDatFile.createNewFile();
                save(uuid);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                PLAYERDATA = gson.fromJson(new FileReader(playerDatFile), PlayerData.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save(UUID uuid) {
        try {
            File playerDatFile = new File(FabricLoader.getInstance().getConfigDir().toFile() + "/ygasi/players/" + uuid + ".json");
            var writer = new FileWriter(playerDatFile);
            writer.write(gson.toJson(PLAYERDATA));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class PlayerData {
        @SerializedName("branches")
        public Map<String, Boolean> branches = new HashMap<>();
        @SerializedName("skills")
        public Map<String, Integer> skills = new HashMap<>();
        public PlayerData() {}
    }
}