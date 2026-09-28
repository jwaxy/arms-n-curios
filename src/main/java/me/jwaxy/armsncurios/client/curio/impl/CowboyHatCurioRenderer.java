package me.jwaxy.armsncurios.client.curio.impl;

import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import me.jwaxy.armsncurios.client.armor.impl.CowboyHatArmorRenderer;
import me.jwaxy.armsncurios.client.curio.GeoCurioRenderer;

public class CowboyHatCurioRenderer extends GeoCurioRenderer {
    public CowboyHatCurioRenderer() {
        super(
                new CowboyHatArmorRenderer(new GenericArmorModel<>("cowboy_hat")),
                "cowboy_hat"
        );
    }
}