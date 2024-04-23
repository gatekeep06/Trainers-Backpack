package com.metacontent.trainersbackpack.mixin;

import com.metacontent.trainersbackpack.block.TrainersBackpackBlocks;
import com.metacontent.trainersbackpack.item.TrainersBackpackItems;
import com.tiviacz.travelersbackpack.TravelersBackpack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TravelersBackpack.class)
public class TravelersBackpackMixin {
    @Inject(method = "onInitialize", at = @At("TAIL"), remap = false)
    protected void injectRegistration(CallbackInfo ci) {
        TrainersBackpackBlocks.registerBlocks();
        TrainersBackpackItems.registerItems();
    }

    @Inject(method = "onInitialize", at = @At("HEAD"), remap = false)
    protected void injectBackpackNamesRegistration(CallbackInfo ci) {
        TrainersBackpackItems.registerBackpackNames();
    }
}
