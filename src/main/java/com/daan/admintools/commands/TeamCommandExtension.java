package com.daan.admintools.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.*;
import net.minecraft.commands.arguments.*;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.scores.*;

import java.util.*;

public class TeamCommandExtension {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("team").requires(
                        sourceStack -> sourceStack.hasPermission(2)
                ).then(Commands.literal("join").then(Commands.argument("team", TeamArgument.team()).executes(
                        cmdContext -> joinTeam(cmdContext.getSource(), TeamArgument.getTeam(cmdContext, "team"), Collections.singleton(cmdContext.getSource().getEntityOrException().getScoreboardName()))
                ).then(Commands.literal("list").then(Commands.argument("members", ScoreHolderArgument.scoreHolders()).executes(context -> {
                    String names = getMembers(context);
                    List<String> nameList = Arrays.stream(names.split(",")).toList();
                    return joinTeam(context.getSource(), TeamArgument.getTeam(context, "team"), nameList);
                }))))));

    }

    private static int joinTeam(CommandSourceStack sourceStack, PlayerTeam playerTeam, Collection<String> stringList) {
        Scoreboard scoreboard = sourceStack.getServer().getScoreboard();

        for (String s : stringList) {
            scoreboard.addPlayerToTeam(s, playerTeam);
        }

        if (stringList.size() == 1) {
            sourceStack.sendSuccess(new TranslatableComponent("commands.team.join.success.single", stringList.iterator().next(), playerTeam.getFormattedDisplayName()), true);
        } else {
            sourceStack.sendSuccess(new TranslatableComponent("commands.team.join.success.multiple", stringList.size(), playerTeam.getFormattedDisplayName()), true);
        }

        return stringList.size();
    }

    private static String getMembers(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<String> names = ScoreHolderArgument.getNamesWithDefaultWildcard(context, "members");
        String string = "";

        // we can't index into a set, so we must loop through it,
        // even though we know there is only 1 value.
        for (String value : names) {
            string = value;
        }

        return string;
    }

}
