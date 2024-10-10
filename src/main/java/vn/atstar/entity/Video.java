package vn.atstar.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")})
@Table(name = "videos")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VideoId")
    private String videoId;

    @Column(name = "Active")
    private int active;

    @Column(name = "Description", length = 500, nullable = true)
    private String description;

    @Column(name = "Poster", length = 500, nullable = true)
    private String poster;

    @Column(name = "Title", length = 500, nullable = true)
    private String title;

    @Column(name = "Views")
    private int views;

    // Bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    // Default constructor
    public Video() {
        // Default constructor
    }

    // All-args constructor
    public Video(String videoId, int active, String description, String poster, String title, int views, Category category) {
        this.videoId = videoId;
        this.active = active;
        this.description = description;
        this.poster = poster;
        this.title = title;
        this.views = views;
        this.category = category;
    }

    // Getter and Setter methods
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @PrePersist
    public void prePersist() {
        this.videoId = "video-" + UUID.randomUUID().toString().replace("-", "");
    }
}
