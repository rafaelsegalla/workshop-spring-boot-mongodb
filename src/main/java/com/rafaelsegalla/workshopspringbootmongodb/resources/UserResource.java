package com.rafaelsegalla.workshopspringbootmongodb.resources;

import com.rafaelsegalla.workshopspringbootmongodb.domain.Post;
import com.rafaelsegalla.workshopspringbootmongodb.domain.User;
import com.rafaelsegalla.workshopspringbootmongodb.dto.UserDTO;
import com.rafaelsegalla.workshopspringbootmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
        User user = this.userService.fromDTO(userDTO);
        user = this.userService.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable String id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = this.userService.fromDTO(userDTO);
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }
}
