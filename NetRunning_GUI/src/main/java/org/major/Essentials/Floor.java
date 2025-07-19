package org.major.Essentials;

import org.json.JSONObject;

public class Floor {
    public String type;
    public JSONObject details;

    public Floor(String type, JSONObject details) {
        this.type = type;
        this.details = details;
    }
}
