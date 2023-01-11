package dev.mayaqq.ygasi.dataGen.recipes;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Consumer;

import static dev.mayaqq.ygasi.registry.ItemRegistry.SKILL_BOOK;

public class YgasiRecipesProvider extends FabricRecipeProvider {
    public YgasiRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, SKILL_BOOK)
                .criterion("has_book", new ImpossibleCriterion.Conditions())
                .input(Items.BOOK)
                .input(ItemTags.SAPLINGS)
                .offerTo(exporter);
    }
}