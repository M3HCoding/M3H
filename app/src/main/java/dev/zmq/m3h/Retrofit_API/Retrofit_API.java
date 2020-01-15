package dev.zmq.m3h.Retrofit_API;

import dev.zmq.m3h.URL.URL_Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_API {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit_API() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl( URL_Constant.BASE_URL1 )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build();
        }
        return retrofit;
    }
}
