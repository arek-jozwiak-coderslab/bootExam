package pl.coderslab.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.PostRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;
    Tag t1, t2;
    Post p1, p2;
    Set<Tag> tags;

    @Before
    public void setUp() {
        t1 = Tag.builder().name("t1").active(true).build();
        t2 = Tag.builder().name("t2").active(true).build();
        t1 = entityManager.persistAndFlush(t1);
        t2 = entityManager.persistAndFlush(t2);
        tags = new HashSet<>(Arrays.asList(t1, t2));
        p1 = Post.builder().title("t1").description("d1").content("c1").tags(tags).build();
        p1 = entityManager.persistAndFlush(p1);
        p2 = Post.builder().title("t1").description("d1").content("c1").build();
        log.info(p1.toString());
    }

    @Test
    public void findAllByTags() {
        // when
        List<Post> posts = postRepository.findAllByTags(t1);
        // then
        assertThat(posts).containsExactly(p1);
    }

    @Test
    public void findAllByTagsName() {
        // when
        List<Post> posts = postRepository.findAllByTagsName(t1.getName());
        // then
        assertThat(posts).contains(p1);
        assertThat(posts).doesNotContain(p2);
    }

    @Test
    public void findPostsByTagsIn() {
        // when
        List<Post> posts = postRepository.findPostsByTagsIn(tags);
        // then
        assertThat(posts).contains(p1);
    }

}
