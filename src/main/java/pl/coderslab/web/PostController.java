package pl.coderslab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.model.User;
import pl.coderslab.repository.PostRepository;
import pl.coderslab.repository.TagRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/post")
public class PostController {

    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public PostController(UserRepository userRepository, TagRepository tagRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/list")
    @ResponseBody
    public String lisr() {
        return "is ok";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("post", new Post());
        return "post/add";
    }

    @PostMapping("/add")
    public String submitForm(@Valid @ModelAttribute Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "post/add";
        }
        postRepository.save(post);
        return "redirect:/post/list";
    }

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @ModelAttribute("allTags")
    public List<Tag> allTags() {
        return tagRepository.findAll();
    }

}
