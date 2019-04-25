package pl.coderslab.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import pl.coderslab.exception.BaseEntityNotFoundException;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.PostRepository;
@Service
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

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findOne(long id) {
        return Optional.ofNullable(postRepository.findOne(id)).orElseThrow(()->new BaseEntityNotFoundException(id));
    }

}
