package dev.mayaqq.ygasi.registry.dataGen.advancements;

import dev.mayaqq.ygasi.registry.dataGen.advancements.Advancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {
    public AdvancementProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        new Advancements().accept(consumer);
    }
}
