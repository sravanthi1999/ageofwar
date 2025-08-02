package com.game.ageofwar.utils;

import com.game.ageofwar.modal.Platoon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BattleUtil {


    private static final Logger log = LogManager.getLogger(BattleUtil.class);

    /**
     * find all platoon sequences where the win count is greater then 2
     */
    public void findAllPermutesAndIdentifyMaxWinSeq(List<String> platoonSeq, int index, List<List<String>> result, List<String> opponentPlatoonSeq) {
        if (index == platoonSeq.size() - 1) {
            int wins = getWinStreakCount(platoonSeq, opponentPlatoonSeq);
            if (wins >= 3) {
                log.debug("Platoon sequence win count:{}, self: {}, opponent: {}", wins, platoonSeq, opponentPlatoonSeq);
                result.add(new ArrayList<>(platoonSeq));
            }
            return;
        }

        for (int i = index; i < platoonSeq.size(); i++) {
            Collections.swap(platoonSeq, i, index);
            findAllPermutesAndIdentifyMaxWinSeq(platoonSeq, index + 1, result, opponentPlatoonSeq);
            Collections.swap(platoonSeq, i, index);
        }
    }

    /**
     * Identify the win count of the player platoon sequence
     */
    private int getWinStreakCount(List<String> selfPlatoonSeq, List<String> oppPlatoonSeq) {
        int winStreak = 0;
        for (int i = 0; i < selfPlatoonSeq.size(); i++) {
            if (isWinner(selfPlatoonSeq.get(i), oppPlatoonSeq.get(i)))
                winStreak++;
        }
        return winStreak;
    }

    private boolean isWinner(String selfPlatoon, String OppPlatoon) {
        return isDefeated(new Platoon(selfPlatoon), new Platoon(OppPlatoon));
    }

    public boolean isDefeated(Platoon self, Platoon opponent){
        boolean isPowerful = self.getName().isDefeats(opponent.getName());
        return isPowerful ? (opponent.getUnits() < self.getUnits() * 2) : self.getUnits() > opponent.getUnits();
    }
}

