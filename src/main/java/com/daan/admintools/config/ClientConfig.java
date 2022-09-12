package com.daan.admintools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

    public static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> SHOW_MESSAGE;

    static {
        BUILDER.push("AdminTools");

        SHOW_MESSAGE = BUILDER.comment("Displays a message when a player tries to fly when elytras are disabled")
                .define("Display message", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
