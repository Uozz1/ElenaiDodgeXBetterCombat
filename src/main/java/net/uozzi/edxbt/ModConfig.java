package net.uozzi.edxbt;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {
    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec.IntValue MAX_FEATHERS;
    public static final ForgeConfigSpec.DoubleValue SWING_PROCESS;
    public static final ForgeConfigSpec.DoubleValue PERFECT_ATTACK_VOLUME;
    public static final ForgeConfigSpec.IntValue PARTICLES;
    public static final ForgeConfigSpec.BooleanValue SHOW_PARTICLE;
    public static final ForgeConfigSpec.BooleanValue USE_CUSTOM_SOUND;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("General settings").push("general");

        MAX_FEATHERS = builder
                .comment("Maximum Feathers for players when they spawn (default: 20)")
                .defineInRange("maxFeathers", 20, 0, 20);

        SWING_PROCESS = builder
                .comment("The Swing Process represents the progress of a timer that starts when an attack is executed.")
                .comment("This timer goes from 0.0 to 1.0, where 1.0 marks the completion of the recovery from attacking.")
                .comment("The value defined here indicates the point during this process (default: 0.7) when rolling is allowed.")
                .defineInRange("swingTreshold", 0.7, 0.0, 1.0);

        PERFECT_ATTACK_VOLUME = builder
                .comment("Volume of the Perfect Attack sound effect. set to 0.0 to disable. (default: 0.6)")
                .defineInRange("perfectAttackVolume", 0.6, 0.0, 1.0);

        SHOW_PARTICLE = builder
                .comment("Set to false if you don't want to show the attack particle. (default: true)")
                .define("showParticle", true);

        PARTICLES = builder
                .comment("How many ticks does the Sweep Attack particle display after performing a Perfect Attack. (default: 5)")
                .defineInRange("Particles", 5, 1, 20);

        USE_CUSTOM_SOUND = builder
                .comment("Set to false to use vanilla sweep attack sound instead of custom.")
                .define("useCustomSound", true);

        builder.pop();
        COMMON_CONFIG = builder.build();
    }

}