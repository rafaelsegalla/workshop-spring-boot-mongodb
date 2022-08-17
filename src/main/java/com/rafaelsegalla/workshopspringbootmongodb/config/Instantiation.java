package com.rafaelsegalla.workshopspringbootmongodb.config;

import com.rafaelsegalla.workshopspringbootmongodb.domain.User;
import com.rafaelsegalla.workshopspringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();

        User maria = new User(null, "Maria", "maria@gmail.com");
        User alex = new User(null, "Alex", "alex@gmail.com");
        User bob = new User(null, "Bob", "bob@gmail.com");

        this.userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}
