package br.com.luan.totenbase.extras;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;


/**
 * Created by Luan on 04/05/17.
 */

public class CustomGsonAdapter {
    public static class UserAdapter implements JsonSerializer<Object> {
        public JsonElement serialize(Object user, Type typeOfSrc,
                                     JsonSerializationContext context) {
            Gson gson = new Gson();
            JsonElement je = gson.toJsonTree(user);
            JsonObject jo = new JsonObject();
            jo.add("order", je);
            return jo;
        }
    }
}