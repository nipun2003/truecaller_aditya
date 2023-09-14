package com.example.truecaller;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebsiteContentService {

    @GET
    Call<String> fetchWebsiteContent(@Url String url);
}
