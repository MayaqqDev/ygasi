package dev.mayaqq.ygasi.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ConfigRegistry {

    public static Config CONFIG = new Config();

    static File modConfFolder = new File(FabricLoader.getInstance().getConfigDir().toFile(),"ygasi");
    private static final File configFile = new File(modConfFolder,"config.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        //we do bunch of checking here mainly if the file exists
        if (!modConfFolder.exists()) {
            modConfFolder.mkdir();
        }
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                CONFIG = gson.fromJson(new FileReader(configFile), Config.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter(configFile);
            gson.toJson(CONFIG, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Config {
        //the thing to write in the config file
        public int pointsRewarded = 1;
        public int branchCost = 16;
        public Boolean enableSkillBook = true;
        public int resetCost = 10;
        public int T1Cost = 5;
        public int T2Cost = 10;
        public int T3Cost = 15;

        public Config() {}
    }
}
