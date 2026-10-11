package me.jwaxy.armsncurios.client.curio.impl;

import com.geckolib.renderer.base.GeoRenderState;
import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import io.redspace.irons_artifice.item.CowboyHatItem;
import me.jwaxy.armsncurios.client.armor.impl.CowboyHatArmorRenderer;
import me.jwaxy.armsncurios.client.curio.GeoCurioRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class CowboyHatCurioRenderer<S extends HumanoidRenderState & GeoRenderState> extends GeoCurioRenderer<CowboyHatItem, S> {
    public CowboyHatCurioRenderer() {
        super(new CowboyHatArmorRenderer<>(new GenericArmorModel<>("cowboy_hat")));
    }
}