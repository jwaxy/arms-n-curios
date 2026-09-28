package me.jwaxy.armsncurios.mixin;

import io.redspace.irons_artifice.item.CowboyHatItem;
import io.redspace.irons_artifice.registry.ItemRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

@Mixin(value = CowboyHatItem.class, remap = false)
public abstract class CowboyHatItemMixin {
    @ModifyVariable(method = "onBulletKill", at = @At(value = "STORE", target = "Lnet/minecraft/world/entity/LivingEntity;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"), name = "hat")
    private static ItemStack checkCuriosSlotForCowboyHat(ItemStack hat, LivingDeathEvent event) {
        if (hat.is(ItemRegistry.COWBOY_HAT.get())) return hat;

        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            return CuriosApi.getCuriosInventory(attacker).flatMap(inv -> inv.findFirstCurio(ItemRegistry.COWBOY_HAT.get())).map(SlotResult::stack).orElse(hat);
        }

        return hat;
    }
}