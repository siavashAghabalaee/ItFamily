
package com.zavosh.itfamily.retrofit.mymodels.magazinerequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagazineResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summery")
    @Expose
    private Object summery;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("linkeCount")
    @Expose
    private String linkeCount;
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

    public Object getSummery() {
        return summery;
    }

    public void setSummery(Object summery) {
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

}
