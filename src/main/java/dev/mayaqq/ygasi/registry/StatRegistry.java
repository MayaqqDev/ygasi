package dev.mayaqq.ygasi.registry;

import eu.pb4.polymer.core.api.other.PolymerStat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;


public class StatRegistry {
    public static Identifier SKILL_POINTS = PolymerStat.registerStat("skill_points", StatFormatter.DEFAULT);
    public static Identifier SKILL_POINTS_TOTAL = PolymerStat.registerStat("skill_points_total", StatFormatter.DEFAULT);

    public static void register() {}
}
