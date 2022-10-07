package dev.mayaqq.ygasi.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.mayaqq.ygasi.config.ConfigData;
import dev.mayaqq.ygasi.config.ModData;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import static dev.mayaqq.ygasi.ygasi.LOGGER;

public class ConfigRegistry {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static ConfigData CONFIG;
    private static ModData DATA;

    public static ConfigData getConfig() {
        return CONFIG;
    }

    public static boolean loadConfig() {
        boolean success = false;
        Charset charSet = StandardCharsets.UTF_8;

        CONFIG = null;
        try {
            File configDir = Paths.get("", "config", "ygasi").toFile();
            File dataDir = Paths.get("", "config", "ygasi", "data").toFile();
            dataDir.mkdirs();
            File configFile = new File(configDir, "config.json");
            File dataFile = new File(dataDir, "data.json");

            ConfigData configData = configFile.exists() ? GSON.fromJson(new InputStreamReader(new FileInputStream(configFile), charSet), ConfigData.class) : new ConfigData();
            ModData modData = dataFile.exists() ? GSON.fromJson(new InputStreamReader(new FileInputStream(dataFile), charSet), ModData.class) : new ModData();

            CONFIG = new ConfigData();

            BufferedWriter configWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), charSet));
            BufferedWriter dataWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile), charSet));
            dataWriter.write(GSON.toJson(modData));
            configWriter.write(GSON.toJson(configData));
            configWriter.close();
            dataWriter.close();
        } catch (IOException e ) {
            LOGGER.error(String.valueOf(e));
        }
        return success;
    }

}
