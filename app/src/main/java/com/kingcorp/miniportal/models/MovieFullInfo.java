package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieFullInfo implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("countries_str")
    @Expose
    private String countries;
    @SerializedName("genres_str")
    @Expose
    private String genres;
    @SerializedName("files")
    @Expose
    private MovieFiles files;

    public MovieFullInfo(int id,
                         int year,
                         String title,
                         String description,
                         String countries,
                         String genres,
                         MovieFiles files) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.description = description;
        this.countries = countries;
        this.genres = genres;
        this.files = files;
    }

    public MovieFullInfo() {
    }

    protected MovieFullInfo(Parcel in) {
        id = in.readInt();
        year = in.readInt();
        title = in.readString();
        description = in.readString();
        countries = in.readString();
        genres = in.readString();
        files = in.readParcelable(MovieFiles.class.getClassLoader());
    }

    public static final Creator<MovieFullInfo> CREATOR = new Creator<MovieFullInfo>() {
        @Override
        public MovieFullInfo createFromParcel(Parcel in) {
            return new MovieFullInfo(in);
        }

        @Override
        public MovieFullInfo[] newArray(int size) {
            return new MovieFullInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public MovieFiles getFiles() {
        return files;
    }

    public void setFiles(MovieFiles files) {
        this.files = files;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(year);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(countries);
        parcel.writeString(genres);
        parcel.writeParcelable(files, i);
    }
}
