package com.zavosh.itfamili.retrofit.mymodels.grouprequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupDetails  implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summery")
    @Expose
    private String summery;
    @SerializedName("image")
    @Expose
    private String image;
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
    private Boolean isLike;
    @SerializedName("type")
    @Expose
    private String type;

    protected GroupDetails(Parcel in) {
        id = in.readString();
        title = in.readString();
        summery = in.readString();
        image = in.readString();
        linkeCount = in.readString();
        body = in.readString();
        linkAddress = in.readString();
        publishDate = in.readString();
        contentSource = in.readString();
        commentCount = in.readString();
        byte tmpIsLike = in.readByte();
        isLike = tmpIsLike == 0 ? null : tmpIsLike == 1;
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(summery);
        dest.writeString(image);
        dest.writeString(linkeCount);
        dest.writeString(body);
        dest.writeString(linkAddress);
        dest.writeString(publishDate);
        dest.writeString(contentSource);
        dest.writeString(commentCount);
        dest.writeByte((byte) (isLike == null ? 0 : isLike ? 1 : 2));
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GroupDetails> CREATOR = new Creator<GroupDetails>() {
        @Override
        public GroupDetails createFromParcel(Parcel in) {
            return new GroupDetails(in);
        }

        @Override
        public GroupDetails[] newArray(int size) {
            return new GroupDetails[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean isLike) {
        this.isLike = isLike;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
