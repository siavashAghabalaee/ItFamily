
package com.zavosh.itfamily.retrofit.mymodels.homeRequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeResult {

    @SerializedName("sliderContents")
    @Expose
    private List<SliderContent> sliderContents = null;
    @SerializedName("video")
    @Expose
    private Video video;
    @SerializedName("podcasts")
    @Expose
    private List<Podcast> podcasts = null;
    @SerializedName("blogContent")
    @Expose
    private BlogContent blogContent;
    @SerializedName("magzines")
    @Expose
    private List<Magzine> magzines = null;
    @SerializedName("aboutText")
    @Expose
    private String aboutText = "";

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public List<SliderContent> getSliderContents() {
        return sliderContents;
    }

    public void setSliderContents(List<SliderContent> sliderContents) {
        this.sliderContents = sliderContents;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public BlogContent getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(BlogContent blogContent) {
        this.blogContent = blogContent;
    }

    public List<Magzine> getMagzines() {
        return magzines;
    }

    public void setMagzines(List<Magzine> magzines) {
        this.magzines = magzines;
    }

}
