package me.jwaxy.armsncurios.client.armor.impl;

import com.geckolib.renderer.base.GeoRenderState;
import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import io.redspace.irons_artifice.item.CowboyHatItem;
import me.jwaxy.armsncurios.client.armor.HatArmorRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class CowboyHatArmorRenderer<S extends HumanoidRenderState & GeoRenderState> extends HatArmorRenderer<CowboyHatItem, S> {

    public CowboyHatArmorRenderer(GenericArmorModel<CowboyHatItem> model) {
        super(model);
    }
}