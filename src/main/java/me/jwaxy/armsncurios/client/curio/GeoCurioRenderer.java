package me.jwaxy.armsncurios.client.curio;

import com.mojang.blaze3d.vertex.PoseStack;
import me.jwaxy.armsncurios.ArmsnCurios;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public abstract class GeoCurioRenderer implements ICurioRenderer {
    private final GeoArmorRenderer<?> renderer;
    private final RenderType renderType;

    protected GeoCurioRenderer(GeoArmorRenderer<?> renderer, String id) {
        this.renderer = renderer;
        this.renderType = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(ArmsnCurios.MODID, id));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        renderer.prepForRender(entity, stack, EquipmentSlot.HEAD, (HumanoidModel<?>) renderLayerParent.getModel(), buffer, partialTicks, limbSwing, limbSwingAmount, netHeadYaw, headPitch);

        renderer.renderToBuffer(poseStack, buffer.getBuffer(renderType), light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
    }
}