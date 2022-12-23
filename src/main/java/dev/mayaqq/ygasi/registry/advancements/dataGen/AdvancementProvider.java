package dev.mayaqq.ygasi.registry.advancements.dataGen;

import dev.mayaqq.ygasi.registry.advancements.Advancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {
    protected AdvancementProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        new Advancements().accept(consumer);
    }
}
