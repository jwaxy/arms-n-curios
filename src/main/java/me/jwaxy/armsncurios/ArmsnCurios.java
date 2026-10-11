package me.jwaxy.armsncurios;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ArmsnCurios.MODID)
public class ArmsnCurios {
    public static final String MODID = "armsncurios";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ArmsnCurios(IEventBus modEventBus, ModContainer modContainer) {
    }
}
