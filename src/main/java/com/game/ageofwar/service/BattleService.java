package com.game.ageofwar.service;

import com.game.ageofwar.modal.Platoon;
import com.game.ageofwar.utils.BattleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        battleUtil.findAllPermutesAndIdentifyMaxWinSeq(new ArrayList<>(List.of(team1PlatoonSeq)), 0, result, List.of(team2PlatoonSeq));
        /* TODO (optional) find the highest win seq from result and return */
        log.info("Total No of wining sequence combinations: {}", result.size());
        if (result.isEmpty()) return new String[]{"There is no chance of winning"};
        return result.get(0).toArray(new String[0]);
    }

}
