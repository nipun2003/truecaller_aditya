package com.example.truecaller;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;



import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private WebsiteContentViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView contentTextView = findViewById(R.id.content);
        TextView char15ResultTextView = findViewById(R.id.char15Result);
        TextView every15thCharResultTextView = findViewById(R.id.every15thCharResult);
        TextView wordCountResultTextView = findViewById(R.id.wordCountResult);
        Button loadButton = findViewById(R.id.loadButton);

        // Create the Retrofit service instance
        WebsiteContentService contentService = RetrofitService.create();

        // Create the Repository
        WebsiteContentRepository repository = new WebsiteContentRepository(contentService);

        // Create the ViewModel
        viewModel = new ViewModelProvider(this, new ViewModelFactory(repository)).get(WebsiteContentViewModel.class);

        // Set click listener for the "Load Content" button
        loadButton.setOnClickListener(view -> loadWebsiteContent());

        // Observe LiveData for content and task results
        viewModel.getContentResult().observe(this, content -> contentTextView.setText("Website Content:\n" + content));


        viewModel.getTask1Result().observe(this, charResult -> char15ResultTextView.setText("15th Character Result:\n" + charResult));

        viewModel.getTask2Result().observe(this, charListResult -> every15thCharResultTextView.setText("Every 15th Character Result:\n" + charListResult));

        viewModel.getTask3Result().observe(this, wordCountResult -> {
            StringBuilder wordCountText = new StringBuilder("Word Count Result:\n");
            for (Map.Entry<String, Integer> entry : wordCountResult.entrySet()) {
                wordCountText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            wordCountResultTextView.setText(wordCountText.toString());
        });
    }

    private void loadWebsiteContent() {
        String url = "https://www.truecaller.com/blog/life-at-truecaller/life-as-an-android-engineer";
        viewModel.fetchWebsiteContent(url);
    }
}
