package com.rafaelsegalla.workshopspringbootmongodb.config;

import com.rafaelsegalla.workshopspringbootmongodb.domain.Post;
import com.rafaelsegalla.workshopspringbootmongodb.domain.User;
import com.rafaelsegalla.workshopspringbootmongodb.repository.PostRepository;
import com.rafaelsegalla.workshopspringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();

        User maria = new User(null, "Maria", "maria@gmail.com");
        User alex = new User(null, "Alex", "alex@gmail.com");
        User bob = new User(null, "Bob", "bob@gmail.com");
        this.userRepository.saveAll(Arrays.asList(maria, alex, bob));


        this.postRepository.deleteAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Post post1 = new Post(null, simpleDateFormat.parse("11/06/2022"), "Good Morning!", "I woke up tired today", maria);
        Post post2 = new Post(null, simpleDateFormat.parse("24/07/2022"), "I'm leaving", "Goodbye", alex);
        Post post3 = new Post(null, simpleDateFormat.parse("16/08/2022"), "Good Morning", "Good coffee", bob);
        this.postRepository.saveAll(Arrays.asList(post1, post2, post3));

    }

    private void createPosts() throws Exception {

    }
}
