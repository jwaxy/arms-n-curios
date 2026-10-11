package me.jwaxy.armsncurios.client.armor;

import com.geckolib.animatable.GeoItem;
import com.geckolib.cache.model.BakedGeoModel;
import com.geckolib.cache.model.GeoBone;
import com.geckolib.constant.dataticket.DataTicket;
import com.geckolib.renderer.GeoArmorRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import com.geckolib.util.RenderUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;

import java.util.Objects;

import static me.jwaxy.armsncurios.ArmsnCurios.MODID;

public abstract class HatArmorRenderer<T extends Item & GeoItem, S extends HumanoidRenderState & GeoRenderState> extends GeoArmorRenderer<T, S> {

    public static final DataTicket<Boolean> HAS_HELMET = DataTicket.create(MODID + ":has_helmet", Boolean.class);

    public HatArmorRenderer(GenericArmorModel<T> model) {
        super(model);
    }

    @Override
    public void submitRenderTasks(RenderPassInfo<S> info, OrderedSubmitNodeCollector renderTasks, RenderType renderType) {
        if (renderType == null) return;

        S state = info.renderState();
        EquipmentSlot slot = Objects.requireNonNull(state.getGeckolibData(CURRENT_SLOT));
        BakedGeoModel model = info.model();

        if (model.isMissingno()) {
            this.submitMissingModelRender(info, renderTasks);
            return;
        }

        int packedLight = info.packedLight();
        int packedOverlay = info.packedOverlay();
        int renderColor = info.renderColor();

        boolean offsetHead = slot == EquipmentSlot.HEAD && Boolean.TRUE.equals(state.getGeckolibData(HAS_HELMET));

        renderTasks.submitCustomGeometry(info.poseStack(), renderType, (pose, vertexConsumer) -> {
            PoseStack poseStack = info.poseStack();
            poseStack.pushPose();
            poseStack.last().set(pose);

            info.renderPosed(() -> {
                for (ArmorSegment segment : this.getSegmentsForSlot(state, slot)) {
                    info.model().getBone(this.getBoneNameForSegment(state, segment)).ifPresent(bone -> {
                        if (offsetHead && bone.name().equals("armorHead")) {
                            renderHeadWithOffset(info, bone, poseStack, vertexConsumer, packedLight, packedOverlay, renderColor);
                        } else {
                            bone.positionAndRender(info, vertexConsumer, packedLight, packedOverlay, renderColor);
                        }
                    });
                }
            });

            poseStack.popPose();
        });
    }

    private void renderHeadWithOffset(RenderPassInfo<S> info, GeoBone bone, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int renderColor) {
        poseStack.pushPose();

        RenderUtil.prepMatrixForBoneAndUpdateListeners(poseStack, bone, info);

        poseStack.translate(0.0, 0.25, 0.0);
        poseStack.scale(1.02F, 1.02F, 1.02F);

        bone.render(info, poseStack, vertexConsumer, packedLight, packedOverlay, renderColor);
        bone.renderChildren(info, poseStack, vertexConsumer, packedLight, packedOverlay, renderColor);

        poseStack.popPose();
    }
}