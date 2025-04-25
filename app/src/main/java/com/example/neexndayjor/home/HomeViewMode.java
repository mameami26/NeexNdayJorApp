package com.example.neexndayjor.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewMode extends ViewModel {

    private final MutableLiveData<String> welcomeText = new MutableLiveData<>("Welcome to Neex Nday Jor!");

    public LiveData<String> getWelcomeText() {
        return welcomeText;
    }

    public void updateText(String newText) {
        welcomeText.setValue(newText);
    }
}
