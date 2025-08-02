package com.game.ageofwar.modal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
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

}
