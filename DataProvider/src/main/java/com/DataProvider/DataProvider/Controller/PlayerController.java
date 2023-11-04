package com.DataProvider.DataProvider.Controller;

import com.DataProvider.DataProvider.DTO.PlayerDTO;
import com.DataProvider.DataProvider.DTO.ResponseDTO;
import com.DataProvider.DataProvider.Entity.Player;
import com.DataProvider.DataProvider.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "Player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @PostMapping("/save")
    public ResponseDTO addPlayer(@RequestBody PlayerDTO playerDTO){
        return playerService.savePlayer(playerDTO);

    }
}
