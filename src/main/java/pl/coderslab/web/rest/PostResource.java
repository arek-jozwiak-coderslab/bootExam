package pl.coderslab.web.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.cderslab.dto.PostDto;
import pl.coderslab.model.Post;
import pl.coderslab.repository.PostRepository;
import pl.coderslab.service.PostService;

@RestController
@Slf4j
@RequestMapping(path = "/api/posts")
public class PostResource {

    private final PostService postService;

    PostResource(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public PostDto getPost(@PathVariable Long id) {
        Post post = postService.findOne(id);
        return PostDto.builder().title(post.getTitle()).content(post.getContent())
                .description(post.getDescription()).author(post.getUser().getUsername()).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PostDto> posts() {
        List<Post> carsEntities = postService.findAll();
        return carsEntities.stream()
                .map(post -> new PostDto(post.getTitle(), post.getDescription(), post.getContent(),
                        String.join(post.getUser().getUsername(), " - ", post.getUser().getEmail())))
                .collect(Collectors.toList());

    }

}
