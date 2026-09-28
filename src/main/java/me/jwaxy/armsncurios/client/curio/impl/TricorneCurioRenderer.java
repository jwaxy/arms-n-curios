package me.jwaxy.armsncurios.client.curio.impl;

import io.redspace.irons_artifice.client.armor.GenericArmorModel;
import me.jwaxy.armsncurios.client.curio.GeoCurioRenderer;
import me.jwaxy.armsncurios.client.armor.impl.TricorneArmorRenderer;

public class TricorneCurioRenderer extends GeoCurioRenderer {
    public TricorneCurioRenderer() {
        super(
                new TricorneArmorRenderer(new GenericArmorModel<>("tricorne")),
                "tricorne"
        );
    }
}