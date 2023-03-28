package info.jab.ms.service;

import info.jab.ms.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private static final List<Comment> COMMENTS = new ArrayList<>();

    static {
        COMMENTS.add(new Comment(1L,1L,"Awesome!"));
        COMMENTS.add(new Comment(2L,1L,"Perfect!"));
        COMMENTS.add(new Comment(3L,2L,"Best post ever!"));
    }

    @Override
    public List<Comment> getCommentsForPost(Long postId) {

        log.info("Finding comments of post with id {}", postId);

        List<Comment> comments = COMMENTS.stream()
                .filter(comment -> comment.postId().equals(postId))
                .collect(toList());

        log.info("Found {} comment(s) of post with id {}", comments.size(), postId);
        return comments;
    }
}