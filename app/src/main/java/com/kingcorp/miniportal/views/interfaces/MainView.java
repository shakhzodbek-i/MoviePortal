package com.kingcorp.miniportal.views.interfaces;

import com.kingcorp.miniportal.models.MovieFullInfo;
import com.kingcorp.miniportal.models.MoviesListData;

public interface MainView {
    void showMovieList(MoviesListData moviesList);

    void openMovieFullInfo(MovieFullInfo movie);

    void showProgress();

    void hideProgress();

    void showNoInternetConnection();

    void showFooterLoading();

    void hideFooterLoading();

    void showFooterNoInternetConnection();
}
