package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieShortInfo implements Parcelable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("files")
    @Expose
    private MovieFiles files;

    public MovieShortInfo(int id, String title, int year, MovieFiles files) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.files = files;
    }

    public MovieShortInfo() {
    }

    protected MovieShortInfo(Parcel in) {
        id = in.readInt();
        title = in.readString();
        year = in.readInt();
        files = in.readParcelable(MovieFiles.class.getClassLoader());
    }

    public static final Creator<MovieShortInfo> CREATOR = new Creator<MovieShortInfo>() {
        @Override
        public MovieShortInfo createFromParcel(Parcel in) {
            return new MovieShortInfo(in);
        }

        @Override
        public MovieShortInfo[] newArray(int size) {
            return new MovieShortInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MovieFiles getMovieFiles() {
        return files;
    }

    public void setMovieFiles(MovieFiles files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(year);
        parcel.writeParcelable(files, i);
    }
}

