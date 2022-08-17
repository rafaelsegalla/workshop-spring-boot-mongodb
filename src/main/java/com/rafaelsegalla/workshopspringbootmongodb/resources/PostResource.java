package com.rafaelsegalla.workshopspringbootmongodb.resources;

import com.rafaelsegalla.workshopspringbootmongodb.domain.Post;
import com.rafaelsegalla.workshopspringbootmongodb.resources.util.URL;
import com.rafaelsegalla.workshopspringbootmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = this.postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/title-search")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParams(text);
        List<Post> postList = postService.findByTitle(text);
        return ResponseEntity.ok().body(postList);
    }
}
