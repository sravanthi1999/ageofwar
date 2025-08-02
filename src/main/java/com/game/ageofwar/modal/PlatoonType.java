package com.game.ageofwar.modal;

import java.util.EnumSet;
import java.util.Set;

public enum PlatoonType {
    Militia,
    Spearmen,
    LightCavalry,
    HeavyCavalry,
    CavalryArcher,
    FootArcher;

    private Set<PlatoonType> defeats;

    static {
        Militia.defeats = EnumSet.of(Spearmen, LightCavalry);
        Spearmen.defeats = EnumSet.of(LightCavalry, HeavyCavalry);
        LightCavalry.defeats = EnumSet.of(FootArcher, CavalryArcher);
        HeavyCavalry.defeats = EnumSet.of(Militia, FootArcher, LightCavalry);
        CavalryArcher.defeats = EnumSet.of(Spearmen, HeavyCavalry);
        FootArcher.defeats = EnumSet.of(Militia, CavalryArcher);
    }

    public boolean isDefeats(PlatoonType other) {
        return defeats.contains(other);
    }
}
