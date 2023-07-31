package org.totemcraft.pow.mixin;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.totemcraft.pow.PicnicOfTheWorld;
import org.totemcraft.pow.item.ItemLoader;
import org.totemcraft.pow.loader.ContentLoader;

@Mixin(ModelBakery.class)
public class MixinModelBakery {
//    @Inject(method = "getModel(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/resources/model/UnbakedModel;", at = @At("HEAD"), cancellable = true)
//    public void getModel(ResourceLocation location, CallbackInfoReturnable<UnbakedModel> callback) {
//        if (!location.getNamespace().equals(PicnicOfTheWorld.Id)) {
//            return;
//        }
//
//        System.out.println("Loading POW " + location);
//    }

    @Inject(method = "loadBlockModel(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/block/model/BlockModel;", at = @At("HEAD"), cancellable = true)
    public void loadBlockModel(ResourceLocation location, CallbackInfoReturnable<BlockModel> callback) {
        if (!location.getNamespace().equals(PicnicOfTheWorld.Id)) {
            return;
        }



        var blockModel = ContentLoader.findExtraBlockModel(location);
        if (blockModel != null) {
            callback.setReturnValue(blockModel);
        }
    }
}
