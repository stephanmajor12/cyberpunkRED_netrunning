package org.major.Essentials;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SeedLoader {

    public static NetArchitecture loadSeed(String filename) throws IOException {
        String path = "./seeds/" + filename + ".json";
        String content = Files.readString(Path.of(path));
        JSONObject obj = new JSONObject(content);

        String name = obj.optString("name", filename + " Mainframe");
        JSONArray floorArray = obj.getJSONArray("floors");
        List<FloorNode> floors = new ArrayList<>();

        for (int i = 0; i < floorArray.length(); i++) {
            JSONObject floorObj = floorArray.getJSONObject(i);
            String type = floorObj.getString("type");
            JSONObject details = floorObj.getJSONObject("details");

            int dv = details.optInt("dv", -1);
            String password = details.optString("password", null);
            String iceType = details.optString("ice_type", null);
            int initiative = details.optInt("initiative", -1);
            String data = details.optString("data", null);

            int perception = details.optInt("perception", 0);
            int speed = details.optInt("speed", 0);
            int attack = details.optInt("attack", 0);
            int defense = details.optInt("defense", 0);
            int rez = details.optInt("rez", 0);

            String worth = details.optString("worth", null);
            String fileContent = details.optString("content", null);

            List<String> programs = jsonArrayToList(details.optJSONArray("programs"));
            List<String> controls = jsonArrayToList(details.optJSONArray("controls"));

            FloorNode node = new FloorNode(
                    type,
                    dv,
                    password,
                    iceType,
                    initiative,
                    programs,
                    controls,
                    data,
                    perception,
                    speed,
                    attack,
                    defense,
                    rez,
                    worth,
                    fileContent
            );

            floors.add(node);
        }

        return new NetArchitecture(name, floors);
    }

    private static List<String> jsonArrayToList(JSONArray array) {
        if (array == null) return Collections.emptyList();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
}
