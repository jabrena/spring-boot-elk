package info.jab.ms.controller;

import info.jab.ms.domain.Post;
import info.jab.ms.domain.PostWithComments;
import info.jab.ms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/resttemplate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostWithComments> getPostRestTemplate(@PathVariable Long id) {
        Optional<PostWithComments> optionalPostWithComments = postService.getPost(id);
        if (!optionalPostWithComments.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalPostWithComments.get());
    }

    @GetMapping(path = "/webclient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostWithComments> getPostWebClient(@PathVariable Long id) {
        Optional<PostWithComments> optionalPostWithComments = postService.getPostWebClient(id);
        if (!optionalPostWithComments.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalPostWithComments.get());
    }

}