package dev.mayaqq.ygasi;

import eu.pb4.polymer.api.other.PolymerStat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;


public class StatRegistry {
    public static Identifier SKILL_POINTS = PolymerStat.registerStat("skill_points", StatFormatter.DEFAULT);

    public static void skillRegister() {}
}
