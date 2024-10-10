package vn.atstar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    @Column(name = "CategoryName", length = 50, nullable = false)
    @NotEmpty(message = "Null is not allowed!")
    private String categoryName;

    @Column(name = "Image", length = 50, nullable = true)
    private String image;

    @Column(name = "Status")
    private int status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos = new ArrayList<>(); // Initialize the list to avoid null pointer exceptions

    // Default constructor
    public Category() {
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    // Methods for managing videos
    public Video addVideo(Video video) {
        videos.add(video);
        video.setCategory(this);
        return video;
    }

    public Video removeVideo(Video video) {
        videos.remove(video);
        video.setCategory(null);
        return video;
    }

    // Builder class
    public static class Builder {
        private int categoryId;
        private String categoryName;
        private String image;
        private int status;
        private List<Video> videos = new ArrayList<>();

        public Builder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setVideos(List<Video> videos) {
            this.videos = videos;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.setCategoryId(categoryId);
            category.setCategoryName(categoryName);
            category.setImage(image);
            category.setStatus(status);
            category.setVideos(videos);
            return category;
        }
    }
}
