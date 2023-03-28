package info.jab.ms.service;

import info.jab.ms.domain.Post;
import info.jab.ms.domain.PostWithComments;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getPosts();

    Optional<PostWithComments> getPost(Long id);

    Optional<PostWithComments> getPostWebClient(Long id);
}