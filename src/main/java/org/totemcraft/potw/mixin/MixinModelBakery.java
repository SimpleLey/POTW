package org.totemcraft.potw.mixin;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.totemcraft.potw.PicnicOfTheWorld;
import org.totemcraft.potw.loader.ContentLoader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mixin(ModelBakery.class)
public class MixinModelBakery {
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

    @Redirect(method = "loadModel", at = @At(value = "INVOKE", target = "Ljava/util/Map;getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    public Object getBlockState(Map<ResourceLocation, List<ModelBakery.LoadedJson>> map, Object arg0, Object arg1) {
        ResourceLocation location = (ResourceLocation) arg0;
        var current = map.getOrDefault(location, List.of());
        if (!location.getNamespace().equals(PicnicOfTheWorld.Id) || !current.isEmpty()) {
            return current;
        }

        return Optional.ofNullable(ContentLoader.findExtraBlockState(location)).map(List::of).orElse(List.of());
    }
}
