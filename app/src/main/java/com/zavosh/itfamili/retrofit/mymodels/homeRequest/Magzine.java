
package com.zavosh.itfamili.retrofit.mymodels.homeRequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Magzine implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;

    protected Magzine(Parcel in) {
        id = in.readString();
        image = in.readString();
        title = in.readString();
        publishDate = in.readString();
    }

    public static final Creator<Magzine> CREATOR = new Creator<Magzine>() {
        @Override
        public Magzine createFromParcel(Parcel in) {
            return new Magzine(in);
        }

        @Override
        public Magzine[] newArray(int size) {
            return new Magzine[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(title);
        dest.writeString(publishDate);
    }
}
