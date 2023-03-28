package info.jab.ms.service;

import info.jab.ms.domain.Comment;
import info.jab.ms.domain.Post;
import info.jab.ms.domain.PostWithComments;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Value("${comment-service.base-url}")
    private String commentServiceBaseUrl;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private static final List<Post> POSTS = new ArrayList<>();

    static {

        POSTS.add(Post.builder()
                .id(1L)
                .title("Post 1")
                .content("Post 1 content")
                .publishDateTime(OffsetDateTime.now(ZoneOffset.UTC))
                .build());

        POSTS.add(Post.builder()
                .id(2L)
                .title("Post 2")
                .content("Post 2 content")
                .publishDateTime(OffsetDateTime.now(ZoneOffset.UTC))
                .build());
    }

    @Override
    public List<Post> getPosts() {
        log.info("Finding details of all posts");
        return POSTS;
    }

    @Override
    public Optional<PostWithComments> getPost(Long id) {

        log.info("Finding with restTemplate details of post with id {}", id);

        Optional<Post> optionalPost = POSTS.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        Optional<PostWithComments> optionalPostWithComments = optionalPost.map(this::asPostWithComments);
        optionalPostWithComments.ifPresent(postWithComments -> {
            List<Comment> comments = this.findCommentsForPost(postWithComments);
            postWithComments.setComments(comments);
        });

        return optionalPostWithComments;
    }

    @Override
    public Optional<PostWithComments> getPostWebClient(Long id) {

        log.info("Finding with webClient details of post with id {}", id);

        Optional<Post> optionalPost = POSTS.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        Optional<PostWithComments> optionalPostWithComments = optionalPost.map(this::asPostWithComments);
        optionalPostWithComments.ifPresent(postWithComments -> {
            List<Comment> comments = this.findWithWebClientCommentsForPost(postWithComments);
            postWithComments.setComments(comments);
        });

        return optionalPostWithComments;
    }

    private List<Comment> findCommentsForPost(Post post) {

        log.info("Call with RestTemplate : Finding comments of post with id {}", post.getId());

        String url = UriComponentsBuilder.fromHttpUrl(commentServiceBaseUrl)
                .path("comments")
                .queryParam("postId", post.getId())
                .toUriString();

        ResponseEntity<List<Comment>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {
                });

        List<Comment> comments = Objects.isNull(response.getBody()) ? new ArrayList<>() : response.getBody();
        log.info("Call with RestTemplate : Found {} comment(s) of post with id {}", comments.size(), post.getId());
        return comments;
    }

    private List<Comment> findWithWebClientCommentsForPost(Post post) {

        log.info("Call with WebClient : Finding comments of post with id {}", post.getId());

        Mono<List<Comment>> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/comments")
                        .queryParam("postId", post.getId())
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {});

        List<Comment> comments = response.block();
        log.info("Call with WebClient : Found {} comment(s) of post with id {}", comments.size(), post.getId());
        return comments;
    }

    private PostWithComments asPostWithComments(Post post) {
        PostWithComments postWithComments = new PostWithComments();
        BeanUtils.copyProperties(post, postWithComments);
        return postWithComments;
    }
}