package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesListData implements Parcelable {
    @SerializedName("item_per_page")
    @Expose
    private int itemPerPage;
    @SerializedName("total_items")
    @Expose
    private int totalItems;
    @SerializedName("movies")
    @Expose
    private ArrayList<MovieShortInfo> movies;

    public MoviesListData(int itemPerPage, int totalItems, ArrayList<MovieShortInfo> movies) {
        this.itemPerPage = itemPerPage;
        this.totalItems = totalItems;
        this.movies = movies;
    }

    public MoviesListData() {
    }

    protected MoviesListData(Parcel in) {
        itemPerPage = in.readInt();
        totalItems = in.readInt();
        movies = in.createTypedArrayList(MovieShortInfo.CREATOR);
    }

    public static final Creator<MoviesListData> CREATOR = new Creator<MoviesListData>() {
        @Override
        public MoviesListData createFromParcel(Parcel in) {
            return new MoviesListData(in);
        }

        @Override
        public MoviesListData[] newArray(int size) {
            return new MoviesListData[size];
        }
    };

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<MovieShortInfo> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MovieShortInfo> movies) {
        this.movies = movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(itemPerPage);
        parcel.writeInt(totalItems);
        parcel.writeTypedList(movies);
    }
}
