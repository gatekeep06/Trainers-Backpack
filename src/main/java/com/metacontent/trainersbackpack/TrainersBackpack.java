package com.metacontent.trainersbackpack;

import com.metacontent.trainersbackpack.block.TrainersBackpackBlocks;
import com.metacontent.trainersbackpack.item.TrainersBackpackItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainersBackpack implements ModInitializer {
    public static final String ID = "trainers-backpack";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    @Override
    public void onInitialize() {
        TrainersBackpackBlocks.registerBlocks();
        TrainersBackpackItems.registerItems();
    }
}
