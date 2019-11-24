package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleMovieData implements Parcelable {
    @SerializedName("movie")
    @Expose
    private MovieFullInfo movieFull;

    protected SingleMovieData(Parcel in) {
        movieFull = in.readParcelable(MovieFullInfo.class.getClassLoader());
    }

    public static final Creator<SingleMovieData> CREATOR = new Creator<SingleMovieData>() {
        @Override
        public SingleMovieData createFromParcel(Parcel in) {
            return new SingleMovieData(in);
        }

        @Override
        public SingleMovieData[] newArray(int size) {
            return new SingleMovieData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(movieFull, i);
    }

    public MovieFullInfo getMovieFull() {
        return movieFull;
    }

    public void setMovieFull(MovieFullInfo movieFull) {
        this.movieFull = movieFull;
    }
}
