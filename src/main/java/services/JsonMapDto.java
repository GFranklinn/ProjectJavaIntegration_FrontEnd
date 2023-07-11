package services;

import com.google.gson.Gson;
import model.EntityModelDto;
import model.ObjectAbstractDto;
import java.util.Arrays;
import java.util.List;

import static services.HttpRest.getJsonDb;

public class JsonMapDto {

    public static <T extends ObjectAbstractDto> List<T> getListDb(Class<T[]> dtoClass, String endURL) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(getJsonDb(endURL), dtoClass));
    }

    public static <T extends ObjectAbstractDto> List<T> getListDb(Class<T[]> classDto, String endURL, String filter) {
        Gson gson = new Gson();

        return Arrays.asList(gson.fromJson(getJsonDb(endURL, filter), classDto));
    }
}

