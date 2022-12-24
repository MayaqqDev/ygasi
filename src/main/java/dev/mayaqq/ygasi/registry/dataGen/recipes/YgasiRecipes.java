package dev.mayaqq.ygasi.registry.dataGen.recipes;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;

import java.util.function.Consumer;

import static dev.mayaqq.ygasi.Ygasi.SKILL_BOOK;

public class YgasiRecipes extends FabricRecipeProvider {
    public YgasiRecipes(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(SKILL_BOOK)
                .criterion("has_book", new ImpossibleCriterion.Conditions())
                .input(Items.BOOK)
                .input(ItemTags.SAPLINGS)
                .offerTo(exporter);
    }
}