package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieFiles implements Parcelable{
    @SerializedName("poster_url")
    @Expose
    private String posterUrl;

    public MovieFiles() {
    }

    public MovieFiles(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    protected MovieFiles(Parcel in) {
        posterUrl = in.readString();
    }

    public static final Parcelable.Creator<MovieFiles> CREATOR = new Parcelable.Creator<MovieFiles>() {
        @Override
        public MovieFiles createFromParcel(Parcel in) {
            return new MovieFiles(in);
        }

        @Override
        public MovieFiles[] newArray(int size) {
            return new MovieFiles[size];
        }
    };

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(posterUrl);
    }
}
