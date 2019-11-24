package com.kingcorp.miniportal.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kingcorp.miniportal.models.MovieFullInfo;
import com.kingcorp.miniportal.models.MoviesListData;
import com.kingcorp.miniportal.models.MoviesListResponse;
import com.kingcorp.miniportal.models.SingleMovieResponse;
import com.kingcorp.miniportal.utils.Constants;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private MoviesApi mApi;
    private static MoviesRepository mInstance;
    private MutableLiveData<MoviesListData> mMoviesList;
    private MutableLiveData<MovieFullInfo> mMovieFullInfo;
    private MutableLiveData<Boolean> mIsInternetWorking;

    private MoviesRepository() {
        mApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.itv.uz/")
                .client(getOkHttpClient())
                .build()
                .create(MoviesApi.class);

        mMovieFullInfo = new MutableLiveData<>();
        mMoviesList = new MutableLiveData<>();
        mIsInternetWorking = new MutableLiveData<>();
    }

    public static MoviesRepository getInstance(){
        if (mInstance == null) {
            mInstance = new MoviesRepository();
        }
        return mInstance;
    }

    public void loadMoviesList(int page){
        mApi.getListOfMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MoviesListResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(MoviesListResponse moviesListResponse) {
                        if (moviesListResponse != null && moviesListResponse.getData() != null) {
                            mMoviesList.postValue(moviesListResponse.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMoviesList.postValue(null);
                        mIsInternetWorking.postValue(false);
                    }
                });
    }

    public void loadMovieById(int id) {
        mApi.getMovieInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SingleMovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(SingleMovieResponse movieResponse) {
                        if (movieResponse != null && movieResponse.getData() != null) {
                            mMovieFullInfo.postValue(movieResponse.getData().getMovieFull());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMovieFullInfo.postValue(null);
                        mIsInternetWorking.postValue(false);
                    }
                });
    }

    public LiveData<MoviesListData> getMoviesList(){
        return mMoviesList;
    }

    public LiveData<MovieFullInfo> getMovieFullInfo() {
        return mMovieFullInfo;
    }

    public LiveData<Boolean> isInternetWorking(){
        return mIsInternetWorking;
    }

    private OkHttpClient getOkHttpClient(){
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(
                        chain ->{
                            Request original = chain.request();
                            HttpUrl originalHttpUrl = original.url();

                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addQueryParameter("user", Constants.API_KEY)
                                    .build();

                            Request.Builder requestBuilder = original.newBuilder()
                                    .url(url);

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                ).build();
    }
}
