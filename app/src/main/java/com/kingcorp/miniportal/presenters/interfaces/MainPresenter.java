package com.kingcorp.miniportal.presenters.interfaces;

import androidx.lifecycle.LifecycleOwner;

import com.kingcorp.miniportal.listeners.OnMoviePosterClickListener;

public interface MainPresenter {
    void loadMovieList(int page);

    void loadMovieById(int id);

    void setupObservers(LifecycleOwner owner);

    OnMoviePosterClickListener getMovieClickListener();
}
