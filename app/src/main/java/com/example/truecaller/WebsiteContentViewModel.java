package com.example.truecaller;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsiteContentViewModel extends ViewModel {

    private final WebsiteContentRepository repository;

    // LiveData for content and task results
    private final MutableLiveData<String> contentResult = new MutableLiveData<>();
    private final MutableLiveData<Character> task1Result = new MutableLiveData<>();
    private final MutableLiveData<List<Character>> task2Result = new MutableLiveData<>();
    private final MutableLiveData<Map<String, Integer>> task3Result = new MutableLiveData<>();

    public WebsiteContentViewModel(WebsiteContentRepository repository) {
        this.repository = repository;
    }

    public LiveData<String> getContentResult() {
        return contentResult;
    }

    public LiveData<Character> getTask1Result() {
        return task1Result;
    }

    public LiveData<List<Character>> getTask2Result() {
        return task2Result;
    }

    public LiveData<Map<String, Integer>> getTask3Result() {
        return task3Result;
    }

    public void fetchWebsiteContent(String url) {
        repository.fetchWebsiteContent(url, text -> {
            contentResult.postValue(text);
            char task1ResultValue = performTask1(text);
            task1Result.setValue(task1ResultValue);

            // Similarly, implement and update task2Result and task3Result
            List<Character> task2ResultValue = performTask2(text);
            task2Result.setValue(task2ResultValue);

            Map<String, Integer> task3ResultValue = performTask3(text);
            task3Result.setValue(task3ResultValue);
        });

        // Implement tasks (Task 1, Task 2, Task 3) here and update task LiveData accordingly
        // For example, to update task1Result with a character value:

    }

    private char performTask1(String content) {
        // Implement Task 1 logic to find the 15th character and return it
        // Example:
        Log.e("Content","Found -> "+content);
        if (content != null && content.length() >= 15) {
            return content.charAt(14);
        } else {
            return ' '; // Return a space character if the content is too short
        }
    }

    private List<Character> performTask2(String content) {
        // Implement Task 2 logic to find every 15th character and return them in a list
        // Example:
        List<Character> characters = new ArrayList<>();
        if (content != null && content.length() >= 15) {
            for (int i = 14; i < content.length(); i += 15) {
                characters.add(content.charAt(i));
            }
        }
        return characters;
    }

    private Map<String, Integer> performTask3(String content) {
        // Implement Task 3 logic to count words and return a map of word counts
        // Example:
        Map<String, Integer> wordCountMap = new HashMap<>();
        if (content != null) {
            String[] words = content.split("\\s+"); // Split by whitespace
            for (String word : words) {
                word = word.toLowerCase(); // Convert to lowercase for case insensitivity
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);

            }
        }
        return wordCountMap;
    }
}
