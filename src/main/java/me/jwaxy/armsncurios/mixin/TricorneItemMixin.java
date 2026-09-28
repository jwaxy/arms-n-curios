package me.jwaxy.armsncurios.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.redspace.irons_artifice.api.ComposeShotEvent;
import io.redspace.irons_artifice.item.TricorneItem;
import io.redspace.irons_artifice.registry.ItemRegistry;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

@Mixin(value = TricorneItem.class, remap = false)
public abstract class TricorneItemMixin {
    @ModifyExpressionValue(method = "handleTricorneAbility", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack checkCuriosSlotForTricorne(ItemStack original, ComposeShotEvent event) {
        if (original.is(ItemRegistry.TRICORNE_HAT.get())) return original;

        return CuriosApi.getCuriosInventory(event.getEntity()).flatMap(inv -> inv.findFirstCurio(ItemRegistry.TRICORNE_HAT.get())).map(SlotResult::stack).orElse(original);
    }
}