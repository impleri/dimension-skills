package net.impleri.dimensionskills.fabric;

import net.fabricmc.api.ModInitializer;
import net.impleri.dimensionskills.DimensionSkills;

public class DimensionSkillsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DimensionSkills.init();
    }
}
