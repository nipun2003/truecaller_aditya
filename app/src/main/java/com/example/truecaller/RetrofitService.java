package com.example.truecaller;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://www.truecaller.com/";

    public static WebsiteContentService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(WebsiteContentService.class);
    }
}
