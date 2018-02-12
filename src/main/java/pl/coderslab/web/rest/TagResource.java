package pl.coderslab.web.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.exception.BaseEntityNotFoundException;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.TagRepository;

@RequestMapping(path = "/api/tag")
@RestController
public class TagResource {

    private final TagRepository tagRepository;

    TagResource(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @RequestMapping(method=GET)
    List<Tag> tags() {
        return tagRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method=GET)
    Tag carById(@PathVariable long id) {
        return Optional.ofNullable(tagRepository.findOne(id)).orElseThrow(() -> new BaseEntityNotFoundException(id));

    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    Tag createTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    Tag changeTagName(@RequestBody Tag tag) {
        if (tag.getId() == null) {
            throw new BaseEntityNotFoundException("Tag with given id not found");
        }

        return tagRepository.save(tag);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    @ResponseStatus(NO_CONTENT)
    private void deleteTag(@PathVariable("id") long id) {
        tagRepository.delete(id);
    }
}
