package me.jwaxy.armsncurios.client;

import io.redspace.irons_artifice.registry.ItemRegistry;
import me.jwaxy.armsncurios.ArmsnCurios;
import me.jwaxy.armsncurios.client.curio.impl.CowboyHatCurioRenderer;
import me.jwaxy.armsncurios.client.curio.impl.TricorneCurioRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(modid = ArmsnCurios.MODID, value = Dist.CLIENT)
public class ArmsnCuriosClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CuriosRendererRegistry.register(ItemRegistry.COWBOY_HAT.get(), CowboyHatCurioRenderer::new);
            CuriosRendererRegistry.register(ItemRegistry.TRICORNE_HAT.get(), TricorneCurioRenderer::new);
        });
    }
}