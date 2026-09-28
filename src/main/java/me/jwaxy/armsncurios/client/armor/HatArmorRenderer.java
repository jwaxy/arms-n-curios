package me.jwaxy.armsncurios.client.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public abstract class HatArmorRenderer<T extends Item & GeoItem> extends GeoArmorRenderer<T> {
    public HatArmorRenderer(GenericArmorModel<T> model) {
        super(model);
    }

    @Override
    public void renderCubesOfBone(PoseStack poseStack, GeoBone bone, VertexConsumer buffer, int packedLight, int packedOverlay, int colour) {
        if (bone == this.head && this.currentEntity instanceof LivingEntity living && !living.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.0D, 0.25D, 0.0D);
            poseStack.scale(1.02F, 1.02F, 1.02F);

            super.renderCubesOfBone(poseStack, bone, buffer, packedLight, packedOverlay, colour);

            poseStack.popPose();
            return;
        }

        super.renderCubesOfBone(poseStack, bone, buffer, packedLight, packedOverlay, colour);
    }
}