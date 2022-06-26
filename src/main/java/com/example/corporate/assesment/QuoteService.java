package com.example.corporate.assesment;
import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteService extends RuntimeException {

    private final String api = "https://zenquotes.io/api/quotes";
    private String jsonQuotes = null;

    @RequestMapping(value = "/quote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Quote getQuote() {

        // api is consumed once
        if (jsonQuotes == null)
            // get quotes in json format
            jsonQuotes = getFamousQuotes(api);

        // extract a single quote from json
        Quote quoteObject = getQuotefromJSON(jsonQuotes);

        // return the Quote object as json
        return quoteObject;
    }

    // get all the quotes from api
    private String getFamousQuotes(String api) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(api, String.class);

        return result;
    }

    // get a random quote from the json string
    private Quote getQuotefromJSON(String jsonString) {
        JSONArray obj = new JSONArray(jsonString);

        // generate random number
        int randomNum = (int) (Math.random() * obj.length());

        // get famous person name and quote from json
        String name = obj.getJSONObject(randomNum).getString("a");
        String quote = obj.getJSONObject(randomNum).getString("q");

        // return Quote object
        return new Quote(name, quote);
    }
}
