package me.calebjones.spacelaunchnow.data.networking.error;

import java.io.IOException;
import java.lang.annotation.Annotation;

import me.calebjones.spacelaunchnow.data.networking.DataClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtil {
    public static SpaceLaunchNowError parseSpaceLaunchNowError(Response<?> response) {
        Converter<ResponseBody, SpaceLaunchNowError> converter = DataClient.getInstance().getSpaceLaunchNowRetrofit().responseBodyConverter(SpaceLaunchNowError.class, new Annotation[0]);

        SpaceLaunchNowError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new SpaceLaunchNowError();
        }

        return error;
    }
}
