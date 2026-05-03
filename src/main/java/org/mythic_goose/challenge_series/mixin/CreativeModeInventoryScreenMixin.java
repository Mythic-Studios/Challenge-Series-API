package org.mythic_goose.challenge_series.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.mythic_goose.challenge_series.creative_tab_v1.BannerRenderer;
import org.mythic_goose.challenge_series.creative_tab_v1.ModCreativeTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin {

    @Shadow
    private static CreativeModeTab selectedTab;

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void msgwoft$renderBanners(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (selectedTab == ModCreativeTabs.CORE) {
            BannerRenderer.render((CreativeModeInventoryScreen)(Object)this, graphics);
        }
    }
}