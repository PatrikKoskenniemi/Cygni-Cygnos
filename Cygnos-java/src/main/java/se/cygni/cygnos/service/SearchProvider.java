package se.cygni.cygnos.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import se.cygni.cygnos.model.Track;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class SearchProvider {

    public List<Track> search(String query) throws Exception {

        List<Track> tracks = new ArrayList<>();

        URI uri = new URIBuilder("https://api.spotify.com/v1/search")
                .addParameter("q", query)
                .addParameter("type", "track")
                .build();

        String response = Request.Get(
                uri).
                execute().
                returnContent().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);

        JsonNode items = rootNode.path("tracks").path("items");

        Iterator<JsonNode> elements = items.elements();
        while(elements.hasNext()){
            JsonNode item = elements.next();
            if (item.path("type").asText().equals("track")) {
                tracks.add(new Track(
                        getValue(item, "id"),
                        getValue(item, "preview_url"),
                        getValue(item.path("artists").get(0), "name"),
                        getValue(item, "name"),
                        getValue(item, "album", "name")
                ));
            }
        }

        return tracks;
    }



    public List<Track> searchforDemo() throws Exception {

        List<Track> tracks = new ArrayList<>();

        ArrayList<String> randomsongs = new ArrayList<>();

        randomsongs.add("4ePP9So5xRzspjLFVVbj90");
        randomsongs.add("7EFVJfuaqhIIvzN1HZpEpth");
        randomsongs.add("7H6ev70Weq6DdpZyyTmUXk");
        randomsongs.add("7v0kHF6tXT8ekVrJAbxpph");
        randomsongs.add("1Je1IMUlBXcx1Fz0WE7oPT");
        randomsongs.add("2Cy7QY8HPLk925AyNAt6OG");
        randomsongs.add("57bgtoPSgt236HzfBOd8kj");
        randomsongs.add("0mWiuXuLAJ3Brin3Or2x6v");
        randomsongs.add("0AIfr4oi192dWLRJZrRz1O");

        Random rand = new Random();
        String songs = new String();

        for (int i = 0; i<4; i++) {

            if (i == 3) {
                songs += randomsongs.get(rand.nextInt(randomsongs.size()));
            } else {
                songs += randomsongs.get(rand.nextInt(randomsongs.size()))+ ",";
            }

        }

        URI uri = new URIBuilder("https://api.spotify.com/v1/tracks")
                .addParameter("ids", songs)
                .build();

        String response = Request.Get(
                uri).
                execute().
                returnContent().asString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);

        JsonNode items = rootNode.path("tracks");

        Iterator<JsonNode> elements = items.elements();
        while(elements.hasNext()){
            JsonNode item = elements.next();
            if (item.path("type").asText().equals("track")) {
                tracks.add(new Track(
                        getValue(item, "id"),
                        getValue(item, "preview_url"),
                        getValue(item.path("artists").get(0), "name"),
                        getValue(item, "name"),
                        getValue(item, "album", "name"),
                        getValue(item.path("album").get(0), "images", "url")
                ));
            }
        }

        return tracks;
    }

    private String getAlbumImg(JsonNode node) {

        return node.path("album").path("images").get(0).path("url").asText();
    }

    private String getValue(JsonNode node, String...elements) {
        JsonNode endNode = node;
        for (String element : elements) {
            endNode = endNode.path(element);
        }
        return endNode.asText();
    }


}
