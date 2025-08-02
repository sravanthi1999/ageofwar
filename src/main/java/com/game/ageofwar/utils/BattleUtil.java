package com.game.ageofwar.utils;

import com.game.ageofwar.modal.Platoon;
import org.springframework.stereotype.Component;

@Component
public class BattleUtil {

    public boolean isDefeated(Platoon self, Platoon opponent){
        boolean isPowerful = self.getName().isDefeats(opponent.getName());
        return isPowerful ? (opponent.getUnits() < self.getUnits() * 2) : self.getUnits() > opponent.getUnits();
    }
}

