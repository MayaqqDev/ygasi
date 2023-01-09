package dev.mayaqq.ygasi.dataGen.advancements;

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
        //main root advancement
        Advancement rootAdvancement = Advancement.Builder.create().display(Items.DIAMOND, // The display icon
                Text.translatable("advancements.ygasi.root.title"), // The title
                Text.translatable("advancements.ygasi.root.description"), // The description
                new Identifier("textures/block/sculk_catalyst_top.png"), // Background image used
                AdvancementFrame.CHALLENGE, // Options: TASK, CHALLENGE, GOAL
                true, // Show toast top right
                false, // Announce to chat
                false // Hidden in the advancement tab
        ).criterion("opened_skill_menu", new ImpossibleCriterion.Conditions()).rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/root");

        //branch advancements
        Advancement mercenary = Advancement.Builder.create().parent(rootAdvancement)
                .display(Items.DIAMOND_SWORD, Text.translatable("advancements.ygasi.mercenary.title"), Text.translatable("advancements.ygasi.mercenary.description"), null, AdvancementFrame.GOAL, false, false, false)
                .criterion("unlocked_mercenary", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/mercenary");
        Advancement wizardry = Advancement.Builder.create().parent(rootAdvancement)
                .display(Items.BLAZE_ROD, Text.translatable("advancements.ygasi.wizardry.title"), Text.translatable("advancements.ygasi.wizardry.description"), null, AdvancementFrame.GOAL, false, false, false)
                .criterion("unlocked_wizardry", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/wizardry");
        Advancement druidry = Advancement.Builder.create().parent(rootAdvancement)
                .display(Items.OAK_SAPLING, Text.translatable("advancements.ygasi.druidry.title"), Text.translatable("advancements.ygasi.druidry.description"), null, AdvancementFrame.GOAL, false, false, false)
                .criterion("unlocked_druidry", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/druidry");
        Advancement extra = Advancement.Builder.create().parent(rootAdvancement)
                .display(Items.EMERALD, Text.translatable("advancements.ygasi.extra.title"), Text.translatable("advancements.ygasi.extra.description"), null, AdvancementFrame.GOAL, false, false, false)
                .criterion("unlocked_extra", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, rootAdvancement.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/extra");

        //mercenary skills

        Advancement offence1 = Advancement.Builder.create().parent(mercenary)
                .display(Items.IRON_SWORD, Text.translatable("advancements.ygasi.mercenary.offence1.title"), Text.translatable("advancements.ygasi.mercenary.offence1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_offence1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, mercenary.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/offence1");
        Advancement offence2 = Advancement.Builder.create().parent(offence1)
                .display(Items.IRON_SWORD, Text.translatable("advancements.ygasi.mercenary.offence2.title"), Text.translatable("advancements.ygasi.mercenary.offence2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_offence2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, offence1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/offence2");
        Advancement offence3 = Advancement.Builder.create().parent(offence2)
                .display(Items.IRON_SWORD, Text.translatable("advancements.ygasi.mercenary.offence3.title"), Text.translatable("advancements.ygasi.mercenary.offence3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_offence3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, offence2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/offence3");

        Advancement ninja1 = Advancement.Builder.create().parent(mercenary)
                .display(Items.BAMBOO, Text.translatable("advancements.ygasi.mercenary.ninja1.title"), Text.translatable("advancements.ygasi.mercenary.ninja1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_ninja1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, mercenary.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/ninja1");
        Advancement ninja2 = Advancement.Builder.create().parent(ninja1)
                .display(Items.BAMBOO, Text.translatable("advancements.ygasi.mercenary.ninja2.title"), Text.translatable("advancements.ygasi.mercenary.ninja2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_ninja2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, ninja1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/ninja2");
        Advancement ninja3 = Advancement.Builder.create().parent(ninja2)
                .display(Items.BAMBOO, Text.translatable("advancements.ygasi.mercenary.ninja3.title"), Text.translatable("advancements.ygasi.mercenary.ninja3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_ninja3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, ninja2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/ninja3");

        Advancement defence1 = Advancement.Builder.create().parent(mercenary)
                .display(Items.IRON_CHESTPLATE, Text.translatable("advancements.ygasi.mercenary.defence1.title"), Text.translatable("advancements.ygasi.mercenary.defence1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_defence1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, mercenary.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/defence1");
        Advancement defence2 = Advancement.Builder.create().parent(defence1)
                .display(Items.IRON_CHESTPLATE, Text.translatable("advancements.ygasi.mercenary.defence2.title"), Text.translatable("advancements.ygasi.mercenary.defence2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_defence2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, defence1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/defence2");
        Advancement defence3 = Advancement.Builder.create().parent(defence2)
                .display(Items.IRON_CHESTPLATE, Text.translatable("advancements.ygasi.mercenary.defence3.title"), Text.translatable("advancements.ygasi.mercenary.defence3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_defence3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, defence2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/defence3");

        //wizardry skills

        Advancement fight1 = Advancement.Builder.create().parent(wizardry)
                .display(Items.BLAZE_POWDER, Text.translatable("advancements.ygasi.wizardry.fight1.title"), Text.translatable("advancements.ygasi.wizardry.fight1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_fight1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, wizardry.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/fight1");
        Advancement fight2 = Advancement.Builder.create().parent(fight1)
                .display(Items.BLAZE_POWDER, Text.translatable("advancements.ygasi.wizardry.fight2.title"), Text.translatable("advancements.ygasi.wizardry.fight2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_fight2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, fight1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/fight2");
        Advancement fight3 = Advancement.Builder.create().parent(fight2)
                .display(Items.BLAZE_POWDER, Text.translatable("advancements.ygasi.wizardry.fight3.title"), Text.translatable("advancements.ygasi.wizardry.fight3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_fight3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, fight2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/fight3");

        Advancement element1 = Advancement.Builder.create().parent(wizardry)
                .display(Items.FIRE_CHARGE, Text.translatable("advancements.ygasi.wizardry.element1.title"), Text.translatable("advancements.ygasi.wizardry.element1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_element1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, wizardry.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/element1");
        Advancement element2 = Advancement.Builder.create().parent(element1)
                .display(Items.FIRE_CHARGE, Text.translatable("advancements.ygasi.wizardry.element2.title"), Text.translatable("advancements.ygasi.wizardry.element2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_element2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, element1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/element2");
        Advancement element3 = Advancement.Builder.create().parent(element2)
                .display(Items.FIRE_CHARGE, Text.translatable("advancements.ygasi.wizardry.element3.title"), Text.translatable("advancements.ygasi.wizardry.element3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_element3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, element2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/element3");

        Advancement force1 = Advancement.Builder.create().parent(wizardry)
                .display(Items.ENDER_PEARL, Text.translatable("advancements.ygasi.wizardry.force1.title"), Text.translatable("advancements.ygasi.wizardry.force1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_force1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, wizardry.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/force1");
        Advancement force2 = Advancement.Builder.create().parent(force1)
                .display(Items.ENDER_PEARL, Text.translatable("advancements.ygasi.wizardry.force2.title"), Text.translatable("advancements.ygasi.wizardry.force2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_force2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, force1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/force2");
        Advancement force3 = Advancement.Builder.create().parent(force2)
                .display(Items.ENDER_PEARL, Text.translatable("advancements.ygasi.wizardry.force3.title"), Text.translatable("advancements.ygasi.wizardry.force3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_force3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, force2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/force3");

        //druidry skills

        Advancement necromancy1 = Advancement.Builder.create().parent(druidry)
                .display(Items.BONE, Text.translatable("advancements.ygasi.druidry.necromancy1.title"), Text.translatable("advancements.ygasi.druidry.necromancy1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_necromancy1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, druidry.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/necromancy1");
        Advancement necromancy2 = Advancement.Builder.create().parent(necromancy1)
                .display(Items.BONE, Text.translatable("advancements.ygasi.druidry.necromancy2.title"), Text.translatable("advancements.ygasi.druidry.necromancy2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_necromancy2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, necromancy1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/necromancy2");
        Advancement necromancy3 = Advancement.Builder.create().parent(necromancy2)
                .display(Items.BONE, Text.translatable("advancements.ygasi.druidry.necromancy3.title"), Text.translatable("advancements.ygasi.druidry.necromancy3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_necromancy3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, necromancy2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/necromancy3");

        Advancement nature1 = Advancement.Builder.create().parent(druidry)
                .display(Items.GRASS_BLOCK, Text.translatable("advancements.ygasi.druidry.nature1.title"), Text.translatable("advancements.ygasi.druidry.nature1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_nature1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, druidry.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/nature1");
        Advancement nature2 = Advancement.Builder.create().parent(nature1)
                .display(Items.GRASS_BLOCK, Text.translatable("advancements.ygasi.druidry.nature2.title"), Text.translatable("advancements.ygasi.druidry.nature2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_nature2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, nature1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/nature2");
        Advancement nature3 = Advancement.Builder.create().parent(nature2)
                .display(Items.GRASS_BLOCK, Text.translatable("advancements.ygasi.druidry.nature3.title"), Text.translatable("advancements.ygasi.druidry.nature3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_nature3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, nature2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/nature3");

        Advancement protean1 = Advancement.Builder.create().parent(druidry)
                .display(Items.SLIME_BALL, Text.translatable("advancements.ygasi.druidry.protean1.title"), Text.translatable("advancements.ygasi.druidry.protean1.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_protean1", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, druidry.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/protean1");
        Advancement protean2 = Advancement.Builder.create().parent(protean1)
                .display(Items.SLIME_BALL, Text.translatable("advancements.ygasi.druidry.protean2.title"), Text.translatable("advancements.ygasi.druidry.protean2.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_protean2", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, protean1.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/protean2");
        Advancement protean3 = Advancement.Builder.create().parent(protean2)
                .display(Items.SLIME_BALL, Text.translatable("advancements.ygasi.druidry.protean3.title"), Text.translatable("advancements.ygasi.druidry.protean3.description"), null, AdvancementFrame.TASK, false, false, true)
                .criterion("unlocked_protean3", new ImpossibleCriterion.Conditions()).criterion("gotten_previous", new AdvancementGottenCriterion.Conditions(EntityPredicate.Extended.EMPTY, protean2.getId()))
                .rewards(AdvancementRewards.Builder.experience(1)).build(consumer, "ygasi" + "/protean3");

        //extra

    }
}