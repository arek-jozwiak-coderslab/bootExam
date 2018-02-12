package pl.coderslab.web.rest;

import com.github.javafaker.Faker;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.service.FakerService;

@RestController
@RequestMapping("/api")
public class FakerResource {

    private final FakerService fakerService;

    FakerResource(FakerService fakerService) {
        this.fakerService = fakerService;
    }

    @GetMapping(path= "/fake-today-games")
    public String sample() {
        return fakerService.getTodayGames().toString();
    }

    @RequestMapping(path = "/fake-test", method = RequestMethod.GET)
    @ResponseBody
    public String test() throws JSONException {
        JSONObject oJsonInner = new JSONObject();
        Faker faker = new Faker();
        oJsonInner.put("firstTeam", faker.team().name());
        return oJsonInner.toString();
    }
}