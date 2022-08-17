package com.rafaelsegalla.workshopspringbootmongodb.resources;

import com.rafaelsegalla.workshopspringbootmongodb.domain.User;
import com.rafaelsegalla.workshopspringbootmongodb.dto.UserDTO;
import com.rafaelsegalla.workshopspringbootmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> userList = this.userService.findAll();
        List<UserDTO> userDTOList = userList.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOList);
    }
}
