package dev.mayaqq.ygasi.dataGen;

import dev.mayaqq.ygasi.dataGen.advancements.AdvancementProvider;
import dev.mayaqq.ygasi.dataGen.recipes.YgasiRecipes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenRegistry implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        dataGenerator.addProvider(AdvancementProvider::new);
        dataGenerator.addProvider(YgasiRecipes::new);
    }
}
