package servicios;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class JsonTransformer {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        return map;
    }

    public static JsonObject stringToJsonObject(String jsonString) {
        return new com.google.gson.JsonParser().parse(jsonString).getAsJsonObject();
    }

    public static JsonArray stringToJsonArray(String jsonString) {
        return new JsonParser().parse(jsonString).getAsJsonArray();
    }


}