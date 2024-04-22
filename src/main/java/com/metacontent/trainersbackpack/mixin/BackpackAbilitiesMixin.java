package com.metacontent.trainersbackpack.mixin;

import com.cobblemon.mod.common.Cobblemon;
import com.metacontent.trainersbackpack.TrainersBackpack;
import com.metacontent.trainersbackpack.item.TrainersBackpackItems;
import com.tiviacz.travelersbackpack.blockentity.TravelersBackpackBlockEntity;
import com.tiviacz.travelersbackpack.common.BackpackAbilities;
import com.tiviacz.travelersbackpack.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BackpackAbilities.class)
public class BackpackAbilitiesMixin {
    @Mutable
    @Shadow @Final public static Item[] ALL_ABILITIES_LIST;

    @Mutable
    @Shadow @Final public static Item[] ITEM_ABILITIES_LIST;

    @Shadow @Final public static List<Item> ALLOWED_ABILITIES;

    @Inject(method = "abilityTick", at = @At("HEAD"))
    protected void injectAbilityTickMethod(ItemStack stack, PlayerEntity player, TravelersBackpackBlockEntity blockEntity, CallbackInfo ci) {
        TrainersBackpack.LOGGER.error("1");
        if (blockEntity == null) {
            if (stack.getItem() == TrainersBackpackItems.HEALING_BACKPACK_ITEM) {
                TrainersBackpack.LOGGER.error("2");
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
        ALL_ABILITIES_LIST = new Item[]{ModItems.NETHERITE_TRAVELERS_BACKPACK, ModItems.DIAMOND_TRAVELERS_BACKPACK, ModItems.GOLD_TRAVELERS_BACKPACK, ModItems.EMERALD_TRAVELERS_BACKPACK, ModItems.IRON_TRAVELERS_BACKPACK, ModItems.LAPIS_TRAVELERS_BACKPACK, ModItems.REDSTONE_TRAVELERS_BACKPACK, ModItems.BOOKSHELF_TRAVELERS_BACKPACK, ModItems.SPONGE_TRAVELERS_BACKPACK, ModItems.CAKE_TRAVELERS_BACKPACK, ModItems.CACTUS_TRAVELERS_BACKPACK, ModItems.MELON_TRAVELERS_BACKPACK, ModItems.PUMPKIN_TRAVELERS_BACKPACK, ModItems.CREEPER_TRAVELERS_BACKPACK, ModItems.DRAGON_TRAVELERS_BACKPACK, ModItems.ENDERMAN_TRAVELERS_BACKPACK, ModItems.BLAZE_TRAVELERS_BACKPACK, ModItems.GHAST_TRAVELERS_BACKPACK, ModItems.MAGMA_CUBE_TRAVELERS_BACKPACK, ModItems.SPIDER_TRAVELERS_BACKPACK, ModItems.WITHER_TRAVELERS_BACKPACK, ModItems.BAT_TRAVELERS_BACKPACK, ModItems.BEE_TRAVELERS_BACKPACK, ModItems.OCELOT_TRAVELERS_BACKPACK, ModItems.COW_TRAVELERS_BACKPACK, ModItems.CHICKEN_TRAVELERS_BACKPACK, ModItems.SQUID_TRAVELERS_BACKPACK, TrainersBackpackItems.HEALING_BACKPACK_ITEM};
        ITEM_ABILITIES_LIST = new Item[]{ModItems.NETHERITE_TRAVELERS_BACKPACK, ModItems.DIAMOND_TRAVELERS_BACKPACK, ModItems.GOLD_TRAVELERS_BACKPACK, ModItems.EMERALD_TRAVELERS_BACKPACK, ModItems.IRON_TRAVELERS_BACKPACK, ModItems.LAPIS_TRAVELERS_BACKPACK, ModItems.CAKE_TRAVELERS_BACKPACK, ModItems.CACTUS_TRAVELERS_BACKPACK, ModItems.PUMPKIN_TRAVELERS_BACKPACK, ModItems.CREEPER_TRAVELERS_BACKPACK, ModItems.DRAGON_TRAVELERS_BACKPACK, ModItems.ENDERMAN_TRAVELERS_BACKPACK, ModItems.BLAZE_TRAVELERS_BACKPACK, ModItems.GHAST_TRAVELERS_BACKPACK, ModItems.MAGMA_CUBE_TRAVELERS_BACKPACK, ModItems.SPIDER_TRAVELERS_BACKPACK, ModItems.WITHER_TRAVELERS_BACKPACK, ModItems.BAT_TRAVELERS_BACKPACK, ModItems.BEE_TRAVELERS_BACKPACK, ModItems.OCELOT_TRAVELERS_BACKPACK, ModItems.COW_TRAVELERS_BACKPACK, ModItems.CHICKEN_TRAVELERS_BACKPACK, ModItems.SQUID_TRAVELERS_BACKPACK, TrainersBackpackItems.HEALING_BACKPACK_ITEM};
        ALLOWED_ABILITIES.add(TrainersBackpackItems.HEALING_BACKPACK_ITEM);
    }
}
