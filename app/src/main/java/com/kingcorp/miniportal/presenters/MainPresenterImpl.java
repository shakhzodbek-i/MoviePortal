package com.kingcorp.miniportal.presenters;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kingcorp.miniportal.listeners.OnMoviePosterClickListener;
import com.kingcorp.miniportal.models.MovieFullInfo;
import com.kingcorp.miniportal.models.MoviesListData;
import com.kingcorp.miniportal.presenters.interfaces.MainPresenter;
import com.kingcorp.miniportal.repository.MoviesRepository;
import com.kingcorp.miniportal.views.interfaces.MainView;

public class MainPresenterImpl implements MainPresenter {

    private MoviesRepository mRepository;
    private LiveData<MovieFullInfo> mMovieFullInfo;
    private LiveData<MoviesListData> mMoviesList;
    private LiveData<Boolean> mIsInternetWorking;
    private MainView mView;
    private int mCurrentPage;

    private OnMoviePosterClickListener
            mMovieClickListener = movieId -> mRepository.loadMovieById(movieId);

    public MainPresenterImpl(MainView view) {
        this.mRepository = MoviesRepository.getInstance();
        this.mMovieFullInfo = mRepository.getMovieFullInfo();
        this.mMoviesList = mRepository.getMoviesList();
        this.mIsInternetWorking = mRepository.isInternetWorking();
        this.mView = view;
    }

    @Override
    public void loadMovieList(int page) {
        mCurrentPage = page;
        if(page == 1)
            mView.showProgress();
        else
            mView.showFooterLoading();
        mRepository.loadMoviesList(page);
    }

    @Override
    public void loadMovieById(int id) {
        mView.showProgress();
        mRepository.loadMovieById(id);
    }

    @Override
    public void setupObservers(LifecycleOwner owner) {
        mMoviesList.observe(owner, moviesListData -> {
            if (moviesListData != null) {
                if (mCurrentPage == 1)
                    mView.hideProgress();
                else
                    mView.hideFooterLoading();

                mView.showMovieList(moviesListData);
            }
        });

        mMovieFullInfo.observe(owner, movieFullInfo -> {
            if (movieFullInfo != null) {
                mView.hideProgress();
                mView.openMovieFullInfo(movieFullInfo);
            }
        });

        mIsInternetWorking.observe(owner, isInternetWorking -> {
            if (!isInternetWorking) {
                if (mCurrentPage == 1)
                    mView.showNoInternetConnection();
                else
                    mView.showFooterNoInternetConnection();
            }
        });
    }

    @Override
    public OnMoviePosterClickListener getMovieClickListener() {
        return mMovieClickListener;
    }
}
