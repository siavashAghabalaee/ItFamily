
package com.zavosh.itfamili.retrofit.mymodels.homeRequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Podcast implements Parcelable {

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
    private String summery;
    @SerializedName("linkeCount")
    @Expose
    private String linkeCount;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("linkAddress")
    @Expose
    private String linkAddress;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("contentSource")
    @Expose
    private String contentSource;
    @SerializedName("commentCount")
    @Expose
    private String commentCount;
    @SerializedName("isLike")
    @Expose
    private String isLike = "false";

    protected Podcast(Parcel in) {
        id = in.readString();
        image = in.readString();
        title = in.readString();
        summery = in.readString();
        linkeCount = in.readString();
        body = in.readString();
        linkAddress = in.readString();
        publishDate = in.readString();
        contentSource = in.readString();
        commentCount = in.readString();
        isLike = in.readString();
    }

    public static final Creator<Podcast> CREATOR = new Creator<Podcast>() {
        @Override
        public Podcast createFromParcel(Parcel in) {
            return new Podcast(in);
        }

        @Override
        public Podcast[] newArray(int size) {
            return new Podcast[size];
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

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getLinkeCount() {
        return linkeCount;
    }

    public void setLinkeCount(String linkeCount) {
        this.linkeCount = linkeCount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getContentSource() {
        return contentSource;
    }

    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
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
        dest.writeString(summery);
        dest.writeString(linkeCount);
        dest.writeString(body);
        dest.writeString(linkAddress);
        dest.writeString(publishDate);
        dest.writeString(contentSource);
        dest.writeString(commentCount);
        dest.writeString(isLike);
    }
}
