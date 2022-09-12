package com.daan.admintools.events;

import com.daan.admintools.AdminTools;
import com.daan.admintools.commands.TeamCommandExtension;
import com.daan.admintools.config.ServerConfig;
import com.daan.admintools.commands.WhitelistCommandExtension;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = AdminTools.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        TeamCommandExtension.register(event.getDispatcher());
        WhitelistCommandExtension.register(event.getDispatcher());
    }


}
