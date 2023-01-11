package dev.mayaqq.ygasi.dataGen;

import dev.mayaqq.ygasi.dataGen.advancements.YgasiAdvancementProvider;
import dev.mayaqq.ygasi.dataGen.recipes.YgasiRecipesProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenRegistry implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(YgasiAdvancementProvider::new);
        pack.addProvider(YgasiRecipesProvider::new);
    }
}
