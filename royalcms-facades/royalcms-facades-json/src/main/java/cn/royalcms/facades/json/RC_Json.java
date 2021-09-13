package cn.royalcms.facades.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.springframework.boot.json.GsonJsonParser;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class RC_Json {
    //禁止实例
    private RC_Json() {
        throw new UnsupportedOperationException();
    }

    public static String encode(Object object) {
        if (object instanceof List) {
            return new Gson().toJson(object);
        } else if (object instanceof Map) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            return gson.toJson(object);
        } else {
            return new Gson().toJson(object);
        }
    }

    public static Object decode(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Object.class);
    }

    public static Map<String, Object> decodeAsMap(String json) {
        return new GsonJsonParser().parseMap(json);
    }

    public static List<Object> decodeAsList(String json) {
        return new GsonJsonParser().parseList(json);
    }

    public static <T> T decode(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, (Type) classOfT);
    }

    public static <T> T decode(String json, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeOfT);
    }

    /**
     * Google Gson
     * @param jsonInString
     * @return boolean
     */
    public static boolean isJson(String jsonInString) {
        try {
            Gson gson = new Gson();
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(JsonSyntaxException ex) {
            return false;
        }
    }



}
