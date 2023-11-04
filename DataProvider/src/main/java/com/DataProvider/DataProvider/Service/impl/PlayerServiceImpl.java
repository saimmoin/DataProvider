package com.DataProvider.DataProvider.Service.impl;

import com.DataProvider.DataProvider.DTO.PlayerDTO;
import com.DataProvider.DataProvider.DTO.ResponseDTO;
import com.DataProvider.DataProvider.Entity.Address;
import com.DataProvider.DataProvider.Entity.Player;
import com.DataProvider.DataProvider.Repository.addressRepo;
import com.DataProvider.DataProvider.Repository.playerRepo;
import com.DataProvider.DataProvider.Service.PlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    playerRepo repo;

    @Autowired
    addressRepo addrRepo;

    @Override
    public ResponseDTO savePlayer(PlayerDTO playerDTO) {
        try {
            saveAddress(playerDTO);
            Player player = new Player();
            player.setName(playerDTO.getName());
            player.setProfession(player.getProfession());
            player = repo.save(player);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage("Player saved sucessfully!");
            responseDTO.setBody(player);
            return responseDTO;
        } catch(Exception exp) {
            System.out.println(exp);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusCode(HttpStatus.OK);
            responseDTO.setMessage(exp.getMessage());
            responseDTO.setBody(exp);
            return responseDTO;
        }
    }
    private void saveAddress(PlayerDTO playerDTO) {
        Address address = new Address();
        address.setAddressDetail(playerDTO.getAddress());
        addrRepo.save(address);
        System.out.println(playerDTO.getAddress()+" saved successfully!");
    }
}
