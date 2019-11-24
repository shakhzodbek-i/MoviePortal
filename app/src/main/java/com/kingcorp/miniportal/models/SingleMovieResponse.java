package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleMovieResponse implements Parcelable {
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SingleMovieData data;


    public SingleMovieResponse(int code, String message, SingleMovieData data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public SingleMovieResponse() {
    }

    protected SingleMovieResponse(Parcel in) {
        code = in.readInt();
        message = in.readString();
        data = in.readParcelable(MoviesListData.class.getClassLoader());
    }

    public static final Creator<SingleMovieResponse> CREATOR = new Creator<SingleMovieResponse>() {
        @Override
        public SingleMovieResponse createFromParcel(Parcel in) {
            return new SingleMovieResponse(in);
        }

        @Override
        public SingleMovieResponse[] newArray(int size) {
            return new SingleMovieResponse[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SingleMovieData getData() {
        return data;
    }

    public void setData(SingleMovieData data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(message);
        parcel.writeParcelable(data, i);
    }
}
