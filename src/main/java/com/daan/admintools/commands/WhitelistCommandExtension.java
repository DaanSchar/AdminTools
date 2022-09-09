package com.daan.admintools.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.*;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.*;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.*;
import net.minecraft.server.players.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WhitelistCommandExtension {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("whitelist").requires(sourceStack -> sourceStack.hasPermission(3))
                        .then(Commands.literal("add").then(Commands.literal("list").then(Commands.argument("targets", GameProfileArgument.gameProfile()).executes(context -> {
                            List<String> names = getNames(context);

                            for (String name : names) {
                                addPlayers(context.getSource(), getGameProfile(name, context));
                            }

                            return 0;
                        }))))
        );
    }

    private static List<String> getNames(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<GameProfile> gameProfiles = GameProfileArgument.getGameProfiles(context, "targets");

        String names = "";
        for (GameProfile profile : gameProfiles) {
            names = profile.getName();
        }
        return Arrays.stream(names.split(",")).toList();
    }

    private static Collection<GameProfile> getGameProfile(String playerName, CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        return GameProfileArgument.gameProfile().parse(new StringReader(playerName)).getNames(context.getSource());
    }

    private static void addPlayers(CommandSourceStack sourceStack, Collection<GameProfile> gameProfiles) {
        UserWhiteList userwhitelist = sourceStack.getServer().getPlayerList().getWhiteList();

        for (GameProfile gameprofile : gameProfiles) {
            if (!userwhitelist.isWhiteListed(gameprofile)) {
                UserWhiteListEntry userwhitelistentry = new UserWhiteListEntry(gameprofile);
                userwhitelist.add(userwhitelistentry);
                sourceStack.sendSuccess(new TranslatableComponent("commands.whitelist.add.success", ComponentUtils.getDisplayName(gameprofile)), true);
            } else {
                sourceStack.sendFailure(new TextComponent("Player is already whitelisted: " + ComponentUtils.getDisplayName(gameprofile).getString()));
            }
        }
    }

}
