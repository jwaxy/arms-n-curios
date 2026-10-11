package me.jwaxy.armsncurios.client.curio.impl;

import com.geckolib.renderer.base.GeoRenderState;
import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import io.redspace.irons_artifice.item.TricorneItem;
import me.jwaxy.armsncurios.client.armor.impl.TricorneArmorRenderer;
import me.jwaxy.armsncurios.client.curio.GeoCurioRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class TricorneCurioRenderer<S extends HumanoidRenderState & GeoRenderState> extends GeoCurioRenderer<TricorneItem, S> {

    public TricorneCurioRenderer() {
        super(new TricorneArmorRenderer<S>(new GenericArmorModel<>("tricorne")));
    }
}