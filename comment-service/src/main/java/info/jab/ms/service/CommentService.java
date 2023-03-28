package info.jab.ms.service;

import info.jab.ms.domain.Comment;
import java.util.List;
public interface CommentService {

    List<Comment> getCommentsForPost(Long postId);
}