package com.game.ageofwar.service;

import com.game.ageofwar.modal.Platoon;
import com.game.ageofwar.utils.BattleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BattleService {

    private static final Logger log = LogManager.getLogger(BattleService.class);
    @Autowired
    BattleUtil battleUtil;

    /* logic to find the number of wins */

    public String findFinalPlatoonSequence(String platoons) {
        String[] totalTeams = platoons.split("\r\n");
        log.debug("My Team: {} and Opponent Team: {}", totalTeams[0], totalTeams[1]);
        String[] platoonsList = maxWinStreakSeq(totalTeams[0].split(";"), totalTeams[1].split(";"));
        log.debug("Wining Sequence  {}", (Object) platoonsList);
        return String.join(";", platoonsList).trim();
    }

    public String[] maxWinStreakSeq(String[] team1PlatoonSeq, String[] team2PlatoonSeq) {
        List<List<String>> result = new ArrayList<>();
        log.debug("Started Finding all wining sequence combinations");
        findAllPermutesAndIdentifyMaxWinSeq(new ArrayList<>(List.of(team1PlatoonSeq)), 0, result, List.of(team2PlatoonSeq));
        /* TODO find the highest win seq from result and return */
        log.info("Total No of wining sequence combinations: {}", result.size());
        if (result.isEmpty()) return new String[]{"There is no chance of winning"};
        return result.get(0).toArray(new String[0]);
    }

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
     * Identify the win count for a self platoon sequence
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
        return battleUtil.isDefeated(new Platoon(selfPlatoon), new Platoon(OppPlatoon));
    }

}
