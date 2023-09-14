package com.example.truecaller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebsiteContentRepository {

    private final WebsiteContentService contentService;

    public WebsiteContentRepository(WebsiteContentService contentService) {
        this.contentService = contentService;
    }
    public void fetchWebsiteContent(String url, OnTextFound onTextFound) {
        Call<String> call = contentService.fetchWebsiteContent(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String htmlContent = response.body();


                    // Parse the HTML content using Jsoup
                    Document doc = Jsoup.parse(htmlContent);

                    // Remove images from the HTML content
                    Elements imgElements;
                    imgElements = doc.select("img");
                    for (Element img : imgElements) {
                        img.remove();
                    }

                    // Get the text content after removing images
                    String textContent = doc.text();

                    // Set the filtered text content in LiveData
                    onTextFound.onTextFound(textContent);
                } else {
                    Log.e("WebsiteContentRepository", "Failed to fetch website content.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("WebsiteContentRepository", "Network request failed: " + t.getMessage());
            }
        });
    }


}

