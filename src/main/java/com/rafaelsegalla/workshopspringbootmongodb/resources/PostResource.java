package com.rafaelsegalla.workshopspringbootmongodb.resources;

import com.rafaelsegalla.workshopspringbootmongodb.domain.Post;
import com.rafaelsegalla.workshopspringbootmongodb.resources.util.URL;
import com.rafaelsegalla.workshopspringbootmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @GetMapping(value = "/filter")
    public ResponseEntity<List<Post>> filter(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDateString,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDateString) {
        text = URL.decodeParams(text);
        Date minDate = URL.convertDate(minDateString, new Date(0L));
        Date maxDate = URL.convertDate(maxDateString, new Date());
        List<Post> postList = postService.filter(text, minDate, maxDate);
        return ResponseEntity.ok().body(postList);
    }
}
