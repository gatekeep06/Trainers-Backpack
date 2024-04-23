package com.metacontent.trainersbackpack.item;

import com.metacontent.trainersbackpack.TrainersBackpack;
import com.metacontent.trainersbackpack.block.TrainersBackpackBlocks;
import com.tiviacz.travelersbackpack.blocks.TravelersBackpackBlock;
import com.tiviacz.travelersbackpack.init.ModItemGroups;
import com.tiviacz.travelersbackpack.init.ModItems;
import com.tiviacz.travelersbackpack.items.TravelersBackpackItem;
import com.tiviacz.travelersbackpack.util.Reference;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TrainersBackpackItems {
    public static final TravelersBackpackItem HEALING_BACKPACK_ITEM = registerBackpackItem("healing_backpack", TrainersBackpackBlocks.HEALING_BACKPACK_BLOCK);

    private static TravelersBackpackItem registerBackpackItem(String name, TravelersBackpackBlock backpackBlock) {
        return Registry.register(Registries.ITEM, new Identifier(TrainersBackpack.ID, name), new TravelersBackpackItem(backpackBlock));
    }

    public static void registerItems() {
        TrainersBackpack.LOGGER.info("Registering items for " + TrainersBackpack.ID);

        ModItems.BACKPACKS.add(HEALING_BACKPACK_ITEM);

        List<String> backpackNames = new ArrayList<>(List.of(Reference.BACKPACK_NAMES));
        backpackNames.add("healing_backpack");
        Reference.BACKPACK_NAMES = backpackNames.toArray(new String[]{});

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.TRAVELERS_BACKPACK).register(entries -> {
            entries.add(HEALING_BACKPACK_ITEM);
        });
    }
}
