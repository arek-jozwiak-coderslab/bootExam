package pl.coderslab.service;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class FakerService {
    FakerService() throws JSONException{
        this.regenerate();
    }

    private ArrayList<JSONObject> todayGames = new ArrayList<>();
    public ArrayList<JSONObject> getTodayGames() {
        return todayGames;
    }

    @Scheduled(fixedRate = 5000)
    public void regenerate() throws JSONException {
        Faker faker = new Faker();
        todayGames.clear();
        for (int i = 0; i < 10; i++) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("firstTeam", faker.team().name());
            oJsonInner.put("betFirst", faker.number().randomDouble(2, 1, 4));
            oJsonInner.put("betX", faker.number().randomDouble(2, 1, 3));
            oJsonInner.put("betSecond", faker.number().randomDouble(2, 1, 3));
            oJsonInner.put("secondTeam", faker.team().name());
            oJsonInner.put("sport", faker.team().sport());
            todayGames.add(oJsonInner);

        }

    }
}
