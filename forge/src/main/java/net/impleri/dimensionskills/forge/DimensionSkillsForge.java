package net.impleri.dimensionskills.forge;

import dev.architectury.platform.forge.EventBuses;
import net.impleri.dimensionskills.DimensionSkills;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DimensionSkills.MOD_ID)
public class DimensionSkillsForge {
    public DimensionSkillsForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(DimensionSkills.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DimensionSkills.init();
    }
}
