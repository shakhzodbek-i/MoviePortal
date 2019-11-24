package com.kingcorp.miniportal.repository;

import com.kingcorp.miniportal.models.SingleMovieResponse;
import com.kingcorp.miniportal.models.MoviesListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("api/content/main/2/list")
    Single<MoviesListResponse> getListOfMovies(@Query("page") int page);

    @GET("api/content/main/2/show/{id}")
    Single<SingleMovieResponse> getMovieInfo(@Path("id") int id);
}
