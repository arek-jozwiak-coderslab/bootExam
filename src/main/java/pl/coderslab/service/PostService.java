package pl.coderslab.service;

import java.util.List;
import java.util.Set;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;

public interface PostService {

    Post findOne(long id);
    
    List<Post> findAllByTags(Tag tags);

    List<Post> findAllByTagsName(String tagName);

    List<Post> findPostsByTagsIn(Set<Tag> tags);
    List<Post> findAll();
}
