package dev.mayaqq.ygasi.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ConfigRegistry {

    public static Config CONFIG = new Config();
    public static ServerData SERVERDATA = new ServerData();

    private static File modConfFolder = new File(FabricLoader.getInstance().getConfigDir().toFile(),"ygasi");
    private static File configFile = new File(modConfFolder,"config.json");
    private static File serverDatFile = new File(modConfFolder,"serverDat.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        //we do bunch of checking here mainly if the file exists
        if (!modConfFolder.exists()) {
            modConfFolder.mkdir();
        }

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                saveConfig();
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
        if (!serverDatFile.exists()) {
            try {
                serverDatFile.createNewFile();
                saveServerData();
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

    public static void saveConfig() throws IOException {
        //Write some info into the file under here
        var writer = new FileWriter(configFile);
        writer.write(gson.toJson(CONFIG));
        writer.close();
    }
    public static void saveServerData() throws IOException {
        var Writer = new FileWriter(serverDatFile);
        Writer.write(gson.toJson(SERVERDATA));
        Writer.close();
    }
    public static class Config {
        //the thing to write in the config file
        public int pointsRewarded = 1;
        public Config() {}
    }
    public static class ServerData {
        //the things to write in the serverData file, this is only temporary for now
        public String Comment = "This File should store all the data for the players when they open the gui. Maybe this will get removed when I learn SQL? (never)";
        public ServerData() {}
    }
}
