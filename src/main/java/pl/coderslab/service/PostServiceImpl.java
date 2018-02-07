package pl.coderslab.service;

import java.util.List;
import java.util.Set;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.PostRepository;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllByTags(Tag tags) {
        return postRepository.findAllByTags(tags);
    }

    @Override
    public List<Post> findAllByTagsName(String tagName) {
        return postRepository.findAllByTagsName(tagName);
    }

    @Override
    public List<Post> findPostsByTagsIn(Set<Tag> tags) {
        return postRepository.findPostsByTagsIn(tags);
    }

}
