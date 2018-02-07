package pl.coderslab.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTags(Tag tag);
    
    List<Post> findAllByTagsName(String tagName);
    
    List<Post> findPostsByTagsIn(Set<Tag> tags);

}