package com.daan.admintools.events;

import com.daan.admintools.AdminTools;
import com.daan.admintools.commands.TeamCommandExtension;
import com.daan.admintools.config.ServerConfig;
import com.daan.admintools.commands.WhitelistCommandExtension;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdminTools.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void blockElytraFlying(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        boolean flightEnabledInConfig = ServerConfig.ENABLE_ELYTRA_FLIGHT.get();

        if (!player.level.isClientSide()) {
            if (!flightEnabledInConfig) {
                blockPlayerFromFlying(player);
            }
        }
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        TeamCommandExtension.register(event.getDispatcher());
        WhitelistCommandExtension.register(event.getDispatcher());
    }

    private static void blockPlayerFromFlying(Player player) {
        if (player.isFallFlying()) {
            player.stopFallFlying();

            boolean showMessage = ServerConfig.SHOW_MESSAGE.get();

            if (showMessage) {
                player.displayClientMessage(getElytraDisabledMessage(), true);
            }
        }
    }

    private static MutableComponent getElytraDisabledMessage() {
        return new TextComponent("Elytra's are ").append(
                new TextComponent("Disabled!")
                        .withStyle(ChatFormatting.RED)
                        .withStyle(ChatFormatting.UNDERLINE)
                        .withStyle(ChatFormatting.BOLD)
        );
    }

}
