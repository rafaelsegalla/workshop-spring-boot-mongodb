package com.rafaelsegalla.workshopspringbootmongodb.services;

import com.rafaelsegalla.workshopspringbootmongodb.domain.User;
import com.rafaelsegalla.workshopspringbootmongodb.dto.UserDTO;
import com.rafaelsegalla.workshopspringbootmongodb.repository.UserRepository;
import com.rafaelsegalla.workshopspringbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent()) {
            throw new ObjectNotFoundException("User not found");
        }

        return user.get();
    }

    public User insert(User user) {
       return this.userRepository.insert(user);
    }

    public void delete(String id) {
        this.findById(id);
        this.userRepository.deleteById(id);
    }

    public User update(User user) {
        User originalUser = this.findById(user.getId());
        this.updateData(originalUser, user);
        return this.userRepository.save(originalUser);
    }

    private void updateData(User originalUser, User user) {
        originalUser.setName(user.getName())
                .setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO user) {
        return new User(user.getId(), user.getName(), user.getEmail());
    }
}
