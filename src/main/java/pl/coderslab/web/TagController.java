package pl.coderslab.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.TagRepository;

@Controller
@RequestMapping("/tag")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("list", tagRepository.findAll());
        return "tag/all";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable long id) {
        model.addAttribute("entity", tagRepository.findOne(id));
        return "tag/show";
    }

    @GetMapping("/{id}/change")
    public String change(@PathVariable long id) {
        Tag t = tagRepository.findOne(id);
        t.setActive(!t.isActive());
        tagRepository.save(t);
        return "redirect:/tag/all";
    }

    @GetMapping("/{id}/posts")
    public String posts(Model model, @PathVariable long id) {
        model.addAttribute("list", tagRepository.findOne(id).getPosts());
        return "tag/posts";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("tag", new Post());
        return "tag/add";
    }

    @PostMapping("/add")
    public String submitForm(@Valid @ModelAttribute Tag post, BindingResult result) {
        if (result.hasErrors()) {
            return "tag/add";
        }
        return "redirect:/tag/all";
    }

}
