package dev.mayaqq.ygasi.dataGen.advancements;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;

import java.util.function.Consumer;

public class YgasiAdvancementProvider extends FabricAdvancementProvider {
    public YgasiAdvancementProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        new YgasiAdvancements().accept(consumer);
    }
}
