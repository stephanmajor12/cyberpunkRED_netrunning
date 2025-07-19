package org.major.Essentials;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlayerLoader {
    public static List<Player> loadPlayers(String filename) throws IOException {
        String path = "./players/" + filename + ".json";
        String content = Files.readString(Path.of(path));
        JSONObject root = new JSONObject(content);

        JSONArray playerArray = root.getJSONArray("players");
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerArray.length(); i++) {
            JSONObject obj = playerArray.getJSONObject(i);
            String name = obj.getString("name");
            int iface = obj.getInt("interface");

            players.add(new Player(name, iface));
        }

        return players;
    }
}
