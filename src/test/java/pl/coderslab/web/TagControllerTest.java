package pl.coderslab.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import pl.coderslab.model.Post;
import pl.coderslab.model.Tag;
import pl.coderslab.repository.TagRepository;
import pl.coderslab.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TagController.class)
public class TagControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TagRepository tagRepository;
    @MockBean
    private UserService userService;

    Tag t1, t2, t3;
    Post p1, p2;
    List<Tag> tags;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        t1 = Tag.builder().name("t1").active(true).build();
        t2 = Tag.builder().name("t2").active(true).build();
        t3 = Tag.builder().name("java").active(true).build();
        tags = Arrays.asList(t1, t2);
        p1 = Post.builder().title("t1").description("d1").content("c1").tags(new HashSet<>(tags)).build();
        p2 = Post.builder().title("t1").description("d1").content("c1").build();

        when(this.tagRepository.findAll()).thenReturn(tags);
        when(this.tagRepository.findOne(1L)).thenReturn(t3);

    }

    @Test
    public void test_listAction_contains_model_list() throws Exception {
        assertThat(this.tagRepository).isNotNull();
        mockMvc.perform(get("/tag/all")).andExpect(model().attributeExists("list"))
                .andExpect(model().attribute("list", hasSize(2)))
                .andExpect(model().attribute("list",
                        hasItem(allOf(hasProperty("active", is(true)), hasProperty("name", is("t1"))))))
                .andExpect(model().attribute("list",
                        hasItem(allOf(hasProperty("active", is(true)), hasProperty("name", is("t2"))))))
                .andExpect(view().name("tag/all"));
    }

    @Test
    public void test_show_student() throws Exception {
        mockMvc.perform(get("/tag/{id}/show", 1L)).andExpect(status().isOk()).andExpect(view().name("tag/show"))
                .andExpect(model().attributeExists("entity"))
                .andExpect(model().attribute("entity", hasProperty("name", is("java"))))

        ;
    }

    @Test
    public void when_save_not_valid_data_then_show_form_again() throws Exception {
        mockMvc.perform(post("/tag/add").param("active", "true")).andExpect(view().name("tag/add"));
    }

    @Test
    public void when_save_valid_data_then_redirect() throws Exception {
        mockMvc.perform(post("/tag/add").param("name", RandomStringUtils.randomAlphabetic(10)))
                .andExpect(redirectedUrl("/tag/all"));
    }

}