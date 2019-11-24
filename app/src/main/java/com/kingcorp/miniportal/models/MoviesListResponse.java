package com.kingcorp.miniportal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesListResponse implements Parcelable {
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private MoviesListData data;


    public MoviesListResponse(int code, String message, MoviesListData data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MoviesListResponse() {
    }

    protected MoviesListResponse(Parcel in) {
        code = in.readInt();
        message = in.readString();
        data = in.readParcelable(MoviesListData.class.getClassLoader());
    }

    public static final Creator<MoviesListResponse> CREATOR = new Creator<MoviesListResponse>() {
        @Override
        public MoviesListResponse createFromParcel(Parcel in) {
            return new MoviesListResponse(in);
        }

        @Override
        public MoviesListResponse[] newArray(int size) {
            return new MoviesListResponse[size];
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

    public MoviesListData getData() {
        return data;
    }

    public void setData(MoviesListData data) {
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
