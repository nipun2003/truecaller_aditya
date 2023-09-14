package com.example.truecaller;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;



public class ViewModelFactory implements ViewModelProvider.Factory {

    private final WebsiteContentRepository repository;

    public ViewModelFactory(WebsiteContentRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WebsiteContentViewModel.class)) {
            return (T) new WebsiteContentViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
