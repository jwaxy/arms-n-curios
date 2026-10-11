package me.jwaxy.armsncurios.client.armor.impl;

import com.geckolib.renderer.base.GeoRenderState;
import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import io.redspace.irons_artifice.item.TricorneItem;
import me.jwaxy.armsncurios.client.armor.HatArmorRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class TricorneArmorRenderer<S extends HumanoidRenderState & GeoRenderState> extends HatArmorRenderer<TricorneItem, S> {

    public TricorneArmorRenderer(GenericArmorModel<TricorneItem> model) {
        super(model);
    }
}