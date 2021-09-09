package data;

import com.google.gson.Gson;

class Transformer {

    static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    static <T> T fromJson(Class<T> clazz, String json) {
        return new Gson().fromJson(json, clazz);
    }

}
