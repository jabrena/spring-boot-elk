package info.jab.ms.controller;

import info.jab.ms.domain.Post;
import info.jab.ms.domain.PostWithComments;
import info.jab.ms.service.PostService;
import info.jab.ms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
       this.postService = postService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostWithComments> getPost(@PathVariable Long id) {
        PostWithComments postWithComments = postService.getPost(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(postWithComments);
    }
}