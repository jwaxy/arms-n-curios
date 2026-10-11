package me.jwaxy.armsncurios.client.curio;

import com.geckolib.animatable.GeoItem;
import com.geckolib.renderer.GeoArmorRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import me.jwaxy.armsncurios.client.armor.HatArmorRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public abstract class GeoCurioRenderer<T extends Item & GeoItem, R extends HumanoidRenderState & GeoRenderState> implements ICurioRenderer {
    protected final GeoArmorRenderer<T, R> renderer;

    protected GeoCurioRenderer(GeoArmorRenderer<T, R> renderer) {
        this.renderer = renderer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends LivingEntityRenderState, M extends EntityModel<? super S>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, S renderState, RenderLayerParent<S, M> renderLayerParent, EntityRendererProvider.Context context, float yRotation, float xRotation) {

        if (!(stack.getItem() instanceof GeoItem) || !(renderState instanceof HumanoidRenderState humanoidRenderState)) {
            return;
        }

        T animatable = (T) stack.getItem();
        EquipmentSlot slot = EquipmentSlot.HEAD;
        HumanoidModel<?> baseModel = (HumanoidModel<?>) renderLayerParent.getModel();

        GeoArmorRenderer.RenderData renderData = new GeoArmorRenderer.RenderData(stack, slot, slotContext.entity(), baseModel);

        this.renderer.captureDefaultRenderState(animatable, renderData, (R) humanoidRenderState, 0.0F);

        R state = (R) humanoidRenderState;
        state.addGeckolibData(HatArmorRenderer.HAS_HELMET, !slotContext.entity().getItemBySlot(EquipmentSlot.HEAD).isEmpty());

        CameraRenderState cameraState = new CameraRenderState();
        context.getEntityRenderDispatcher().camera.extractRenderState(cameraState, 0.0F);

        this.renderer.performRenderPass((R) humanoidRenderState, poseStack, submitNodeCollector, cameraState);
    }
}