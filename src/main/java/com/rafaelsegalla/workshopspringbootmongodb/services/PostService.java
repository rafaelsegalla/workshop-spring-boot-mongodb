package com.rafaelsegalla.workshopspringbootmongodb.services;

import com.rafaelsegalla.workshopspringbootmongodb.domain.Post;
import com.rafaelsegalla.workshopspringbootmongodb.repository.PostRepository;
import com.rafaelsegalla.workshopspringbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> post = this.postRepository.findById(id);

        if (!post.isPresent()) {
            throw new ObjectNotFoundException("Post not found");
        }

        return post.get();
    }
}
