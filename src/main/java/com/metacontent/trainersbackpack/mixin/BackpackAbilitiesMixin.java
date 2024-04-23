package com.metacontent.trainersbackpack.mixin;

import com.cobblemon.mod.common.Cobblemon;
import com.metacontent.trainersbackpack.item.TrainersBackpackItems;
import com.tiviacz.travelersbackpack.blockentity.TravelersBackpackBlockEntity;
import com.tiviacz.travelersbackpack.common.BackpackAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(BackpackAbilities.class)
public class BackpackAbilitiesMixin {
    @Mutable
    @Shadow @Final public static Item[] ALL_ABILITIES_LIST;

    @Mutable
    @Shadow @Final public static Item[] ITEM_ABILITIES_LIST;

    @Shadow(remap = false) @Final public static List<Item> ALLOWED_ABILITIES;

    @Inject(method = "abilityTick", at = @At("HEAD"))
    protected void injectAbilityTickMethod(ItemStack stack, PlayerEntity player, TravelersBackpackBlockEntity blockEntity, CallbackInfo ci) {
        if (blockEntity == null) {
            if (stack.getItem() == TrainersBackpackItems.HEALING_BACKPACK_ITEM) {
                healingAbility(player);
            }
        }
    }

    @Unique
    private void healingAbility(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity serverPlayer && Cobblemon.INSTANCE.getBattleRegistry().getBattleByParticipatingPlayer(serverPlayer) == null) {
            Cobblemon.INSTANCE.getStorage().getParty(serverPlayer).forEach(pokemon -> {
                if (!pokemon.isFainted() && !pokemon.isFullHealth()) {
                    int currentHealth = pokemon.getCurrentHealth();
                    pokemon.setCurrentHealth(++currentHealth);
                }
            });
        }
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void injectStaticBlock(CallbackInfo ci) {
        List<Item> allAbilitiesList = new ArrayList<>(List.of(ALL_ABILITIES_LIST));
        List<Item> itemAbilitiesList = new ArrayList<>(List.of(ITEM_ABILITIES_LIST));

        allAbilitiesList.add(TrainersBackpackItems.HEALING_BACKPACK_ITEM);
        itemAbilitiesList.add(TrainersBackpackItems.HEALING_BACKPACK_ITEM);

        ALL_ABILITIES_LIST = allAbilitiesList.toArray(new Item[]{});
        ITEM_ABILITIES_LIST = itemAbilitiesList.toArray(new Item[]{});

        ALLOWED_ABILITIES.add(TrainersBackpackItems.HEALING_BACKPACK_ITEM);
    }
}
