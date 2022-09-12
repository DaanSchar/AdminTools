package com.daan.admintools.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

    public static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ELYTRA_FLIGHT;

    static {
        BUILDER.push("AdminTools");

        ENABLE_ELYTRA_FLIGHT = BUILDER.comment("Define here elytra flight")
                .define("Enable Elytra Flight", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
