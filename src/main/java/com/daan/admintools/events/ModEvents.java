package com.daan.admintools.events;

import com.daan.admintools.AdminTools;
import com.daan.admintools.commands.TeamCommandExtension;
import com.daan.admintools.commands.WhitelistCommandExtension;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AdminTools.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        TeamCommandExtension.register(event.getDispatcher());
        WhitelistCommandExtension.register(event.getDispatcher());
    }


}
