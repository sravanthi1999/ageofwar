package com.game.ageofwar.modal;

import org.springframework.stereotype.Component;

@Component

public class Platoon {
    PlatoonType name;
    int units;

    Platoon() {
    }

    public Platoon(PlatoonType name, int units) {
        this.name = name;
        this.units = units;
    }

    public Platoon(String name, int units) {
        this.name = PlatoonType.valueOf(name.trim());
        this.units = units;
    }

    public Platoon(String platoon) {
        String[] parts = platoon.split("#");
        this.name = PlatoonType.valueOf(parts[0].trim());
        this.units = Integer.parseInt(parts[1].trim());
    }

    public PlatoonType getName() {
        return name;
    }

    public void setName(PlatoonType name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
