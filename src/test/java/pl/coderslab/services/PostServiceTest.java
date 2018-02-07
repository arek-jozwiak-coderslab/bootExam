package pl.coderslab.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.PostRepository;
import pl.coderslab.service.PostService;
import pl.coderslab.service.PostServiceImpl;

@RunWith(SpringRunner.class)

public class PostServiceTest {

    private PostService service;

    @Mock
    private PostRepository repository;

    Tag t1, t2;
    Post p1, p2;
    Set<Tag> tags;

    @Before
    public void setUp() {
        service = new PostServiceImpl(repository);
        t1 = Tag.builder().name("t1").active(true).build();
        t2 = Tag.builder().name("t2").active(true).build();
        tags = new HashSet<>(Arrays.asList(t1, t2));
        p1 = Post.builder().title("t1").description("d1").content("c1").tags(tags).build();
        p2 = Post.builder().title("t2").description("d2").content("c2").build();
    }

    @Test
    public void findAllByTags() {
        // given
        when(repository.findAllByTags(t1)).thenReturn(Arrays.asList(p1));
        // when
        List<Post> posts = service.findAllByTags(t1);
        // then
        assertThat(posts).containsExactly(p1);
    }

    @Test
    public void findAllByTagsName() {
        // given
        when(repository.findAllByTagsName(t1.getName())).thenReturn(Arrays.asList(p1));
        // when
        List<Post> posts = service.findAllByTagsName(t1.getName());
        // then
        assertThat(posts).containsExactly(p1);
    }

    @Test
    public void findPostsByTagsIn() {
        // given
        when(repository.findPostsByTagsIn(tags)).thenReturn(Arrays.asList(p1));
        // when
        List<Post> posts = service.findPostsByTagsIn(tags);
        // then
        assertThat(posts).contains(p1);
        assertThat(posts).doesNotContain(p2);
    }

}
