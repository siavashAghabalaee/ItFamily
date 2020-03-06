
package com.zavosh.itfamily.retrofit.mymodels.homeRequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderContent implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summery")
    @Expose
    private Object summery;
    @SerializedName("linkeCount")
    @Expose
    private Object linkeCount;
    @SerializedName("body")
    @Expose
    private Object body;
    @SerializedName("linkAddress")
    @Expose
    private Object linkAddress;
    @SerializedName("publishDate")
    @Expose
    private Object publishDate;
    @SerializedName("contentSource")
    @Expose
    private Object contentSource;
    @SerializedName("commentCount")
    @Expose
    private Object commentCount;

    protected SliderContent(Parcel in) {
        id = in.readString();
        image = in.readString();
        title = in.readString();
    }

    public static final Creator<SliderContent> CREATOR = new Creator<SliderContent>() {
        @Override
        public SliderContent createFromParcel(Parcel in) {
            return new SliderContent(in);
        }

        @Override
        public SliderContent[] newArray(int size) {
            return new SliderContent[size];
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

    public Object getSummery() {
        return summery;
    }

    public void setSummery(Object summery) {
        this.summery = summery;
    }

    public Object getLinkeCount() {
        return linkeCount;
    }

    public void setLinkeCount(Object linkeCount) {
        this.linkeCount = linkeCount;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Object getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(Object linkAddress) {
        this.linkAddress = linkAddress;
    }

    public Object getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Object publishDate) {
        this.publishDate = publishDate;
    }

    public Object getContentSource() {
        return contentSource;
    }

    public void setContentSource(Object contentSource) {
        this.contentSource = contentSource;
    }

    public Object getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Object commentCount) {
        this.commentCount = commentCount;
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
    }
}
