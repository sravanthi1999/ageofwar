package com.game.ageofwar.controller;

import com.game.ageofwar.service.BattleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/war")
public class BattleController {

    private static final Logger log = LoggerFactory.getLogger(BattleController.class);
    @Autowired
    BattleService battleService;

    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>("Hello, \n Welcome to Age Of War", HttpStatus.OK);
    }

    @PostMapping("/fight")
    public ResponseEntity<Object>  winCount(@RequestBody String body) {
        log.debug("Battle Started");
        return new ResponseEntity<>(battleService.findFinalPlatoonSequence(body), HttpStatus.OK);
    }
}
