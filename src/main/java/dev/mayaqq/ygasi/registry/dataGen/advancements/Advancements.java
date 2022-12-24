package dev.mayaqq.ygasi.registry.dataGen.advancements;

import de.dafuqs.revelationary.advancement_criteria.AdvancementGottenCriterion;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(
                        Items.DIAMOND, // The display icon
                        Text.literal("You've got a skill issue!"), // The title
                        Text.literal("The journey begins here!"), // The description
                        new Identifier("textures/block/sculk_catalyst_top.png"), // Background image used
                        AdvancementFrame.CHALLENGE, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        false, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("opened_skill_menu", new ImpossibleCriterion.Conditions())
                .rewards(AdvancementRewards.Builder.experience(1))
                .build(consumer, "ygasi" + "/root");

        Advancement mercenary = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        Items.IRON_SWORD,
                        Text.literal("Mercenary"),
                        Text.literal("Unlock the mercenary branch!"),
                        null,
                        AdvancementFrame.GOAL,
                        false,
                        false,
                        false
                )
                .criterion("unlocked_mercenary", new ImpossibleCriterion.Conditions())
                .criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1))
                .build(consumer, "ygasi" + "/mercenary");
        Advancement wizardry = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        Items.BLAZE_ROD,
                        Text.literal("Wizardry"),
                        Text.literal("Unlock the wizardry branch!"),
                        null,
                        AdvancementFrame.GOAL,
                        false,
                        false,
                        false
                )
                .criterion("unlocked_wizardry", new ImpossibleCriterion.Conditions())
                .criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1))
                .build(consumer, "ygasi" + "/wizardry");
        Advancement druidry = Advancement.Builder.create().parent(rootAdvancement)
                .display(
                        Items.OAK_SAPLING,
                        Text.literal("Druidry"),
                        Text.literal("Unlock the druidry branch!"),
                        null,
                        AdvancementFrame.GOAL,
                        false,
                        false,
                        false
                )
                .criterion("unlocked_druidry", new ImpossibleCriterion.Conditions())
                .criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1))
                .build(consumer, "ygasi" + "/druidry");
    }
}