package com.daan.admintools.mixin;


import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Overwrite;

@org.spongepowered.asm.mixin.Mixin(ElytraItem.class)
public class MixinElytra {

    /**
     * @author Daan Schar
     * @reason Disables elytra
     *
     * Presumably removes opportunity for players to enter elytra fly state.
     */
    @Overwrite
    public boolean canElytraFly(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        if (entity instanceof Player player) {
            player.displayClientMessage(getElytraDisabledMessage(), true);
        }

        return false;
    }

    /**
     * @author Daan Schar
     * @reason Disables elytra
     *
     * Were a player to still enter an elytra fly state, it will leave the state when the next tick comes around.
     */
    @Overwrite
    public boolean elytraFlightTick(ItemStack stack, net.minecraft.world.entity.LivingEntity entity, int flightTicks) {
        if (entity instanceof Player player) {
            player.displayClientMessage(getElytraDisabledMessage(), true);
        }

        return false;
    }

    private MutableComponent getElytraDisabledMessage() {
        return new TextComponent("Elytra's ")
                .withStyle(ChatFormatting.YELLOW)
                .append(
                        new TextComponent("are ")
                                .withStyle(ChatFormatting.WHITE)
                )
                .append(
                        new TextComponent("Disabled!")
                                .withStyle(ChatFormatting.RED)
                                .withStyle(ChatFormatting.UNDERLINE)
                                .withStyle(ChatFormatting.BOLD)
                );
    }




}
